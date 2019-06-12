package com.example.mohitkumar.trialapp.core.settings;

import com.example.mohitkumar.trialapp.data.settings.UserPOJO;

public interface ISettingsPresenter {
    void onAttach(ISettingsView settingsView);

    void updateSettings(UserPOJO pojo);

    void getProfile(String username);
}
