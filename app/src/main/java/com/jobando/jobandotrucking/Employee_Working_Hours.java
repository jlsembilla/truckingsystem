package com.jobando.jobandotrucking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.jobando.jobandotrucking.databinding.ActivityEmployeeWorkingHoursBinding;

public class Employee_Working_Hours extends Employee_Navigation_Drawer_Base {

    ActivityEmployeeWorkingHoursBinding activityEmployeeWorkingHoursBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityEmployeeWorkingHoursBinding = ActivityEmployeeWorkingHoursBinding.inflate(getLayoutInflater());
        setContentView(activityEmployeeWorkingHoursBinding.getRoot());
        allocateActivityTitle("Working Hours");


    }
}