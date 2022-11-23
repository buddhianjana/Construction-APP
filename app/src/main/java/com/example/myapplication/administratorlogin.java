package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.DataBase.DataHandler;

public class administratorlogin extends AppCompatActivity {

    EditText name,password;
    Button signup,login;
    DataHandler dataHandler = new DataHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administratorlogin);
        name = findViewById(R.id.username);
        password = findViewById(R.id.pass);
        signup = findViewById(R.id.signupbtn);
        login = findViewById(R.id.loginbtn);
        dataHandler.openDB();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String adminname = name.getText().toString().trim();
                String adminpass = password.getText().toString().trim();

                if (!validUsername() | !validPassword()) {
                    return;
                }

                Boolean checkUsernamePassword = dataHandler.checkUsernamePassword(adminname, adminpass);
                if (checkUsernamePassword == true) {
                    Toast.makeText(getApplicationContext(), "Logging Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(administratorlogin.this, administratorhome.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Logging Failed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(administratorlogin.this, administratorsignup.class);
                startActivity(intent);
            }
        });
    }
    //username empty
    private boolean validUsername() {
        String aname = name.getText().toString().trim();

        if (aname.isEmpty()) {
            name.setError("Username is Empty.");
            return false;
        } else {
            name.setError(null);
            return true;
        }

    }

    //password empty
    private boolean validPassword() {
        String pass = password.getText().toString().trim();

        if (pass.isEmpty()) {
            password.setError("Password is Empty.");
            return false;
        } else {
            password.setError(null);
            return true;
        }

    }
}