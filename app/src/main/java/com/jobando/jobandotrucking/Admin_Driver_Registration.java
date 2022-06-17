package com.jobando.jobandotrucking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.jobando.jobandotrucking.databinding.ActivityAdminDashboardBinding;
import com.jobando.jobandotrucking.databinding.ActivityAdminDriverRegistrationBinding;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class Admin_Driver_Registration extends Admin_Navigation_Drawer_Base{

    ActivityAdminDriverRegistrationBinding activityAdminDriverRegistrationBinding;

    TextInputEditText fname, mname, lname, age, email, wexp, tmodel, platenum;
    Button register;
    Random random = new Random();

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = FirebaseFirestore.getInstance();

        activityAdminDriverRegistrationBinding = ActivityAdminDriverRegistrationBinding.inflate(getLayoutInflater());
        setContentView(activityAdminDriverRegistrationBinding.getRoot());
        allocateActivityTitle("Driver Registration");

        register = findViewById(R.id.registerBtn);
        fname = findViewById(R.id.firstNameText);
        mname = findViewById(R.id.middleNameText);
        lname = findViewById(R.id.lastNameText);
        age = findViewById(R.id.ageText);
        email = findViewById(R.id.emailText);
        wexp = findViewById(R.id.workExperienceText);
        tmodel = findViewById(R.id.truckModelText);
        platenum = findViewById(R.id.plateNumberText);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(fname.getText().toString().isEmpty() || mname.getText().toString().isEmpty() || lname.getText().toString().isEmpty() || age.getText().toString().isEmpty() || email.getText().toString().isEmpty() || wexp.getText().toString().isEmpty() ||tmodel.getText().toString().isEmpty() || platenum.getText().toString().isEmpty()){

                    Toast.makeText(Admin_Driver_Registration.this, "All fields are required!", Toast.LENGTH_SHORT).show();
                }
                else{

                    String fn = fname.getText().toString();
                    String mn = mname.getText().toString();
                    String ln = lname.getText().toString();
                    String ag = age.getText().toString();
                    String em = email.getText().toString();
                    String we = wexp.getText().toString();
                    String tm = tmodel.getText().toString();
                    String pn = platenum.getText().toString();

                    String uniqueId = UUID.randomUUID().toString();
                    registerDriver(uniqueId,fn, mn, ln, ag, em, we, tm, pn);
                    generateUsernamePassword(uniqueId, fn);
                    clearFields();
                }

            }
        });


    }

    private void registerDriver(String eID,String fName, String mName, String lName, String age, String eMail, String workExp, String tModel, String pNum) {


        Map<String, Object> driverData = new HashMap<>();
        driverData.put("EmployeeID", eID);
        driverData.put("First Name", fName);
        driverData.put("Middle Name", mName);
        driverData.put("Last Name", lName);
        driverData.put("Full Name", fName + " " + mName + " " + lName);
        driverData.put("Age", age);
        driverData.put("Email", eMail);
        driverData.put("Work Experience", workExp);
        driverData.put("Truck Model", tModel);
        driverData.put("Plate Number", pNum);

        db.collection("Drivers").document(eID)
                .set(driverData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(Admin_Driver_Registration.this, "Driver Registration Success!", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Admin_Driver_Registration.this, "Driver Registration Failed!", Toast.LENGTH_SHORT).show();

                    }
                });

    }

    private void generateUsernamePassword(String EmployeeID, String name){

        String uname = name + random.nextInt(100);
        String pass = generatePassword();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Username  & Password");
        builder.setMessage("Username: " + uname + "\nPassword: " + pass);
        builder.setPositiveButton("Copy to Clipboard", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                ClipboardManager cm =(ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("TextView", "Username: " + uname + "\nPassword: " + pass);
                cm.setPrimaryClip(clip);

                Toast.makeText(Admin_Driver_Registration.this, "Copied to Clipboard", Toast.LENGTH_SHORT).show();

                createUserDetails(EmployeeID, uname, pass);
            }
        });

        builder.setNegativeButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                createUserDetails(EmployeeID, uname, pass);
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    private void createUserDetails(String EmployeeID, String uname, String pass){

        Map<String, Object> userData = new HashMap<>();
        userData.put("EmployeeID", EmployeeID);
        userData.put("username", uname);
        userData.put("password", pass);
        userData.put("EmployeeType", "Employee");

        db.collection("Users").document(EmployeeID)
                .set(userData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(Admin_Driver_Registration.this, "User Added Successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Admin_Driver_Registration.this, "Adding User Failed!", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void clearFields() {
        fname.setText("");
        mname.setText("");
        lname.setText("");
        age.setText("");
        email.setText("");
        wexp.setText("");
        tmodel.setText("");
        platenum.setText("");

    }

    private String generatePassword(){
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++){
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }
}