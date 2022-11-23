package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Salary extends AppCompatActivity {
    EditText name, days;
    TextView mname, day, perday, full, cname, tv18, tv19, tv20, tv21;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salary);
        name = findViewById(R.id.name);
        days = findViewById(R.id.days);

        mname = findViewById(R.id.mname);
        day = findViewById(R.id.day);
        perday = findViewById(R.id.perday);
        full = findViewById(R.id.full);
        cname = findViewById(R.id.cname);
        tv18 = findViewById(R.id.textView18);
        tv19 = findViewById(R.id.textView19);
        tv20 = findViewById(R.id.textView20);
        tv21 = findViewById(R.id.textView21);
    }

    public void click(View view) {
        String masonname = name.getText().toString();
        String date = days.getText().toString();
        mname.setText(masonname);
        day.setText(date);
        perday.setText("3000");
        int dat= Integer.parseInt(days.getText().toString());
        int x=3000;
        full.setText(""+dat * x);
        cname.setText("W.J.Constructions");
        tv18.setText("Name");
        tv19.setText("Days");
        tv20.setText("Per Day");
        tv21.setText("Salary");
    }


}