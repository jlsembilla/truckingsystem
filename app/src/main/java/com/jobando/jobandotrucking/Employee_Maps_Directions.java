package com.jobando.jobandotrucking;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.media.audiofx.Equalizer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jobando.jobandotrucking.databinding.ActivityEmployeeMapsDirectionsBinding;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class Employee_Maps_Directions extends Employee_Navigation_Drawer_Base implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    ActivityEmployeeMapsDirectionsBinding activityEmployeeMapsDirectionsBinding;

    boolean isPermissionGranted;
    GoogleMap gMap;
    FloatingActionButton floatingActionButton;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private int GPS_REQUEST_CODE = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityEmployeeMapsDirectionsBinding = ActivityEmployeeMapsDirectionsBinding.inflate(getLayoutInflater());
        setContentView(activityEmployeeMapsDirectionsBinding.getRoot());
        allocateActivityTitle("Maps & Directions");

        floatingActionButton = findViewById(R.id.fab);
        checkMyPermission();
        initMap();

        fusedLocationProviderClient = new FusedLocationProviderClient(this);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCurrentLoc();
            }
        });


    }

    @SuppressLint("MissingPermission")
    private void getCurrentLoc() {
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                Location location = task.getResult();
                gotoLocation(location.getLatitude(), location.getLongitude());
            }
        });
    }

    private void gotoLocation(double latitude, double longitude) {
        LatLng latLng = new LatLng(latitude, longitude);

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 18);
        gMap.moveCamera(cameraUpdate);
        gMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        MarkerOptions options = new MarkerOptions().position(latLng).title("You are Here!");
        gMap.addMarker(options);
    }

    private void initMap() {


        if(isPermissionGranted){
            if(isGPSEnabled()) {
                SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_map);
                supportMapFragment.getMapAsync(this);
            }
        }
    }

    private boolean isGPSEnabled(){
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        boolean providerEnable = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if(providerEnable){
            return true;
        } else {
            AlertDialog alertDialog = new AlertDialog.Builder(this)
                    .setTitle("GPS Permission")
                    .setMessage("Please enable GPS")
                    .setPositiveButton("Yes", ((dialogInterface, i) -> {
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        startActivityForResult(intent, GPS_REQUEST_CODE);
                    }))
                    .setCancelable(false)
                    .show();
        }
        return false;
    }

    private void checkMyPermission() {
        Dexter.withContext(this).withPermission(Manifest.permission.ACCESS_FINE_LOCATION).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                Toast.makeText(Employee_Maps_Directions.this, "Permission Granted", Toast.LENGTH_SHORT).show();
                isPermissionGranted = true;
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(),"");
                intent.setData(uri);
                startActivity(intent);
            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();
            }
        }).check();
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        gMap = googleMap;
        //gMap.setMyLocationEnabled(true);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GPS_REQUEST_CODE){
            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

            boolean providerEnable = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

            if (providerEnable) {
                Toast.makeText(this, "GPS is enable", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "GPS is not enable", Toast.LENGTH_SHORT).show();
            }
        }
    }
}