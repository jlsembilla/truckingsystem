package com.jobando.jobandotrucking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.material.navigation.NavigationView;

public class Employee_Navigation_Drawer_Base extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;

    @Override
    public void setContentView(View view) {

        drawerLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_employee_navigation_drawer_base, null);
        FrameLayout container = drawerLayout.findViewById(R.id.activityContainer);
        container.addView(view);

        super.setContentView(drawerLayout);

        Toolbar toolbar = drawerLayout.findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        NavigationView navigationView = drawerLayout.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.menu_drawer_open, R.string.menu_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        drawerLayout.closeDrawer(GravityCompat.START);
        switch (item.getItemId()){
            case R.id.employee_dashboard:
                startActivity(new Intent(this, Employee_Dashboard.class));
                overridePendingTransition(0,0);
                break;

            case R.id.employee_maps:
                startActivity(new Intent(this, Employee_Maps_Directions.class));
                overridePendingTransition(0,0);
                break;

            case R.id.employee_working_hours:
                startActivity(new Intent(this, Employee_Working_Hours.class));
                overridePendingTransition(0,0);
                break;

            case R.id.employee_delivery_info:
                startActivity(new Intent(this, Employee_Delivery_Info.class));
                overridePendingTransition(0,0);
                break;

            case R.id.employee_past_records:
                startActivity(new Intent(this, Employee_Past_Records.class));
                overridePendingTransition(0,0);
                break;

            case R.id.employee_profile:
                startActivity(new Intent(this, Employee_Profile_Viewer.class));
                overridePendingTransition(0,0);
                break;
            case R.id.employee_logout:


                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Logout");
                builder.setMessage("Are you sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        finishAffinity();

                        System.exit(0);
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                });

                builder.show();
                break;
        }
        return false;
    }

    public void profileViewer(){
        Intent intent = new Intent(this, Employee_Profile_Viewer.class);
        startActivity(intent);
    }

    protected void allocateActivityTitle(String titleString){
        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle(titleString);
        }
    }
}