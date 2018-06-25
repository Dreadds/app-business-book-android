package com.edu.upc.businessbook;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;

public class BusinessBookApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AndroidNetworking.initialize(getApplicationContext());
    }
}
