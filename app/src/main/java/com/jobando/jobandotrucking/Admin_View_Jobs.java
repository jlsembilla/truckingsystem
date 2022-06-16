package com.jobando.jobandotrucking;

import android.os.Bundle;

import com.jobando.jobandotrucking.databinding.ActivityAdminViewJobsBinding;

public class Admin_View_Jobs extends Admin_Navigation_Drawer_Base {

    ActivityAdminViewJobsBinding activityViewJobsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityViewJobsBinding = activityViewJobsBinding.inflate(getLayoutInflater());
        setContentView(activityViewJobsBinding.getRoot());
        allocateActivityTitle("View Jobs");
    }
}