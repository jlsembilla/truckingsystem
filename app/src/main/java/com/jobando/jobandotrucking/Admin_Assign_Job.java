package com.jobando.jobandotrucking;

import androidx.annotation.NonNull;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.jobando.jobandotrucking.databinding.ActivityAdminAssignJobBinding;

import org.abego.treelayout.util.AbstractTreeForTreeLayout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Admin_Assign_Job extends Admin_Navigation_Drawer_Base {

    ActivityAdminAssignJobBinding activityAdminAssignJobBinding;

    TextInputEditText place, jobDetails, jobAddress;
    Button assign;

    AutoCompleteTextView selectDriver, selectAccompaniedDriver;
    ArrayAdapter<String> adapterItems;

    ArrayList<String> items = new ArrayList<>();

    ArrayList<String> driverList = new ArrayList<>();
    ArrayList<String> driverEID = new ArrayList<>();
    Map<String, String> drivers = new HashMap<>();

    FirebaseFirestore db;

    private String mainDriver, accompanyDriver, driverID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityAdminAssignJobBinding = ActivityAdminAssignJobBinding.inflate(getLayoutInflater());
        setContentView(activityAdminAssignJobBinding.getRoot());
        allocateActivityTitle("Job Assignation");

        db = FirebaseFirestore.getInstance();

        place = findViewById(R.id.place_text);
        jobDetails = findViewById(R.id.job_details_text);
        jobAddress = findViewById(R.id.job_address_text);
        assign = findViewById(R.id.assign);

        selectDriver = findViewById(R.id.selectDriver);
        selectAccompaniedDriver = findViewById(R.id.selectAccompaniedDriver);
        adapterItems = new ArrayAdapter<String>(this, R.layout.list_item, driverList);

        selectDriver.setAdapter(adapterItems);
        selectAccompaniedDriver.setAdapter(adapterItems);

        db.collection("Drivers")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot documentSnapshot: task.getResult()){
                                driverEID.add(documentSnapshot.getString("EmployeeID"));
                                driverList.add(documentSnapshot.getString("First Name") + " " + documentSnapshot.getString("Last Name"));
                                driverHashMap(documentSnapshot.getString("EmployeeID"), documentSnapshot.getString("First Name") + " " + documentSnapshot.getString("Last Name"));
                            }
                        }
                        else {
                            Toast.makeText(Admin_Assign_Job.this, "Failed to get data", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Admin_Assign_Job.this, "Failed to get data", Toast.LENGTH_SHORT).show();
                    }
                });

        selectDriver.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                driverList.remove(item);
            }
        });

        assign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");
                String currentDate = simpleDateFormat.format(date);

                for(Map.Entry<String, String> entry: drivers.entrySet()){
                    if(entry.getValue().equals(selectDriver.getText().toString())){
                        driverID = entry.getKey();
                    }
                }

                updateFirestoreDB(driverID, place.getText().toString(), jobDetails.getText().toString(), jobAddress.getText().toString(), selectDriver.getText().toString(), selectAccompaniedDriver.getText().toString(), currentDate);
            }
        });
    }

    private void updateFirestoreDB(String ID, String location, String jobDetails, String jobAddress, String mainDriver, String accompanyDriver, String Date){

        Map<String, Object> job = new HashMap<>();
        job.put("Job Location", location);
        job.put("Job Details", jobDetails);
        job.put("Job Address", jobAddress);
        job.put("Main Driver", mainDriver);
        job.put("Accompany Driver", accompanyDriver);
        job.put("Job Date", Date);

        db.collection("Driver's Current Job").document(ID)
                .set(job)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(Admin_Assign_Job.this, "success", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Admin_Assign_Job.this, "failed", Toast.LENGTH_SHORT).show();
                    }
                });

        place.setText("");
        this.jobDetails.setText("");
        this.jobAddress.setText("");
        selectDriver.setText("");
        selectAccompaniedDriver.setText("");
    }


    private void driverHashMap(String employeeID, String driverName) {
        drivers.put(employeeID, driverName);
    }
}