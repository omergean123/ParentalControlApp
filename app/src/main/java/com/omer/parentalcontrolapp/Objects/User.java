package com.omer.parentalcontrolapp.Objects;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class User {
    String uid;
    String PhoneNumber;
    String password;
    int type;
//    private ArrayList<String> myListsUids;


    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", PhoneNumber='" + PhoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", type=" + type +
                '}';
    }

    public User(){ }

    public User(String uid, String PhoneNumber, String password,int type) {
        this.uid = uid;
        this.PhoneNumber = PhoneNumber;
        this.password = password;
        this.type = type;

    }
    public User(String uid, String PhoneNumber, String password) {
        this.uid = uid;
        this.PhoneNumber = PhoneNumber;
        this.password = password;

    }
    public int getType() {
        return type;
    }

    public String getUid() {
        return uid;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }



    public void setType(int type) {
        this.type = type;
    }

//    public void setUid(String uid) {
//        this.uid = uid;
//    }
//
//    public void setPhoneNumber(String email) {
//        this.PhoneNumber = email;
//    }

    public String getPassword() {
        return password;
    }

//    public void setPassword(String password) {
//        this.password = password;
//    }
//    @Exclude
//    public Map<String, Object> toMap() {
//        HashMap<String, Object> result = new HashMap<>();
//        result.put("uid", uid);
//        result.put("email", PhoneNumber);
//        result.put("password", password);
//        result.put("myListsUids", myListsUids);
//        return result;
//    }
}