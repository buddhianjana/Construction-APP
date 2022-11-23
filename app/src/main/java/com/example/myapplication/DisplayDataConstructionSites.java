package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.myapplication.Adapters.ConstructionAdapter;
import com.example.myapplication.DataBase.AddSite;
import com.example.myapplication.DataBase.DB;

import java.util.ArrayList;

public class DisplayDataConstructionSites extends AppCompatActivity {
    DB db;
    SQLiteDatabase sqLiteDatabase;
    RecyclerView recyclerView;
    ConstructionAdapter constructionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data_construction_sites);
        recyclerView = findViewById(R.id.rv);
        db = new DB(this);
        displayData();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    private void displayData() {
        sqLiteDatabase = db.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from AddSite", null);
        ArrayList<AddSite> addSites = new ArrayList<>();
        while (cursor.moveToNext()) {
            String sitename = cursor.getString(1);
            String sitelocation = cursor.getString(2);
            addSites.add(new AddSite(sitename, sitelocation));
        }
        cursor.close();
        constructionAdapter = new ConstructionAdapter(this, R.layout.singledataforconstruction, addSites, sqLiteDatabase);
        recyclerView.setAdapter(constructionAdapter);
    }
}