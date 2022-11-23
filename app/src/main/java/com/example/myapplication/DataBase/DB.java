package com.example.myapplication.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DB extends SQLiteOpenHelper {
    public DB(@Nullable Context context) {
        super(context, "WJ-Construction", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Administrator (id INTEGER PRIMARY KEY AUTOINCREMENT ,name text,email text,password text,cpassword text)");
        db.execSQL("CREATE TABLE Supervisor (id INTEGER PRIMARY KEY ,name text,email text,password text,cpassword text)");
        db.execSQL("CREATE TABLE AddSite (id INTEGER PRIMARY KEY AUTOINCREMENT ,SiteName text,SiteLocation text)");
        db.execSQL("CREATE TABLE Masonry (id INTEGER PRIMARY KEY ,name text,email text,nic text,mobile text)");
        db.execSQL("CREATE TABLE Attendance (id INTEGER PRIMARY KEY ,name text,days text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Administrator");
        db.execSQL("drop table if exists Supervisor");
        db.execSQL("drop table if exists AddSite");
        db.execSQL("drop table if exists Masonry");
        db.execSQL("drop table if exists Attendance");
    }
}
