package com.jobando.jobandotrucking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class driverRegistration extends AppCompatActivity {
    
    FirebaseFirestore db;
    
    DrawerLayout drawerLayout;

    TextInputEditText fname, mname, lname, age, email, dexp, tmodel, platenum;
    Random random = new Random();

    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_registration);
        
        db = FirebaseFirestore.getInstance();
        
        drawerLayout = findViewById(R.id.admin_nav_drawer);
        register = findViewById(R.id.regBtn);

        fname = findViewById(R.id.fName);
        mname = findViewById(R.id.mName);
        lname = findViewById(R.id.lName);
        email = findViewById(R.id.eMail);
        age = findViewById(R.id.age);
        dexp = findViewById(R.id.drivingExp);
        tmodel = findViewById(R.id.tModel);
        platenum = findViewById(R.id.plateNum);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fname.getText().toString().isEmpty() || mname.getText().toString().isEmpty() || lname.getText().toString().isEmpty() || email.getText().toString().isEmpty() || age.getText().toString().isEmpty() || dexp.getText().toString().isEmpty() || tmodel.getText().toString().isEmpty() || platenum.getText().toString().isEmpty()){
                    Toast.makeText(driverRegistration.this, "Please fill up all fields!", Toast.LENGTH_SHORT).show();
                }
                else {
                    registerDriver(fname.getText().toString(), mname.getText().toString(), lname.getText().toString(), email.getText().toString(), age.getText().toString(), dexp.getText().toString(), tmodel.getText().toString(), platenum.getText().toString());
                }
            }
        });
    }

    public void registerDriver(String fname, String mname, String lname, String email, String age, String dexp, String tmodel, String platenum){

        String eid = "E001";

        Map<String, Object> data = new HashMap<>();
        data.put("EmployeeID", eid);
        data.put("First Name", fname);
        data.put("Middle Name", mname);
        data.put("Last Name", lname);
        data.put("Full Name", fname + " " + mname + " " + lname);
        data.put("Email", email);
        data.put("Age", age);
        data.put("Driving Experience", dexp);
        data.put("Truck Model", tmodel);
        data.put("Plate Number", platenum);

        db.collection("Drivers").document("DriverDetails")
                .set(data)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        generateUsernamePassword(this, fname, eid);
                        Toast.makeText(driverRegistration.this, "Driver Added Successfully!", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(driverRegistration.this, "Adding Driver Failed!", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void generateUsernamePassword(final OnSuccessListener<Void> activity, String name, String eid){

        String uname = name + random.nextInt(100);
        String pass = generatePass();

        AlertDialog.Builder builder = new AlertDialog.Builder((Context) activity);
        builder.setTitle("Generate");
        builder.setMessage("Username: " + uname + "\nPassword: " + pass);
        builder.setPositiveButton("Copy to Clipboard", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("TextView", "Username: " + uname + "\nPassword: " + pass);
                cm.setPrimaryClip(clip);

                Toast.makeText(driverRegistration.this, "Copied to Clipboard", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Map<String, String> data = new HashMap<>();
                data.put("EmployeeID", eid);
                data.put("username", uname);
                data.put("password", pass);
                data.put("EmployeeType", "Employee");

                db.collection("Users").document("employeeAccount")
                        .set(data)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(driverRegistration.this, "User Added Successfully!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(driverRegistration.this, "Adding User Failed!", Toast.LENGTH_SHORT).show();
                            }
                        });

                dialog.dismiss();
            }
        });
    }

    private String generatePass(){
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++){
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }

    /*private String generateEmployeeID(){
        int i = 1;
        String eid = "E00";

        
        if(checkEID(eid + i) == true){
            i++;
            checkEID(eid + i);
        }
        else {
            return eid + i;
        }

        return eid;
    }

    private boolean checkEID(String eid){

        final boolean[] check = new boolean[1];

        db.collection("Users")
                .whereEqualTo("EmployeeID", eid)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            check[0] = true;
                        }
                        else {
                            check[0] = false;
                        }
                    }
                });
        Log.d("check", String.valueOf(check[0]));
        return check[0];
    }*/


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

        recreate();
    }

    public void clickAssign(View view){

        adminDashboard.redirectActivity(this, jobAssign.class);
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