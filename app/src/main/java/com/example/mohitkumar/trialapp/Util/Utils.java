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
        if (PrefManager.getString(Constants.ACCESS_TOKEN, "") != null) {
            return true;
        }
        return false;
    }

    public void saveToken() {
       // Pref
    }
}
