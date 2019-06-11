package com.example.mohitkumar.trialapp.Util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.mohitkumar.trialapp.MainApplication;

public class Utils {

    public static boolean hasNetwork() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) MainApplication.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static boolean isLoggedIn() {
        return PrefManager.getBoolean(Constants.LOG_IN);
    }

    public void saveToken() {
       // Pref
    }
}
