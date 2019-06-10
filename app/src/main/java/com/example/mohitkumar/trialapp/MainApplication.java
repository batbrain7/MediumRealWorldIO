package com.example.mohitkumar.trialapp;

import android.app.Application;
import android.content.Context;

public class MainApplication extends Application {

    public static volatile Context context;
    public static final String TAG = "TrialAPP";

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
