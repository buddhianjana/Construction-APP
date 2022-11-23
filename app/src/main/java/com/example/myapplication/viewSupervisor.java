package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.myapplication.Adapters.ViewSupervisor;
import com.example.myapplication.DataBase.DB;
import com.example.myapplication.DataBase.Supervisor;

import java.util.ArrayList;

public class viewSupervisor extends AppCompatActivity {
    DB db;
    SQLiteDatabase sqLiteDatabase;
    RecyclerView recyclerView;
    ViewSupervisor supervisorAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_supervisor);
        recyclerView = findViewById(R.id.rv);
        db = new DB(this);
        displayData();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    private void displayData() {
        sqLiteDatabase = db.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from Supervisor", null);
        ArrayList<Supervisor> supervisiors = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String email = cursor.getString(2);
            String password = cursor.getString(3);
            String cpassword = cursor.getString(4);
            supervisiors.add(new Supervisor(id, name, email, password, cpassword));
        }
        cursor.close();
        supervisorAdapter = new ViewSupervisor(this, R.layout.singeldataviewsupervisor, supervisiors, sqLiteDatabase);
        recyclerView.setAdapter(supervisorAdapter);
    }
}