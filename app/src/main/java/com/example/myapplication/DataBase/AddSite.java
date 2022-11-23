package com.example.myapplication.DataBase;

public class AddSite {
    private String Sitename;
    private String Sitelocation;

    public AddSite(String sitename, String sitelocation) {
        Sitename = sitename;
        Sitelocation = sitelocation;
    }

    public AddSite() {
        Sitename = "";
        Sitelocation = "";
    }

    public String getSitename() {
        return Sitename;
    }

    public void setSitename(String sitename) {
        Sitename = sitename;
    }

    public String getSitelocation() {
        return Sitelocation;
    }

    public void setSitelocation(String sitelocation) {
        Sitelocation = sitelocation;
    }
}
