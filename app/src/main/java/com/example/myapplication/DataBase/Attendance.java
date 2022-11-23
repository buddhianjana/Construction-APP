package com.example.myapplication.DataBase;

public class Attendance {
    private String Name;
    private String Days;

    public Attendance() {
        Name = "";
        Days = "";
    }

    public Attendance(String name, String days) {
        Name = name;
        Days = days;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDays() {
        return Days;
    }

    public void setDays(String days) {
        Days = days;
    }
}
