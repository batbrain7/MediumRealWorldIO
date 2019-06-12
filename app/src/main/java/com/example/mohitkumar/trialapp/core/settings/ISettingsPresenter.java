package com.example.mohitkumar.trialapp.core.settings;

public interface ISettingsPresenter {
    void onAttach(ISettingsView settingsView);

    void updateSettings(String username, String string);

    void getProfile(String username);
}
