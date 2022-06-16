package com.jobando.jobandotrucking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.jobando.jobandotrucking.databinding.ActivityAdminAssignJobBinding;
import com.jobando.jobandotrucking.databinding.ActivityAdminDashboardBinding;

public class Admin_Assign_Job extends Admin_Navigation_Drawer_Base {

    ActivityAdminAssignJobBinding activityAdminAssignJobBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityAdminAssignJobBinding = ActivityAdminAssignJobBinding.inflate(getLayoutInflater());
        setContentView(activityAdminAssignJobBinding.getRoot());
        allocateActivityTitle("Job Assignation");
    }
}