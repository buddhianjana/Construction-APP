package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.DataBase.DB;
import com.example.myapplication.DataBase.DataHandler;
import com.example.myapplication.DataBase.Supervisor;

public class addsupervisors extends AppCompatActivity {
    EditText name, email, password, cpassword;
    Button add, view, edit;
    DataHandler dataHandler = new DataHandler(this);
    SQLiteDatabase sqLiteDatabase;
    DB db;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addsupervisors);
        name = findViewById(R.id.sname);
        email = findViewById(R.id.semail);
        password = findViewById(R.id.spass);
        cpassword = findViewById(R.id.scpass);

        add = findViewById(R.id.add);
        view = findViewById(R.id.view);
        edit = findViewById(R.id.Edit);

        dataHandler.openDB();
        db = new DB(this);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullname = name.getText().toString().trim();
                String emailaddress = email.getText().toString().trim();
                String pass = password.getText().toString().trim();
                String cpass = cpassword.getText().toString().trim();

                if (!validUsername() | !validEmail() | !validPassword() | !validCPassword()) {
                    return;
                }
                Supervisor supervisior = new Supervisor(id, fullname, emailaddress, pass, cpass);
                try {
                    dataHandler.Supervisor_Create(supervisior);
                    Toast.makeText(getApplicationContext(), "Added Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(addsupervisors.this, addsupervisors.class);
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
                Intent intent = new Intent(addsupervisors.this, Displaydataforsupervisor.class);
                startActivity(intent);
            }
        });
        editData();
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues cv = new ContentValues();
                cv.put("name", name.getText().toString());
                cv.put("email", email.getText().toString());
                cv.put("password", password.getText().toString());
                cv.put("cpassword", cpassword.getText().toString());
                sqLiteDatabase = db.getReadableDatabase();
                long recedit = sqLiteDatabase.update("Supervisor", cv, "id=" + id, null);
                if (recedit != -1) {
                    Toast.makeText(addsupervisors.this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                    add.setVisibility(View.VISIBLE);
                    edit.setVisibility(View.GONE);
                } else {
                    Toast.makeText(addsupervisors.this, "Data Updated Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void editData() {
        if (getIntent().getBundleExtra("userdata") != null) {
            Bundle bundle = getIntent().getBundleExtra("userdata");
            id = bundle.getInt("id");
            name.setText(bundle.getString("name"));
            email.setText(bundle.getString("email"));
            password.setText(bundle.getString("password"));
            cpassword.setText(bundle.getString("cpassword"));
            edit.setVisibility(View.VISIBLE);
            add.setVisibility(View.GONE);
        }
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