package com.jobando.jobandotrucking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jobando.jobandotrucking.databinding.ActivityEmployeePastRecordsBinding;

import java.util.List;

public class Employee_Past_Records extends Employee_Navigation_Drawer_Base{

    ActivityEmployeePastRecordsBinding activityEmployeePastRecordsBinding;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityEmployeePastRecordsBinding = ActivityEmployeePastRecordsBinding.inflate(getLayoutInflater());
        setContentView(activityEmployeePastRecordsBinding.getRoot());
        allocateActivityTitle("Past Records");

        recyclerView = findViewById(R.id.recyclerViewRecords);
    }

    class rowHolder extends RecyclerView.ViewHolder{
        public rowHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    class rvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }
}