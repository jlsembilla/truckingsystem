package com.jobando.jobandotrucking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;

public class maps extends AppCompatActivity {

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        drawerLayout = findViewById(R.id.nav_drawer);
    }

    public void registerDriver(View view) {
    }

    public void clickMenu(View view) {

        EDashboardActivity.openDrawer(drawerLayout);
    }

    public void clickLogo(View view){

        EDashboardActivity.closeDrawer(drawerLayout);
    }

    public void clickDashboard(View view){

        EDashboardActivity.redirectActivity(this, EDashboardActivity.class);
    }

    public void clickMap(View view){

        recreate();
    }

    public void clickWorkingHours(View view){

        EDashboardActivity.redirectActivity(this, workingHours.class);
    }

    public void clickInfo(View view){

        EDashboardActivity.redirectActivity(this, deliveryInfo.class);
    }

    public void clickRecords(View view){

        EDashboardActivity.redirectActivity(this,pastRecords.class);
    }

    public void clickLogout(View view){

        EDashboardActivity.logout(this);
    }


}