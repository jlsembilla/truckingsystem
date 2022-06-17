package com.jobando.jobandotrucking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.jobando.jobandotrucking.databinding.ActivityEmployeePastRecordsBinding;

import java.util.List;

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