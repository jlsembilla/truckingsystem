package com.jobando.jobandotrucking;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jobando.jobandotrucking.databinding.ActivityAdminViewDriversBinding;

public class Admin_View_Drivers extends Admin_Navigation_Drawer_Base {

    ActivityAdminViewDriversBinding activityViewDriversBinding;

    String s1[];
    int images[] = {R.drawable.profilep, R.drawable.profilep, R.drawable.profilep, R.drawable.profilep, R.drawable.profilep};

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityViewDriversBinding = activityViewDriversBinding.inflate(getLayoutInflater());
        setContentView(activityViewDriversBinding.getRoot());
        allocateActivityTitle("View Drivers");

        recyclerView = findViewById(R.id.recyclerViewDrivers);

        s1 = getResources().getStringArray(R.array.driver_name);

        Adapter_View_Drivers myAdapter = new Adapter_View_Drivers(this, s1,images);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}