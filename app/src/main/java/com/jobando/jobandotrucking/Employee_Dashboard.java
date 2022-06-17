package com.jobando.jobandotrucking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.jobando.jobandotrucking.databinding.ActivityEmployeeDashboardBinding;

public class Employee_Dashboard extends Employee_Navigation_Drawer_Base {

    ActivityEmployeeDashboardBinding activityEmployeeDashboardBinding;

    TextView dashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityEmployeeDashboardBinding = ActivityEmployeeDashboardBinding.inflate(getLayoutInflater());
        setContentView(activityEmployeeDashboardBinding.getRoot());
        allocateActivityTitle("Dashboard");


    }
}