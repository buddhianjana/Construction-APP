package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.DataBase.AddSite;
import com.example.myapplication.DataBase.DataHandler;

public class addconstructionsites extends AppCompatActivity {
    EditText addsite, location;
    Button add, view;
    DataHandler dataHandler = new DataHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addconstructionsites);
        addsite = findViewById(R.id.sname);
        location = findViewById(R.id.slocation);

        add = findViewById(R.id.addsite);
        view = findViewById(R.id.view);
        dataHandler.openDB();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sname = addsite.getText().toString().trim();
                String slocation = location.getText().toString().trim();


                if (!validSitename() | !validSiteLocation()) {
                    return;
                }

                AddSite addSite = new AddSite(sname, slocation);
                try {
                    dataHandler.Addsite_Create(addSite);
                    Toast.makeText(getApplicationContext(), "Added Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(addconstructionsites.this, addconstructionsites.class);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(addconstructionsites.this, DisplayDataConstructionSites.class);
                startActivity(intent);
            }
        });
    }

    //username empty
    private boolean validSitename() {
        String sname = addsite.getText().toString().trim();

        if (sname.isEmpty()) {
            addsite.setError("Site Name is Empty.");
            return false;
        } else {
            addsite.setError(null);
            return true;
        }

    }

    //password empty
    private boolean validSiteLocation() {
        String slocation = location.getText().toString().trim();

        if (slocation.isEmpty()) {
            location.setError("Site Location is Empty.");
            return false;
        } else {
            location.setError(null);
            return true;
        }

    }
}