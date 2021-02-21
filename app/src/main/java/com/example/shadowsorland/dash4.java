package com.example.shadowsorland;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class dash4 extends AppCompatActivity {
    Button submitStress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_dash4);
        submitStress = (Button) findViewById(R.id.submit);

        submitStress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(dash4.this, "Display Results of Stress Level in Health Results", Toast.LENGTH_SHORT).show();
            }
        });


    }
}