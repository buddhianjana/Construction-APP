package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.DataBase.Attendance;
import com.example.myapplication.DataBase.DataHandler;
import com.example.myapplication.DataBase.Supervisor;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Attendancetype extends AppCompatActivity {
    EditText day;
    TextView name;
    final Calendar myCalendar= Calendar.getInstance();
    DataHandler dataHandler = new DataHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendancetype);
        day=findViewById(R.id.attendance);
        name=findViewById(R.id.masonname);
        dataHandler.openDB();

        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };
        day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(Attendancetype.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        editData();
    }
    private void updateLabel(){
        String myFormat="MM/dd/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        day.setText(dateFormat.format(myCalendar.getTime()));
    }

    private void editData() {
        if (getIntent().getBundleExtra("userdata") != null) {
            Bundle bundle = getIntent().getBundleExtra("userdata");
            name.setText(bundle.getString("name"));
        }
    }
    //name validation
    private boolean validDate() {
        String fname = day.getText().toString().trim();


        if (fname.isEmpty()) {
            day.setError("Days are Empty.");
            return false;
        }  else {
            day.setError(null);
            return true;
        }
    }

    public  void mark(View view){
        if (!validDate()) {
            return;
        }
        String mname=name.getText().toString().trim();
        String fname = day.getText().toString().trim();

        Attendance attendance=new Attendance(mname,fname);
        try{
            dataHandler.Mark_Attendance(attendance);
            Toast.makeText(getApplicationContext(), "Added Successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Attendancetype.this, com.example.myapplication.Attendance.class);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
        }
    }
}