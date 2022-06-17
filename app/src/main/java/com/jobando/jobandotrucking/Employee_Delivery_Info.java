package com.jobando.jobandotrucking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jobando.jobandotrucking.databinding.ActivityEmployeeDeliveryInfoBinding;

public class Employee_Delivery_Info extends Employee_Navigation_Drawer_Base {


    ActivityEmployeeDeliveryInfoBinding activityEmployeeDeliveryInfoBinding;
    View v1, v2;
    TextView jDetails, jLocation;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityEmployeeDeliveryInfoBinding = ActivityEmployeeDeliveryInfoBinding.inflate(getLayoutInflater());
        setContentView(activityEmployeeDeliveryInfoBinding.getRoot());
        allocateActivityTitle("Delivery Information");

        jDetails = findViewById(R.id.job_details);
        jLocation = findViewById(R.id.job_Location);
        v1 = findViewById(R.id.view1);
        v2 = findViewById(R.id.view2);

        layout = findViewById(R.id.linear_layout);

    }

    public void expandCardView(View view) {
        int v = (jDetails.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;
        int vs = (jLocation.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;

        TransitionManager.beginDelayedTransition(layout, new AutoTransition());

        jDetails.setVisibility(v);
        jLocation.setVisibility(v);
        v1.setVisibility(v);
        v2.setVisibility(v);
    }
}