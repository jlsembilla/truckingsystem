package com.jobando.jobandotrucking;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter_Past_Records extends RecyclerView.Adapter<Adapter_Past_Records.MyViewHolder> {

    String data1[], data2[], data3[];
    int image[];
    Context context;

    public Adapter_Past_Records(Context ct, String s1[], String s2[], String s3[], int img[]){
        context = ct;
        data1 = s1;
        data2 = s2;
        data3 = s3;
        image = img;

    }
    @NonNull
    @Override
    public Adapter_Past_Records.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.past_records_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Past_Records.MyViewHolder holder, int position) {
        holder.myText1.setText(data1[position]);
        holder.myText2.setText(data2[position]);
        holder.myText3.setText(data3[position]);
        holder.imageView.setImageResource(image[position]);
    }

    @Override
    public int getItemCount() {
        return data1.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView myText1, myText2, myText3;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);

            myText1 = itemView.findViewById(R.id.title_text);
            myText2 = itemView.findViewById(R.id.description);
            myText3 = itemView.findViewById(R.id.date);
            imageView = itemView.findViewById(R.id.images);
        }
    }
}
