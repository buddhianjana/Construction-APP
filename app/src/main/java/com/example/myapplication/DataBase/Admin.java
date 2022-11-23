package com.example.myapplication.DataBase;

public class Admin {
    private String Name;
    private String Email;
    private String Password;
    private String Confirm_Password;

    public Admin(String name, String email, String password, String confirm_Password) {
        Name = name;
        Email = email;
        Password = password;
        Confirm_Password = confirm_Password;
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

    public Admin() {
        Name = "";
        Email = "";
        Password = "";
        Confirm_Password = "";
    }
}
