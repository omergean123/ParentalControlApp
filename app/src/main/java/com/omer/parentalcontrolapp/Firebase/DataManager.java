package com.omer.parentalcontrolapp.Firebase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.omer.parentalcontrolapp.Objects.User;

import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private final FirebaseAuth firebaseAuth;
    private final FirebaseDatabase realTimeDB;
    private final FirebaseStorage storage;

    private User currentUser;
    private static DataManager single_instance = null;

    public DataManager(){
        firebaseAuth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        realTimeDB = FirebaseDatabase.getInstance();
    }
    public static DataManager getInstance() {
        return single_instance;
    }

    public static DataManager initHelper() {
        if (single_instance == null) {
            single_instance = new DataManager();
        }
        return single_instance;
    }

    //Firebase Getters
    public FirebaseDatabase getRealTimeDB() {
        return realTimeDB;
    }

    public  FirebaseAuth getFirebaseAuth() {
        return firebaseAuth;
    }

    public FirebaseStorage getStorage() {
        return storage;
    }
    public User getCurrentUser() {
        return currentUser;
    }

    public DataManager setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
        return this;
    }

}
