package com.example.shadowsorland;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class signup extends AppCompatActivity {
    public static final String TAG = "TAG";
    FirebaseDatabase rootnode, RootNode;
    DatabaseReference reference;
    Button buttonsignup;
    RadioButton MaleSS,FemaleSS;
    String gender;
    EditText editTextEmailSS, editTextPasswordSS,editTextNameSS,editTextNumberSS,DoB,Add,id;
    int N=0;
    float heart=0;
    float bo=0;
    float tem=0;
    float bp=0;
    float glucose=0;
    float respiration=0;

    FirebaseAuth mAuth;
    FirebaseFirestore fstore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_signup);


       // id = findViewById(R.id.editTextid);
        buttonsignup = findViewById(R.id.buttonsignup);
        editTextEmailSS =  findViewById(R.id.editTextEmail);
        editTextPasswordSS = findViewById(R.id.editTextPassword);
        editTextNameSS =  findViewById(R.id.editTextName);
        editTextNumberSS =  findViewById(R.id.editTextNumber);
        DoB =  findViewById(R.id.editTextDoB);
        Add = findViewById(R.id.editTextTextPostalAddress);
        MaleSS =  findViewById(R.id.radioMale);
        FemaleSS =  findViewById(R.id.radioFemale);


        mAuth = FirebaseAuth.getInstance();

        buttonsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonSS();
            }
        });

    }



    private void buttonSS() {
        final String email = editTextEmailSS.getText().toString().trim();
        final String name = editTextNameSS.getText().toString().trim();
        String pass = editTextPasswordSS.getText().toString().trim();
        final String phone = editTextNumberSS.getText().toString().trim();
        final String addr = Add.getText().toString().trim();
        final String dob = DoB.getText().toString().trim();
              String m1 = MaleSS.getText().toString();
              String m2 = FemaleSS.getText().toString();

              //String gender = null;


        if(MaleSS.isChecked()){
            gender=m1;

        }

        if (FemaleSS.isChecked()){
            gender=m2;
        }


        if(name.isEmpty()) {
            editTextNameSS.setError("Email is required");
            editTextNameSS.requestFocus();
            return;
        }

        if(email.isEmpty()) {
            editTextEmailSS.setError("Email is required");
            editTextEmailSS.requestFocus();
            return;
        }
        /*
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    editTextEmailSS.setError("Please enter a valid Email");

                    return;
                }


         */
        if(pass.isEmpty()) {
            editTextPasswordSS.setError("Password is required");

            return;
        }

        if(pass.length()<6) {
            editTextPasswordSS.setError("Minimum length of Password should be 6");

            return;
        }

        if(phone.isEmpty()) {
            editTextNumberSS.setError("Phone number is required");

            return;
        }
        /*
        if(!Patterns.PHONE.matcher(phone).matches()){
                    editTextNumberSS.setError("Please enter a valid Phone number");

                    return;
                }


         */
        if(dob.isEmpty()) {
            DoB.setError("dob is required");

            return;
        }

        if(addr.isEmpty()) {
            Add.setError("add is required");

            return;
        }


        //String finalGender = gender;
        mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    User user = new User(name, email, dob, addr, phone, gender, heart, bo, tem, bp, glucose,respiration);

                    FirebaseDatabase.getInstance().getReference("User")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful()){
                                Toast.makeText(signup.this, "User created", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(signup.this, "Error!" + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                }
            }
        });
    }


}