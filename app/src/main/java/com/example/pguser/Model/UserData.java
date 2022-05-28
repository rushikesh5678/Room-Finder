package com.example.pguser.Model;

public class UserData {

    public String name;
    public String email;
    public String mno;
    public String userKey;

    public UserData() {
    }

    public UserData(String name, String email, String mno, String userKey) {
        this.name = name;
        this.email = email;
        this.mno = mno;
        this.userKey = userKey;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMno() {
        return mno;
    }

    public void setMno(String mno) {
        this.mno = mno;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

}
