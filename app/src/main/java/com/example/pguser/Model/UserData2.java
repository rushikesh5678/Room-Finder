package com.example.pguser.Model;

public class UserData2 {

    public String name;
    public String email;
    public String mno;
    public String pgname;
    public String userKey;

    public UserData2() {
    }

    public UserData2(String name, String email, String mno, String userKey, String pgname) {
        this.name = name;
        this.email = email;
        this.mno = mno;
        this.userKey = userKey;
        this.pgname = pgname;
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

    public String getPgname() {
        return pgname;
    }

    public void setPgname(String pgname) {
        this.pgname = pgname;
    }
}
