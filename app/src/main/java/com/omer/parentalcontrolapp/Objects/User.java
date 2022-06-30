package com.omer.parentalcontrolapp.Objects;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.omer.parentalcontrolapp.Firebase.DataManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class User {
    String uid;
    String PhoneNumber;
    String password;
    int type;
//    private ArrayList<String> myListsUids;
    private final DataManager dataManager = DataManager.getInstance();
    private final FirebaseDatabase realtimeDB = dataManager.getRealTimeDB();

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


//    public User getUserByPhoneNumber(String phoneNumber){
//        User tempUser = new User();
//        DatabaseReference myRef = realtimeDB.getReference("Users").child(phoneNumber);
//        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot ds : snapshot.getChildren()){
//                    if(ds.getKey().equals(phoneNumber)){
//                        ds.getChildren().
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }
    public void setType(int type) {
        this.type = type;
    }

//    public void setUid(String uid) {
//        this.uid = uid;
//    }
//
//    public void setPhoneNumber(String phoneNumber) {
//        this.PhoneNumber = phoneNumber;
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