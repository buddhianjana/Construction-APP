package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class supervisorhome extends AppCompatActivity {
Button supervisor,masonry,att,calculate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervisorhome);
        supervisor=findViewById(R.id.sup);
        masonry=findViewById(R.id.mansonry);
        att=findViewById(R.id.att);
        calculate=findViewById(R.id.calculate);

       supervisor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(supervisorhome.this,addsupervisors.class);
                startActivity(intent);
            }
        });

        masonry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(supervisorhome.this,addmasonryworkers.class);
                startActivity(intent);
            }
        });

        att.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(supervisorhome.this,Attendance.class);
                startActivity(intent);
            }
        });

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(supervisorhome.this,calculatesalary.class);
                startActivity(intent);
            }
        });
    }
}