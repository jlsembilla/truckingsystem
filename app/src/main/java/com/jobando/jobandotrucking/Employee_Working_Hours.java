package com.jobando.jobandotrucking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.jobando.jobandotrucking.databinding.ActivityEmployeeWorkingHoursBinding;

import java.text.DateFormat;
import java.util.Calendar;

public class Employee_Working_Hours extends Employee_Navigation_Drawer_Base {

    ActivityEmployeeWorkingHoursBinding activityEmployeeWorkingHoursBinding;

    TextView currentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityEmployeeWorkingHoursBinding = ActivityEmployeeWorkingHoursBinding.inflate(getLayoutInflater());
        setContentView(activityEmployeeWorkingHoursBinding.getRoot());
        allocateActivityTitle("Working Hours");

        currentDate = findViewById(R.id.dateToday);

        Calendar calendar = Calendar.getInstance();
        String date = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        currentDate.setText(date);


    }
}