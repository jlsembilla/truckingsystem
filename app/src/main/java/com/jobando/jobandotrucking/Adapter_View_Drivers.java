package com.jobando.jobandotrucking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter_View_Drivers extends RecyclerView.Adapter<Adapter_View_Drivers.MyViewHolder> {

    String data1[];
    int image[];
    Context context;

    public Adapter_View_Drivers(Context ct, String s1[], int img[]){
        context = ct;
        data1 = s1;
        image = img;

    }

    @NonNull
    @Override
    public Adapter_View_Drivers.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.view_drivers_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_View_Drivers.MyViewHolder holder, int position) {
        holder.myText1.setText(data1[position]);
        holder.imageView.setImageResource(image[position]);
    }

    @Override
    public int getItemCount() {
        return data1.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView myText1;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            myText1 = itemView.findViewById(R.id.title_text);
            imageView = itemView.findViewById(R.id.images);

        }
    }
}
