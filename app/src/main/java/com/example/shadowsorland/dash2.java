package com.example.shadowsorland;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class dash2 extends AppCompatActivity {

    FirebaseUser user1;
    DatabaseReference reference1;
    String userID1;
    TextView BO1, Tem, BP, BG, HB,RR;
    String Sms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_dash2);
        ActivityCompat.requestPermissions(dash2.this, new String[] {Manifest.permission.SEND_SMS, Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS},
                PackageManager.PERMISSION_GRANTED);

        BO1 = (TextView) findViewById(R.id.textView20);
        BP = (TextView) findViewById(R.id.textView35);
        BG = (TextView) findViewById(R.id.textView39);
        Tem = (TextView) findViewById(R.id.textView21);
        HB = (TextView) findViewById(R.id.textView40);
        RR = (TextView) findViewById(R.id.textViewrr);

        user1 = FirebaseAuth.getInstance().getCurrentUser();
        reference1 = FirebaseDatabase.getInstance().getReference("User");
        userID1 = user1.getUid();

        reference1.child(userID1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if (userProfile != null) {
                    float heart = userProfile.heart;
                    float bo1 = userProfile.bo;
                    float tem = userProfile.tem;
                    float bp = userProfile.bp;
                    float glucose = userProfile.glucose;
                    float respiration = userProfile.respiration;
                    String phone = userProfile.phone;
                    String dob = userProfile.dob;
                    String Gen = userProfile.gender;
                    SmsManager mysms = SmsManager.getDefault();

                    // HeartBeat ranges...............................................................................................................

                    if (Gen == "Male") {
                        if (heart < 49){
                            HB.setText("Low Heartbeat");
                            HB.setTextColor(Color.parseColor("#FF0000"));//red
                        }
                        else if (heart >= 49 && heart <= 61){

                            HB.setText("Normal Heartbeat");
                            HB.setTextColor(Color.parseColor("#238E23"));//green
                        }
                        else if (heart >= 62 && heart <= 73){

                            HB.setText("High Heartbeat");
                            HB.setTextColor(Color.parseColor("#FFFF00"));//yellow
                        }
                        else if (heart >= 74 && heart <= 82){

                            HB.setText("Very High Heartbeat");
                            HB.setTextColor(Color.parseColor("#FFA500"));//orange
                        }
                        else {
                            HB.setText("High Heartbeat");
                            HB.setTextColor(Color.parseColor("#FF0000"));//red
                        }
                    }
                    else {
                        if (heart < 54){
                            HB.setText("Low Heartbeat");
                            HB.setTextColor(Color.parseColor("#FF0000"));//red
                        }
                        else if (heart >= 54 && heart <= 65){

                            HB.setText("Normal Heartbeat");
                            HB.setTextColor(Color.parseColor("#238E23"));//green
                        }
                        else if (heart >= 66 && heart <= 73){

                            HB.setText("Lightly High Heartbeat");
                            HB.setTextColor(Color.parseColor("#FFFF00"));//yellow
                        }
                        else if (heart >= 74 && heart <= 84){

                            HB.setText("High Heartbeat");
                            HB.setTextColor(Color.parseColor("#FFA500"));//orange
                        }
                        else {
                            HB.setText("High Heartbeat");
                            HB.setTextColor(Color.parseColor("#FF0000"));//red
                        }
                    }

                    //...............................................................................................................................



                    //Blood Oxygen Level...............................................................................................................
                    if (bo1 >= 95){
                        BO1.setText("Normal Blood Oxygen Level");
                        BO1.setTextColor(Color.parseColor("#238E23"));
                    }
                    else if(bo1 < 95 && bo1 >= 90 ){
                        BO1.setText("Average Blood Oxygen Level");
                        BO1.setTextColor(Color.parseColor("#FFFF00"));
                        String message="Your Blood Oxygen Level is 'low' so consult with doctors for there may be chance of 'Mild Hypoxemia'";
                        mysms.sendTextMessage(phone, null, message, null, null);
                    }
                    else if(bo1 < 90 && bo1 >= 86 ){
                        BO1.setText("Low Blood Oxygen Level");
                        BO1.setTextColor(Color.parseColor("#FFA500"));
                        String message="Your Blood Oxygen Level is 'very low' so consult with doctors for there may be chance of 'Moderate Hypoxemia'";
                        mysms.sendTextMessage(phone, null, message, null, null);
                    }
                    else if(bo1 < 86  ){
                        BO1.setText("Very Low Blood Oxygen Level");
                        BO1.setTextColor(Color.parseColor("#FF0000"));
                        String message="Your Blood Oxygen Level is 'very low' so consult with doctors for there may be chance of 'Severe Hypoxemia'";
                        mysms.sendTextMessage(phone, null, message, null, null);

                    }
                    //.................................................................................................................................................

                    //Body Temperature.................................................................................................................................
                    if (tem <= 37.2 && tem >= 35){
                        Tem.setText("Normal Body Temperature");
                        Tem.setTextColor(Color.parseColor("#238E23"));//green
                    }
                    else if(tem >= 37.3 && tem <= 38.2){
                        Tem.setText("Low Grade Fever");
                        Tem.setTextColor(Color.parseColor("#FFFF00"));//yellow
                    }
                    else if(tem >= 38.3 && tem <= 41.5){
                        Tem.setText("Fever");
                        Tem.setTextColor(Color.parseColor("#FFA500"));//orange
                        String message="Your Body Temperature is 'High' so consult with doctors for there may be chance of 'Fever'.";
                        mysms.sendTextMessage(phone, null, message, null, null);
                    }
                    else if(tem > 41.5){
                        Tem.setText("High Fever");
                        Tem.setTextColor(Color.parseColor("#FF0000"));//red
                        String message="Your Body Temperature is 'High' so consult with doctors for there may be chance of 'High Fever'.";
                        mysms.sendTextMessage(phone, null, message, null, null);
                    }
                    else if(tem < 35){
                        Tem.setText("Low Body Temperature");
                        Tem.setTextColor(Color.parseColor("#0000FF"));//Blue
                    }
                    //.................................................................................................................................................
                    //Bloood Pressure Level............................................................................................................................

                    if (bp < 120){
                        BP.setText("Normal Blood Pressure");
                        BP.setTextColor(Color.parseColor("#238E23"));//green
                    }
                    else if(bp >= 120 && bp <= 139 ){
                        BP.setText("High Graded Blood Pressure");
                        BP.setTextColor(Color.parseColor("#FFFF00"));//yellow
                        String message="Your Blood Pressure is 'High' so consult with doctors for there may be chance of 'Prehypertension'.";
                        mysms.sendTextMessage(phone, null, message, null, null);
                    }
                    else if(bp >= 140 && bp <= 159){
                        BP.setText("High Blood Pressure(Stage 1)");
                        BP.setTextColor(Color.parseColor("#FFA500"));//orange
                        String message="Your Blood Pressure is 'High' so consult with doctors for there may be chance of 'Hypertension Stage 1'.";
                        mysms.sendTextMessage(phone, null, message, null, null);
                    }
                    else if(bp > 160  ){
                        BP.setText("High Blood Pressure(Stage 2)");
                        BP.setTextColor(Color.parseColor("#FF0000"));//red
                        String message="Your Blood Pressure is 'High' so consult with doctors for there may be chance of 'Hypertension Stage 2'.";
                        mysms.sendTextMessage(phone, null, message, null, null);
                    }

                    //.................................................................................................................................................
                    //Blood Glucose Level..............................................................................................................................
                    if (glucose<=59){
                        BG.setText("Very Low Glucose Level");
                        BG.setTextColor(Color.parseColor("#FF0000"));//red
                        String message="Your Blood Glucose Level is 'Very Low' so consult with doctors for there may be chance of 'Hypoglycemia'.";
                        mysms.sendTextMessage(phone, null, message, null, null);

                    }
                    else if(glucose<= 79 && glucose >= 60 ){
                        BG.setText("Low Glucose Level");
                        BG.setTextColor(Color.parseColor("#FFFF00"));//yellow
                        String message="Your Blood Glucose Level is 'Low' so consult with doctors for there may be chance of 'Diabetes'.";
                        mysms.sendTextMessage(phone, null, message, null, null);
                    }
                    else if(glucose >= 80 && glucose <= 115 ){
                        BG.setText("Normal Glucose Level");
                        BG.setTextColor(Color.parseColor("#238E23"));//green
                    }
                    else if(glucose >= 116 && glucose <= 126){
                        BG.setText("High Glucose Level");
                        BG.setTextColor(Color.parseColor("#FFFF00"));//yellow
                        String message="Your Blood Glucose Level is 'High' so consult with doctors for there may be chance of 'Early diabetes'.";
                        mysms.sendTextMessage(phone, null, message, null, null);
                    }
                    else if(glucose > 127 ){
                        BG.setText("Very High Glucose Level");
                        BG.setTextColor(Color.parseColor("#FF0000"));//red
                        String message="Your Blood Glucose Level is 'Very High' so consult with doctors for there may be chance of 'Diabetes'.";
                        mysms.sendTextMessage(phone, null, message, null, null);
                    }
                    //.................................................................................................................................................
                    //Sensor data Blood oxygen level Display****************************************
                    if (respiration < 12){
                        RR.setText("Low Respiration Rate");
                        RR.setTextColor(Color.parseColor("#FFFF00"));//yellow
                        String message="Your Respiration  is 'Low' so consult with doctors for there may be chance of 'Bradypnea'.";
                        mysms.sendTextMessage(phone, null, message, null, null);
                    }
                    else if(respiration <= 25 && respiration >= 12 ){
                        RR.setText("Normal Respiration Rate");
                        RR.setTextColor(Color.parseColor("#238E23"));//green

                    }
                    else if(respiration > 25  ){
                        RR.setText("High Respiration Rate");
                        RR.setTextColor(Color.parseColor("#FF0000"));//red
                        String message="Your Respiration  is 'High' so consult with doctors for there may be chance of 'Bronchiectasis'.";
                        mysms.sendTextMessage(phone, null, message, null, null);
                    }
                    //******************************************************************************
                    //Predict Asthma*****************************************************************
                    if (respiration <= 25 && respiration >= 12 && bo1 < 95 && bo1 >= 92 && heart >= 100 && heart <= 125){
                        String message="Consult with doctors for there may be chance of 'Asthma'.";
                        mysms.sendTextMessage(phone, null, message, null, null);
                    }



                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}