package com.jobando.jobandotrucking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.jobando.jobandotrucking.databinding.ActivityEmployeePastRecordsBinding;

import java.util.List;

public class Employee_Past_Records extends Employee_Navigation_Drawer_Base{

    ActivityEmployeePastRecordsBinding activityEmployeePastRecordsBinding;

    String s1[], s2[], s3[];
    int images[] = {R.drawable.place, R.drawable.place, R.drawable.place, R.drawable.place, R.drawable.place};

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityEmployeePastRecordsBinding = ActivityEmployeePastRecordsBinding.inflate(getLayoutInflater());
        setContentView(activityEmployeePastRecordsBinding.getRoot());
        allocateActivityTitle("Past Records");

        recyclerView = findViewById(R.id.recyclerView);

        s1 = getResources().getStringArray(R.array.sample_place);
        s2 = getResources().getStringArray(R.array.description);
        s3 = getResources().getStringArray(R.array.date);

        Adapter_Past_Records myAdapter = new Adapter_Past_Records(this, s1, s2, s3, images);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}