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
import com.example.myapplication.DataBase.Masonry;

public class addmasonryworkers extends AppCompatActivity {
    EditText name, nic, email, mobilenumber;
    Button add, view, edit;
    DataHandler dataHandler = new DataHandler(this);
    int id = 0;
    SQLiteDatabase sqLiteDatabase;
    DB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmasonryworkers);
        name = findViewById(R.id.mname);
        email = findViewById(R.id.memeail);
        nic = findViewById(R.id.mcontact);
        mobilenumber = findViewById(R.id.mgender);
        add = findViewById(R.id.add);
        view = findViewById(R.id.view);
        edit = findViewById(R.id.Edit);
        dataHandler.openDB();
        db = new DB(this);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fname = name.getText().toString().trim();
                String mail = email.getText().toString().trim();
                String Nic = nic.getText().toString().trim();
                String phone = mobilenumber.getText().toString().trim();

                if (!validUsername() | !validEmail() | !validPassword() | !validCPassword()) {
                    return;
                }

                Masonry masonry = new Masonry(id, fname, mail, Nic, phone);
                try {
                    dataHandler.Masonry_Create(masonry);
                    Toast.makeText(getApplicationContext(), "Register Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(addmasonryworkers.this, addmasonryworkers.class);
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
                Intent intent = new Intent(addmasonryworkers.this, DisplayDataMasonry.class);
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
                cv.put("nic", nic.getText().toString());
                cv.put("mobile", mobilenumber.getText().toString());
                sqLiteDatabase = db.getReadableDatabase();
                long recedit = sqLiteDatabase.update("Masonry", cv, "id=" + id, null);
                if (recedit != -1) {
                    Toast.makeText(addmasonryworkers.this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                    add.setVisibility(View.VISIBLE);
                    edit.setVisibility(View.GONE);
                } else {
                    Toast.makeText(addmasonryworkers.this, "Data Updated Failed", Toast.LENGTH_SHORT).show();
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
            nic.setText(bundle.getString("nic"));
            mobilenumber.setText(bundle.getString("mobile"));
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
        String Nic = nic.getText().toString().trim();


        if (Nic.isEmpty()) {
            nic.setError("NIC is Empty.");
            return false;
        } else {
            nic.setError(null);
            return true;
        }

    }

    //confirm password validation
    private boolean validCPassword() {
        String phone = mobilenumber.getText().toString().trim();

        if (phone.isEmpty()) {
            mobilenumber.setError("Phone Number is Empty.");
            return false;
        } else {
            mobilenumber.setError(null);
            return true;
        }

    }
}