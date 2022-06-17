package com.jobando.jobandotrucking;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jobando.jobandotrucking.databinding.ActivityAdminViewJobsBinding;

public class Admin_View_Jobs extends Admin_Navigation_Drawer_Base {

    ActivityAdminViewJobsBinding activityViewJobsBinding;

    String s1[], s2[], s3[];
    int images[] = {R.drawable.place, R.drawable.place, R.drawable.place, R.drawable.place, R.drawable.place};

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityViewJobsBinding = activityViewJobsBinding.inflate(getLayoutInflater());
        setContentView(activityViewJobsBinding.getRoot());
        allocateActivityTitle("View Jobs");

        recyclerView = findViewById(R.id.recyclerViewAdmin);

        s1 = getResources().getStringArray(R.array.sample_place);
        s2 = getResources().getStringArray(R.array.description);
        s3 = getResources().getStringArray(R.array.date);

        Adapter_Past_Records myAdapter = new Adapter_Past_Records(this, s1, s2, s3, images);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}