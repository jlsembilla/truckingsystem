package com.jobando.jobandotrucking;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Document;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    firebaseConfig configureDB = new firebaseConfig();
    FirebaseFirestore db;
    String  username, passcode, employeeType;

    DocumentReference documentReference;

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