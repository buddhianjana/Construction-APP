package com.example.myapplication.DataBase;

public class Supervisor {
    private int Id;
    private String Name;
    private String Email;
    private String Password;
    private String Confirm_Password;

    public Supervisor() {
        Name = "";
        Email = "";
        Password = "";
        Confirm_Password = "";
    }

    public Supervisor(int id, String name, String email, String password, String confirm_Password) {
        Id = id;
        Name = name;
        Email = email;
        Password = password;
        Confirm_Password = confirm_Password;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getConfirm_Password() {
        return Confirm_Password;
    }

    public void setConfirm_Password(String confirm_Password) {
        Confirm_Password = confirm_Password;
    }
}
