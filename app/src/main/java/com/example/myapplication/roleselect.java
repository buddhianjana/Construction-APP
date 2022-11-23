package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class roleselect extends AppCompatActivity {
    Button adminbtn,supbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.roleselect);
        adminbtn = findViewById(R.id.adminbtn);
        supbtn = findViewById(R.id.supbtn);
        adminbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(roleselect.this, administratorlogin.class);
                startActivity(intent);
            }

        });
        supbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(roleselect.this, supervisorlogin.class);
                startActivity(intent);
            }
        });
    }
}