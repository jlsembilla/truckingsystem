package com.jobando.jobandotrucking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;

public class jobAssign extends AppCompatActivity {

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_assign);

        drawerLayout = findViewById(R.id.admin_nav_drawer);
    }

    public void clickAdminMenu(View view) {

        adminDashboard.openDrawer(drawerLayout);
    }

    public void clickLogo(View view){

        adminDashboard.closeDrawer(drawerLayout);
    }

    public void clickAdminDashboard(View view){

        adminDashboard.redirectActivity(this, adminDashboard.class);
    }

    public void clickRegisterDriver(View view){

        adminDashboard.redirectActivity(this, driverRegistration.class);
    }

    public void clickAssign(View view){

        recreate();
    }

    public void clickViewDrivers(View view){

        adminDashboard.redirectActivity(this, viewDrivers.class);
    }

    public void clickViewJobs(View view){

        adminDashboard.redirectActivity(this, viewJobs.class);
    }

    public void clickLogout(View view){

        adminDashboard.logout(this);
    }
}