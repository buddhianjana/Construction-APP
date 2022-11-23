package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.DataBase.Admin;
import com.example.myapplication.DataBase.DataHandler;

public class administratorsignup extends AppCompatActivity {
EditText name,email,password,cpassword;
Button signup,login;
    DataHandler dataHandler = new DataHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administratorsignup);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        cpassword = findViewById(R.id.cpassword);
        signup = findViewById(R.id.signup);
        login = findViewById(R.id.login);
        dataHandler.openDB();
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = name.getText().toString().trim();
                String useremail = email.getText().toString().trim();
                String userpass = password.getText().toString().trim();
                String usercpass = cpassword.getText().toString().trim();

                if (!validUsername() | !validEmail() | !validPassword() | !validCPassword()) {
                    return;
                }
                Admin administrator = new Admin(username, useremail, userpass, usercpass);
                try {
                    dataHandler.Admin_Create(administrator);
                    Toast.makeText(getApplicationContext(), "Register Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(administratorsignup.this, administratorsignup.class);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(administratorsignup.this, administratorlogin.class);
                startActivity(intent);
            }
        });


    }
    //name validation
    private boolean validUsername() {
        String fname = name.getText().toString().trim();
        String vname = "^[A-Za-z]\\w{5,29}$";

        if (fname.isEmpty()) {
            name.setError("Username is Empty.");
            return false;
        } else if (!fname.matches(vname)) {
            name.setError("Invalid Name.");
            return false;
        } else {
            name.setError(null);
            return true;
        }

    }

    //email validation
    private boolean validEmail() {
        String mail = email.getText().toString().trim();
        String vemail = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

        if (mail.isEmpty()) {
            email.setError("Email is Empty.");
            return false;
        } else if (!mail.matches(vemail)) {
            email.setError("Invalid Email Address.");
            return false;
        } else {
            email.setError(null);
            return true;
        }

    }

    //password validation
    private boolean validPassword() {
        String pass = password.getText().toString().trim();
        String cpass = cpassword.getText().toString().trim();
        String vpassword = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";

        if (pass.isEmpty()) {
            password.setError("Password is Empty.");
            return false;
        } else if (!pass.matches(vpassword)) {
            password.setError("Use Strong Password.");
            return false;
        } else if (!pass.matches(cpass)) {
            password.setError("Please Use same Password");
            return false;
        } else {
            password.setError(null);
            return true;
        }

    }

    //confirm password validation
    private boolean validCPassword() {
        String cpass = cpassword.getText().toString().trim();
        String pass = password.getText().toString().trim();

        if (cpass.isEmpty()) {
            cpassword.setError("Confirm Password is Empty.");
            return false;
        } else if (!pass.matches(cpass)) {
            cpassword.setError("Please Use same Password");
            return false;
        } else {
            cpassword.setError(null);
            return true;
        }

    }
}