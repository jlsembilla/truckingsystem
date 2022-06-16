package com.jobando.jobandotrucking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.jobando.jobandotrucking.databinding.ActivityEmployeeMapsDirectionsBinding;

public class Employee_Maps_Directions extends Employee_Navigation_Drawer_Base {

    ActivityEmployeeMapsDirectionsBinding activityEmployeeMapsDirectionsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityEmployeeMapsDirectionsBinding = ActivityEmployeeMapsDirectionsBinding.inflate(getLayoutInflater());
        setContentView(activityEmployeeMapsDirectionsBinding.getRoot());
        allocateActivityTitle("Maps & Directions");
    }
}