package com.jobando.jobandotrucking;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class firebaseConfig {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public void startConfig(Context act)
    {
        Map<String, Object> users = new HashMap<>();
        users.put("username", "root");
        users.put("password", "admin");
        users.put("EmployeeType", "Employer");

        db.collection("Users").document("adminAccount").set(users).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(act, "Configuration Success!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
