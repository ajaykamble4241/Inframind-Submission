package com.example.shadowsorland;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class dash3 extends AppCompatActivity {



    FirebaseUser user;
    DatabaseReference reference;
    String userID;


    TextView Temperature,plus,BP,BG,BO,RR;



    // ArrayList<String> myArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_dash3);


        plus = (TextView) findViewById(R.id.pluseR);
        BP = (TextView) findViewById(R.id.pressure);
        BG = (TextView) findViewById(R.id.glucoseL);
        Temperature = findViewById(R.id.temperature);
        BO = (TextView) findViewById(R.id.oxy);
        RR = (TextView) findViewById(R.id.RespirationR);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("User");
        userID = user.getUid();
        reference.child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if (userProfile != null) {
                    float heart = userProfile.heart;
                    float bo = userProfile.bo;
                    float tem = userProfile.tem;
                    float bp = userProfile.bp;
                    float glucose = userProfile.glucose;
                    float respiration = userProfile.respiration;
                    //String Gen = userProfile.gender;
                    //Sensor data HeartBeat Display*************************************************
                    if (heart >= 60 && heart <= 100){
                        plus.setText(heart + "bpm");
                        plus.setTextColor(Color.parseColor("#238E23"));
                    }
                    else {
                        plus.setText(heart + "bpm");
                        plus.setTextColor(Color.parseColor("#FFFF00"));
                    }
                    //******************************************************************************

                    //Sensor data Temperature Display***********************************************
                    if (tem <= 37.2 && tem >= 35){
                        Temperature.setText(tem + "%");
                        Temperature.setTextColor(Color.parseColor("#238E23"));//green
                    }
                    else if(tem >= 37.3 && tem <= 38.2){
                        Temperature.setText(tem + "%");
                        Temperature.setTextColor(Color.parseColor("#FFFF00"));//yellow
                    }
                    else if(tem >= 38.3 && tem <= 41.5){
                        Temperature.setText(tem + "%");
                        Temperature.setTextColor(Color.parseColor("#FFA500"));//orange
                    }
                    else if(tem > 41.5){
                        Temperature.setText(tem + "%");
                        Temperature.setTextColor(Color.parseColor("#FF0000"));//red
                    }
                    else if(tem < 35){
                        Temperature.setText(tem + "%");
                        Temperature.setTextColor(Color.parseColor("#FF0000"));//red
                    }
                    //******************************************************************************

                    //Sensor data Blood oxygen level Display****************************************
                    if (bo >= 95){
                        BO.setText(bo + "%");
                        BO.setTextColor(Color.parseColor("#238E23"));//green
                    }
                    else if(bo < 95 && bo >= 90 ){
                        BO.setText(bo + "%");
                        BO.setTextColor(Color.parseColor("#FFFF00"));//yellow
                    }
                    else if(bo < 90 && bo >= 86 ){
                        BO.setText(bo + "%");
                        BO.setTextColor(Color.parseColor("#FFA500"));//orange
                    }
                    else if(bo < 86  ){
                        BO.setText(bo + "%");
                        BO.setTextColor(Color.parseColor("#FF0000"));//red
                    }
                    //******************************************************************************

                    //Sensor data Blood Pressure Display********************************************
                    if (bp < 120){
                        BP.setText(bp + "Systolic mmHg");
                        BP.setTextColor(Color.parseColor("#238E23"));//green
                    }
                    else if(bp >= 120 && bp <= 139 ){
                        BP.setText(bp + "Systolic mmHg");
                        BP.setTextColor(Color.parseColor("#FFFF00"));//yellow
                    }
                    else if(bp >= 140 && bp <= 159){
                        BP.setText(bp + "Systolic mmHg");
                        BP.setTextColor(Color.parseColor("#FFA500"));//orange
                    }
                    else if(bp < 160  ){
                        BP.setText(glucose + "mg/dL");
                        BP.setTextColor(Color.parseColor("#FF0000"));//red
                    }
                    //******************************************************************************

                    //Sensor data Blood Glucose Level***********************************************
                    if(glucose >= 80 && glucose <= 100 ){
                        BG.setText(glucose + "mg/dL");
                        BG.setTextColor(Color.parseColor("#238E23"));//green
                    }
                    else if(glucose >= 101 && glucose <= 125){
                        BG.setText(glucose + "mg/dL");
                        BG.setTextColor(Color.parseColor("#FFFF00"));//yellow
                    }
                    else if(glucose < 126 ){
                        BG.setText(glucose + "mg/dL");
                        BG.setTextColor(Color.parseColor("#FF0000"));//red
                    }
                    //******************************************************************************

                    //Sensor data Blood oxygen level Display****************************************
                    if (respiration < 12){
                        RR.setText(respiration + "Brpm");
                        RR.setTextColor(Color.parseColor("#FFFF00"));//yellow
                    }
                    else if(respiration < 20 && respiration >= 12 ){
                        RR.setText(respiration   + "%");
                        RR.setTextColor(Color.parseColor("#238E23"));//green

                    }
                    else if(respiration > 20  ){
                        RR.setText(respiration + "%");
                        RR.setTextColor(Color.parseColor("#FF0000"));//red
                    }
                    //******************************************************************************



                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }





}