package com.example.shadowsorland;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity{
    Button buttonLO, buttonSU, forgot;
    //TextView textViewLO;
    EditText editTextEmailLO, editTextPasswordLO;
    FirebaseAuth mAuth;
    FirebaseUser userM;
    DatabaseReference referenceM;
    String userIDM;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_main);

        buttonLO = findViewById(R.id.buttonlogin);
        buttonSU = findViewById(R.id.buttonsignup);
        editTextEmailLO = findViewById(R.id.editTextEmailL);
        editTextPasswordLO = findViewById(R.id.editTextPasswordL);
        mAuth = FirebaseAuth.getInstance();
        forgot = findViewById(R.id.forgotpw);
        //textViewLO = findViewById(R.id.textviewsignup);
        buttonSU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(MainActivity.this,signup.class);
                startActivity(intent1);

                Toast.makeText(MainActivity.this, "Open Signup Page",Toast.LENGTH_SHORT).show();
            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText resetMail = new EditText(view.getContext());
                AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(view.getContext());
                passwordResetDialog.setTitle("Reset Password?");
                passwordResetDialog.setMessage("Enter Your Email To Received Reset Link");
                passwordResetDialog.setView(resetMail);

                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        String mail = resetMail.getText().toString();
                        mAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(MainActivity.this, "Reset Link Sent To Your Email", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(MainActivity.this, "Error! Reset Link is Not Sent" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                passwordResetDialog.create().show();
            }
        });




        //findViewById(R.id.buttonlogin).setOnClickListener(this);


        buttonLO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextEmailLO.getText().toString().trim();
                String password = editTextPasswordLO.getText().toString().trim();

                if (email.isEmpty()) {
                    editTextEmailLO.setError("Email is required");
                    editTextEmailLO.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    editTextEmailLO.setError("Please enter a valid Email");
                    editTextEmailLO.requestFocus();
                    return;
                }

                if (password.isEmpty()) {
                    editTextPasswordLO.setError("Password is required");
                    editTextPasswordLO.requestFocus();
                    return;
                }

                if (password.length() < 6) {
                    editTextPasswordLO.setError("Minimum length of Password should be 6");
                    editTextPasswordLO.requestFocus();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), dash.class));
                            userM = FirebaseAuth.getInstance().getCurrentUser();
                            referenceM = FirebaseDatabase.getInstance().getReference("User");
                            userIDM = userM.getUid();

                            referenceM.child(userIDM).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    User userProfile = snapshot.getValue(User.class);

                                    if (userProfile != null) {
                                        float heart = userProfile.heart;
                                        float bo1 = userProfile.bo;
                                        float tem = userProfile.tem;
                                        float bp = userProfile.bp;
                                        float glucose = userProfile.glucose;
                                        String phone = userProfile.phone;
                                        //String Gen = userProfile.gender;
                                        SmsManager mysms = SmsManager.getDefault();





                                    }

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        } else {
                            Toast.makeText(MainActivity.this, "Error!" + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        //findViewById(R.id.textviewsignup).setOnClickListener(this);


    }














    }














