package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.DataBase.DataHandler;

public class supervisorlogin extends AppCompatActivity {
    EditText username, pass;
    Button login;
    DataHandler dataHandler = new DataHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervisorlogin);
        username = findViewById(R.id.username);
        pass = findViewById(R.id.password);
        login = findViewById(R.id.login);
        dataHandler.openDB();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = username.getText().toString().trim();
                String password = pass.getText().toString().trim();

                if (!validUsername() | !validPassword()) {
                    return;
                }

                Boolean checkUsernamePasswordSupervisor = dataHandler.checkUsernamePasswordSupervisor(name, password);
                if (checkUsernamePasswordSupervisor == true) {
                    Toast.makeText(getApplicationContext(), "Logging Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(supervisorlogin.this,supervisorhome.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(getApplicationContext(), "Logging Failed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    //username empty
    private boolean validUsername() {
        String name = username.getText().toString().trim();

        if (name.isEmpty()) {
            username.setError("Username is Empty.");
            return false;
        } else {
            username.setError(null);
            return true;
        }

    }

    //password empty
    private boolean validPassword() {
        String password = pass.getText().toString().trim();

        if (password.isEmpty()) {
            pass.setError("Password is Empty.");
            return false;
        } else {
            pass.setError(null);
            return true;
        }

    }
}