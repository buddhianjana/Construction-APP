package com.example.myapplication.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DataHandler {
    private Context con;
    private DB dbCon;
    private SQLiteDatabase db;


    public DataHandler(Context con) {
        this.con = con;
    }

    public void openDB() {
        this.dbCon = new DB(con);
        this.db = dbCon.getWritableDatabase();
    }


    public void Admin_Create(Admin administrator) throws Exception {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", administrator.getName());
        contentValues.put("email", administrator.getEmail());
        contentValues.put("password", administrator.getPassword());
        contentValues.put("cpassword", administrator.getConfirm_Password());
        db.insert("Administrator", null, contentValues);
    }

    public void Supervisor_Create(Supervisor supervisior) throws Exception {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", supervisior.getName());
        contentValues.put("email", supervisior.getEmail());
        contentValues.put("password", supervisior.getPassword());
        contentValues.put("cpassword", supervisior.getConfirm_Password());
        db.insert("Supervisor", null, contentValues);
    }

    public void Addsite_Create(AddSite addSite) throws Exception {
        ContentValues contentValues = new ContentValues();
        contentValues.put("sitename", addSite.getSitename());
        contentValues.put("sitelocation", addSite.getSitelocation());
        db.insert("AddSite", null, contentValues);
    }

    public void Mark_Attendance(Attendance attendance) throws Exception {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", attendance.getName());
        contentValues.put("days", attendance.getDays());
        db.insert("Attendance", null, contentValues);
    }

    public void Masonry_Create(Masonry masonry) throws Exception {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", masonry.getName());
        contentValues.put("email", masonry.getEmail());
        contentValues.put("nic", masonry.getNic());
        contentValues.put("mobile", masonry.getMobileNo());
        db.insert("Masonry", null, contentValues);
    }

    //Check Sign In Record Database Admin
    public boolean checkUsernamePassword(String fullname, String pass) {
        Cursor cursor = db.rawQuery("SELECT * FROM  Administrator WHERE name=? and password=?", new String[]{fullname, pass});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    //Check Sign In Record Database Supervisor
    public boolean checkUsernamePasswordSupervisor(String fullname, String pass) {
        Cursor cursor = db.rawQuery("SELECT * FROM  Supervisor WHERE name=? and password=?", new String[]{fullname, pass});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }
}
