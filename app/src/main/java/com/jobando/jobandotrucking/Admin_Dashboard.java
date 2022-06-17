package com.jobando.jobandotrucking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;

import com.jobando.jobandotrucking.databinding.ActivityAdminDashboardBinding;

public class Admin_Dashboard extends Admin_Navigation_Drawer_Base {

    ActivityAdminDashboardBinding activityAdminDashboardBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAdminDashboardBinding = ActivityAdminDashboardBinding.inflate(getLayoutInflater());
        setContentView(activityAdminDashboardBinding.getRoot());
        allocateActivityTitle("Dashboard");

    }
}