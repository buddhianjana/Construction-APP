package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.Adapters.CalculateSalary;
import com.example.myapplication.DataBase.Attendance;
import com.example.myapplication.DataBase.DB;

import java.util.ArrayList;

public class calculatesalary extends AppCompatActivity {
    DB db;
    SQLiteDatabase sqLiteDatabase;
    RecyclerView recyclerView;
    CalculateSalary constructionAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculatesalary);
        recyclerView = findViewById(R.id.rv);
        db = new DB(this);
        displayData();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    private void displayData() {
        sqLiteDatabase = db.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from Attendance", null);
        ArrayList<com.example.myapplication.DataBase.Attendance> addSites = new ArrayList<>();
        while (cursor.moveToNext()) {
            String name = cursor.getString(1);
            String days = cursor.getString(2);
            addSites.add(new Attendance(name, days));
        }
        cursor.close();
        constructionAdapter = new CalculateSalary(this, R.layout.singledataforcalculate, addSites, sqLiteDatabase);
        recyclerView.setAdapter(constructionAdapter);
    }

    public void cal(View view){
        Intent intent=new Intent(calculatesalary.this,Salary.class);
        startActivity(intent);
    }
}