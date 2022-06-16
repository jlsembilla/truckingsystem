package com.jobando.jobandotrucking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.jobando.jobandotrucking.databinding.ActivityEmployeePastRecordsBinding;

public class Employee_Past_Records extends Employee_Navigation_Drawer_Base{

    ActivityEmployeePastRecordsBinding activityEmployeePastRecordsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityEmployeePastRecordsBinding = ActivityEmployeePastRecordsBinding.inflate(getLayoutInflater());
        setContentView(activityEmployeePastRecordsBinding.getRoot());
        allocateActivityTitle("Past Records");
    }
}