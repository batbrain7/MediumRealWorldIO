package com.example.mohitkumar.trialapp.Util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.mohitkumar.trialapp.MainApplication;

public final class PrefManager {

    private SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainApplication.context);

    public String getString(String preferenceKey, String defaultPreferenceValue) {
        return preferences.getString(preferenceKey, defaultPreferenceValue);
    }

    public void putString(String preferenceKey, String preferenceValue) {
        preferences.edit().putString(preferenceKey, preferenceValue).apply();
    }
}
