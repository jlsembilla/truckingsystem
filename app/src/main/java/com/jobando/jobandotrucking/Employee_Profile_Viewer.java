package com.jobando.jobandotrucking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.jobando.jobandotrucking.databinding.ActivityEmployeeProfileViewerBinding;

public class Employee_Profile_Viewer extends Employee_Navigation_Drawer_Base {

    ActivityEmployeeProfileViewerBinding activityEmployeeProfileViewerBinding;

    FirebaseFirestore db;

    TextView tfullname, fullname, age, email, workExp, tmodel, platenum;
    Button changeB;
    Button uploadDL;


    EmployeeID eid = new EmployeeID();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityEmployeeProfileViewerBinding = ActivityEmployeeProfileViewerBinding.inflate(getLayoutInflater());
        setContentView(activityEmployeeProfileViewerBinding.getRoot());
        allocateActivityTitle("Profile");

        db = FirebaseFirestore.getInstance();

        uploadDL = findViewById(R.id.uploadDriverLicense);

        changeB = findViewById(R.id.changePass);
        tfullname = findViewById(R.id.top_full_name);
        fullname = findViewById(R.id.full_name);
        age = findViewById(R.id.age);
        email = findViewById(R.id.email);
        workExp = findViewById(R.id.driving_experience);
        tmodel = findViewById(R.id.truck_model);
        platenum = findViewById(R.id.plate_number);

        String e = eid.getEmployeeID();


        db.collection("Drivers").document(e)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot documentSnapshot = task.getResult();

                            if(documentSnapshot != null && documentSnapshot.exists()){

                                String fulls = documentSnapshot.getString("Full Name");
                                String ages = documentSnapshot.getString("Age");
                                String emails = documentSnapshot.getString("Email");
                                String workExps= documentSnapshot.getString("Work Experience");
                                String tmodels = documentSnapshot.getString("Truck Model");
                                String platenums = documentSnapshot.getString("Plate Number");

                                tfullname.setText(fulls);
                                fullname.setText(fulls);
                                age.setText(ages);
                                email.setText(emails);
                                workExp.setText(workExps + " Years");
                                tmodel.setText(tmodels);
                                platenum.setText(platenums);

                                Toast.makeText(Employee_Profile_Viewer.this, "Data Sync Success!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Employee_Profile_Viewer.this, "Cannot Sync Data!", Toast.LENGTH_SHORT).show();
                    }
                });

        uploadDL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Employee_Profile_Viewer.this, Employee_Upload_Driver_License.class);
                startActivity(intent);
            }
        });

    }

}