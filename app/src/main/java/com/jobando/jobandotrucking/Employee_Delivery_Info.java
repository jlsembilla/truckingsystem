package com.jobando.jobandotrucking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.jobando.jobandotrucking.databinding.ActivityEmployeeDeliveryInfoBinding;

public class Employee_Delivery_Info extends Employee_Navigation_Drawer_Base {

    ActivityEmployeeDeliveryInfoBinding activityEmployeeDeliveryInfoBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityEmployeeDeliveryInfoBinding = ActivityEmployeeDeliveryInfoBinding.inflate(getLayoutInflater());
        setContentView(activityEmployeeDeliveryInfoBinding.getRoot());
        allocateActivityTitle("Delivery Information");
    }
}