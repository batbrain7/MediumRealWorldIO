package com.example.mohitkumar.trialapp;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

public class MainApplication extends MultiDexApplication {

    public static volatile Context context;
    public static final String TAG = "TrialAPP";

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
