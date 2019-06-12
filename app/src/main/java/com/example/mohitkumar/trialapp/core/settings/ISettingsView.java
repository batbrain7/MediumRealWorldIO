package com.example.mohitkumar.trialapp.core.settings;

import com.example.mohitkumar.trialapp.data.settings.ProfileResponse;

public interface ISettingsView {

    void onSettingsUpdateSuccess(String result);

    void onSettingsUpdateError(String message);

    void onProfileFetchSuccess(ProfileResponse response);

    void onProfileFetchError(String error);

    void displayProgress();
}
