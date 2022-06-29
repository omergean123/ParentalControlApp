package com.omer.parentalcontrolapp;

import android.app.Application;

import com.omer.parentalcontrolapp.Firebase.DataManager;


public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //Initiate FireBase Managers
        DataManager.initHelper();
    }
}