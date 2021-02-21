package com.example.shadowsorland;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class dash1 extends AppCompatActivity {
    TextView Pname, Pemailid, Pdobs, Pgender, Paddrs, Pphnno;
    Button Profile;
    //FirebaseAuth fauth;
    private FirebaseUser user;
    private DatabaseReference reference;
    private  String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_dash1);
        Pname = findViewById(R.id.Name);
        Pemailid = findViewById(R.id.Email);
        Pdobs = findViewById(R.id.Birth);
        Pphnno = findViewById(R.id.Phone);
        Paddrs = findViewById(R.id.Addr);
        Pgender = findViewById(R.id.Gender);
        Profile = findViewById(R.id.button3);



        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(dash1.this,signup.class);
                startActivity(intent1);

                Toast.makeText(dash1.this, "Edit Profile ",Toast.LENGTH_SHORT).show();
            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("User");
        userID = user.getUid();

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if(userProfile != null){
                    String name = userProfile.Name;
                    String email = userProfile.email;
                    String dob = userProfile.dob;
                    String phone = userProfile.phone;
                    String addr = userProfile.Addr;
                    String Gen = userProfile.gender;

                    Pname.setText(name);
                    Pemailid.setText(email);
                    Paddrs.setText(addr);
                    Pphnno.setText(phone);
                    Pdobs.setText(dob);
                    Pgender.setText(Gen);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(dash1.this,"Something wrong happened!",Toast.LENGTH_LONG).show();

            }
        });

    }
}