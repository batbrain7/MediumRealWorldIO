package com.example.mohitkumar.trialapp.Util;

import android.content.Context;
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
}
