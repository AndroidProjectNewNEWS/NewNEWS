package com.newnews.newnews;

/**
 * Created by Phil on 3/26/2018.
 */

public class User {
    private String email;
    private String password;
    private String uid;

    public User(String email, String password, String uid) {
        this.email = email;
        this.password = password;
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword() {
        this.password = password;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
