package com.example.myapplication.DataBase;

public class Masonry {
    private int Id;
    private String Name;
    private String Email;
    private String Nic;
    private String MobileNo;

    public Masonry(int id, String name, String email, String nic, String mobileNo) {
        Id = id;
        Name = name;
        Email = email;
        Nic = nic;
        MobileNo = mobileNo;
    }

    public Masonry() {
        Name = "";
        Email = "";
        Nic = "";
        MobileNo = "";
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

    public String getNic() {
        return Nic;
    }

    public void setNic(String nic) {
        Nic = nic;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String mobileNo) {
        MobileNo = mobileNo;
    }
}
