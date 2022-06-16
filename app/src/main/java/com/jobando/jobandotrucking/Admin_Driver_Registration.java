package com.jobando.jobandotrucking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.jobando.jobandotrucking.databinding.ActivityAdminDashboardBinding;
import com.jobando.jobandotrucking.databinding.ActivityAdminDriverRegistrationBinding;

public class Admin_Driver_Registration extends Admin_Navigation_Drawer_Base{

    ActivityAdminDriverRegistrationBinding activityAdminDriverRegistrationBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityAdminDriverRegistrationBinding = ActivityAdminDriverRegistrationBinding.inflate(getLayoutInflater());
        setContentView(activityAdminDriverRegistrationBinding.getRoot());
        allocateActivityTitle("Driver Registration");
    }
}