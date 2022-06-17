package com.jobando.jobandotrucking;

import androidx.annotation.NonNull;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.jobando.jobandotrucking.databinding.ActivityAdminAssignJobBinding;

import java.util.ArrayList;

public class Admin_Assign_Job extends Admin_Navigation_Drawer_Base {

    ActivityAdminAssignJobBinding activityAdminAssignJobBinding;

    TextInputEditText place, jobDetails, jobAddress;
    Button assign;

    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;

    ArrayList<String> items = new ArrayList<>();

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityAdminAssignJobBinding = ActivityAdminAssignJobBinding.inflate(getLayoutInflater());
        setContentView(activityAdminAssignJobBinding.getRoot());
        allocateActivityTitle("Job Assignation");

        db = FirebaseFirestore.getInstance();

        db.collection("Drivers")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

        place = findViewById(R.id.place_text);
        jobDetails = findViewById(R.id.job_details_text);
        jobAddress = findViewById(R.id.job_address_text);
        assign = findViewById(R.id.assign);

        autoCompleteTextView = findViewById(R.id.selectDriver);

        adapterItems = new ArrayAdapter<String>(this, R.layout.list_item, items);

        autoCompleteTextView.setAdapter(adapterItems);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
            }
        });

    }
}