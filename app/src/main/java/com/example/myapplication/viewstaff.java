package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class viewstaff extends AppCompatActivity {
Button masonry,supervisor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewstaff);
        masonry=findViewById(R.id.masonry);
        supervisor=findViewById(R.id.supervisor);

        masonry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(viewstaff.this,viewMasonry.class);
                startActivity(intent);
            }
        });
        supervisor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(viewstaff.this,viewSupervisor.class);
                startActivity(intent);
            }
        });
    }
}