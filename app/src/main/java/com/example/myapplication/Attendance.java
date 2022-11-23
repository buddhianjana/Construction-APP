package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.Adapters.MarkAttendance;
import com.example.myapplication.DataBase.DB;
import com.example.myapplication.DataBase.Masonry;

import java.util.ArrayList;

public class Attendance extends AppCompatActivity {

    DB db;
    SQLiteDatabase sqLiteDatabase;
    RecyclerView recyclerView;
    MarkAttendance masonryAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        recyclerView = findViewById(R.id.rv);
        db = new DB(this);
        displayData();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    private void displayData() {
        sqLiteDatabase = db.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from Masonry", null);
        ArrayList<Masonry> masonries = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id=cursor.getInt(0);
            String name = cursor.getString(1);
            String email=cursor.getString(2);
            String nic=cursor.getString(3);
            String phone=cursor.getString(4);
            masonries.add(new Masonry(id,name, email,nic,phone));
        }
        cursor.close();
        masonryAdapter = new MarkAttendance(this, R.layout.singledataforattendance, masonries, sqLiteDatabase);
        recyclerView.setAdapter(masonryAdapter);
    }

}