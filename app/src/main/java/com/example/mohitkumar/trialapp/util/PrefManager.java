package com.example.mohitkumar.trialapp.util;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.mohitkumar.trialapp.MainApplication;

public final class PrefManager {

    private static SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainApplication.context);

    public static String getString(String preferenceKey, String defaultPreferenceValue) {
        return preferences.getString(preferenceKey, defaultPreferenceValue);
    }

    public static void putString(String preferenceKey, String preferenceValue) {
        preferences.edit().putString(preferenceKey, preferenceValue).apply();
    }

    public static boolean getBoolean(String preferenceKey) {
        return preferences.getBoolean(preferenceKey, false);
    }

    public static void putBoolean(String preferenceKey, boolean preferenceValue) {
        preferences.edit().putBoolean(preferenceKey, preferenceValue).apply();
    }

    public static void clearToken() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(Constants.ACCESS_TOKEN);
        editor.remove(Constants.EMAIL);
        editor.remove(Constants.LOG_IN);
        editor.remove(Constants.USER_NAME);
        editor.apply();
    }

    public static void clearPrefs() {
        preferences.edit().clear().apply();
    }
}
