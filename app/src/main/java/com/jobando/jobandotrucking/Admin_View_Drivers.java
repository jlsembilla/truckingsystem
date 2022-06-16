package com.jobando.jobandotrucking;

import android.os.Bundle;

import com.jobando.jobandotrucking.databinding.ActivityAdminViewDriversBinding;

public class Admin_View_Drivers extends Admin_Navigation_Drawer_Base {

    ActivityAdminViewDriversBinding activityViewDriversBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityViewDriversBinding = activityViewDriversBinding.inflate(getLayoutInflater());
        setContentView(activityViewDriversBinding.getRoot());
        allocateActivityTitle("View Drivers");
    }
}