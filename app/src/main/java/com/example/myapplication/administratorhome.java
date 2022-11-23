package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class administratorhome extends AppCompatActivity {
    Button addconstructionsites,addsup,addmw,viewstaff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administratorhome);
        addconstructionsites = findViewById(R.id.addconstructionsites);
        addsup = findViewById(R.id.addsup);
        addmw = findViewById(R.id.addmw);
        viewstaff = findViewById(R.id.viewstaff);
        addconstructionsites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(administratorhome.this, addconstructionsites.class);
                startActivity(intent);
            }
        });

        addsup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(administratorhome.this, addsupervisors.class);
                startActivity(intent);
            }
        });

        addmw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(administratorhome.this, addmasonryworkers.class);
                startActivity(intent);
            }
        });

        viewstaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(administratorhome.this, viewstaff.class);
                startActivity(intent);
            }
        });

    }
}