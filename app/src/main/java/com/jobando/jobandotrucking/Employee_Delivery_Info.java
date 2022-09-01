package com.jobando.jobandotrucking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.jobando.jobandotrucking.databinding.ActivityEmployeeDeliveryInfoBinding;

import java.security.DomainCombiner;

public class Employee_Delivery_Info extends Employee_Navigation_Drawer_Base {


    ActivityEmployeeDeliveryInfoBinding activityEmployeeDeliveryInfoBinding;

    TextView jobPlace, jobDescription, jobDate, jobAddress;
    Button getDirections;

    FirebaseFirestore db;

    EmployeeID employeeID = new EmployeeID();

    String driversName;

    String a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityEmployeeDeliveryInfoBinding = ActivityEmployeeDeliveryInfoBinding.inflate(getLayoutInflater());
        setContentView(activityEmployeeDeliveryInfoBinding.getRoot());
        allocateActivityTitle("Delivery Information");

        jobPlace = findViewById(R.id.locationTitle);
        jobDescription = findViewById(R.id.jobDescription);
        jobDate = findViewById(R.id.jobDate);
        jobAddress = findViewById(R.id.jobAddress);
        getDirections = findViewById(R.id.getDirections);
        db = FirebaseFirestore.getInstance();

        jobPlace.setText("Job Not Assigned Yet");
        jobDate.setText("Job Not Assigned Yet");
        jobDescription.setText("Job Not Assigned Yet");
        jobAddress.setText("Job Not Assigned Yet");

        db.collection("Drivers").document(employeeID.getEmployeeID())
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot snapshot = task.getResult();
                            if(snapshot != null && snapshot.exists()){
                                driversName = snapshot.get("First Name") + " " + snapshot.get("Last Name");
                                Log.d("statae", driversName);
                            }
                        }
                    }
                });

        db.collection("Driver's Current Job").document(employeeID.getEmployeeID())
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot snapshot = task.getResult();
                            if(snapshot != null && snapshot.exists()){

                                jobPlace.setText(snapshot.getString("Job Location"));
                                jobDate.setText(snapshot.getString("Job Date"));
                                jobDescription.setText(snapshot.getString("Job Details"));
                                jobAddress.setText(snapshot.getString("Job Address"));
                            } else{

                                db.collection("Driver's Current Job").whereEqualTo("Accompany Driver", driversName)
                                        .get()
                                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                if(task.isSuccessful()){
                                                    Log.d("Scua", "TAE IS GREAT");
                                                    for(DocumentSnapshot documentSnapshot : task.getResult()){
                                                        jobPlace.setText(documentSnapshot.getString("Job Location"));
                                                        jobDate.setText(documentSnapshot.getString("Job Date"));
                                                        jobDescription.setText(documentSnapshot.getString("Job Details"));
                                                        jobAddress.setText(documentSnapshot.getString("Job Address"));
                                                    }

                                                }
                                            }
                                        });
                            }
                        }
                    }
                });
    }



}