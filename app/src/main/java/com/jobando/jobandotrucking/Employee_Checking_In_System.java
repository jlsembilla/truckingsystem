package com.jobando.jobandotrucking;


import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Employee_Checking_In_System {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Map<String, Object> checkInTime = new HashMap();
    Calendar calendar = Calendar.getInstance();

    public void setTimeIn(String eid){

        String currentTime = new SimpleDateFormat("hh:mm a", Locale.getDefault()).format(new Date());
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM", Locale.getDefault());
        String nameOfMonth = sdf.format(calendar.getTime());
        String dateOnly = DateFormat.getDateInstance().format(calendar.getTime());

        checkInTime.put("Time In" , currentTime);
        checkInTime.put("Date", currentDate);
        checkInTime.put("Time Out", "");

        db.collection("Checking In System")
                .document(nameOfMonth)
                .collection(dateOnly)
                .document(eid)
                .set(checkInTime).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("dae", "Tapos na bonak ka kase");
                    }
                });

    }

    private Map<String, Object> getTimeIn (){
        return null;
    }


}
