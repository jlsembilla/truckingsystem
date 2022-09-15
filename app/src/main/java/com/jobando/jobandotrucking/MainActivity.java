package com.jobando.jobandotrucking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    firebaseConfig configureDB = new firebaseConfig();
    FirebaseFirestore db;
    String  username, passcode, employeeType;

    Employee_Checking_In_System checkingInSystem = new Employee_Checking_In_System();
    EmployeeID epv = new EmployeeID();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configureDB.startConfig(this);
        Button btnlogin = findViewById(R.id.buttonLogin);
        TextView user = findViewById(R.id.usernametxt);
        TextView code = findViewById(R.id.passwordtxt);
        db = FirebaseFirestore.getInstance();


        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = user.getText().toString();
                passcode = code.getText().toString();

                checkUsernamePassword(username, passcode);
            }
        });
    }

    private void checkUsernamePassword(String user, String pass){

        db.collection("Users")
                .whereEqualTo("username", user)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult()){
                                String passW = document.getString("password");
                                employeeType = document.getString("EmployeeType");
                                if(pass.equals(passW)){
                                    if(employeeType.equals("Employer")){
                                        Intent intent = new Intent(MainActivity.this, Admin_Dashboard.class);
                                        startActivity(intent);

                                        Toast.makeText(MainActivity.this, "Admin Login Success", Toast.LENGTH_SHORT).show();
                                    }
                                    else if(employeeType.equals("Employee")){

                                        String eid = document.getString("EmployeeID");

                                        db.collection("Drivers")
                                                .whereEqualTo("EmployeeID", eid)
                                                .get()
                                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                        if(task.isSuccessful()){
                                                            for(QueryDocumentSnapshot document : task.getResult()){
                                                                String eName = document.getString("Last Name");

                                                                epv.setEmployeeID(eid);
                                                                epv.setEmployeeName(eName);
                                                                checkingInSystem.setTimeIn(eid);
                                                            }
                                                        }
                                                    }
                                                });

                                        Intent intent = new Intent(MainActivity.this, Employee_Dashboard.class);
                                        startActivity(intent);
                                        Toast.makeText(MainActivity.this, "Employee Login Success", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else {
                                    Toast.makeText(MainActivity.this, "Password is incorrect!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "Username is incorrect", Toast.LENGTH_SHORT).show();
            }
        });
    }

}