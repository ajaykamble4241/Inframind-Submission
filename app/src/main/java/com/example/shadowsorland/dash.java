package com.example.shadowsorland;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class dash extends AppCompatActivity {
    private CardView card6;
    private CardView card5;
    private CardView card4;
    private CardView card3;
    private CardView card2;
    private CardView card1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_dash);

        //click on dashboard
        card6 = (CardView) findViewById(R.id.c6);
        card6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opendash();

            }
        });

        card5 = (CardView) findViewById(R.id.c5);
        card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opendash5();

            }
        });

        card4 = (CardView) findViewById(R.id.c4);
        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opendash4();

            }
        });

        card3 = (CardView) findViewById(R.id.c3);
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opendash3();

            }
        });

        card2 = (CardView) findViewById(R.id.c2);
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opendash2();

            }
        });

        card1 = (CardView) findViewById(R.id.c1);
        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opendash1();

            }
        });
        //End click on dashboard

    }






    //Connectivity of forms
    //logout
    public void opendash() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    //Remainder
    public void opendash5() {
        Intent intent = new Intent(this, dash5.class);
        startActivity(intent);
    }
    //Diagnosis
    public void opendash4() {
        Intent intent = new Intent(this, dash4.class);
        startActivity(intent);
    }
    //Health
    public void opendash3() {
        Intent intent = new Intent(this, dash3.class);
        startActivity(intent);
    }
    //History
    public void opendash2() {
        Intent intent = new Intent(this, dash2.class);
        startActivity(intent);
    }
    //Profile
    public void opendash1() {
        Intent intent = new Intent(this, dash1.class);
        startActivity(intent);
    }
    //End Connectivity of forms









}