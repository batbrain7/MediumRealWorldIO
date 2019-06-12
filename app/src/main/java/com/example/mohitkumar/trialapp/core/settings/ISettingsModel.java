package com.example.mohitkumar.trialapp.core.settings;

import com.example.mohitkumar.trialapp.data.loginsignup.User;
import com.example.mohitkumar.trialapp.data.settings.ProfileResponse;
import com.example.mohitkumar.trialapp.data.settings.UserPOJO;

import retrofit2.Response;

public interface ISettingsModel {

    void updateSettings(UserPOJO object, ISettingsModel.OnSettingsUpdateListener listener);

    void getProfile(String username, ISettingsModel.OnFetchProfileListener listener);

    interface OnSettingsUpdateListener {
        void onSettingsUpdateError(String error);
        void onSettingsUpdateSuccess(Response<User> response);
    }

    interface OnFetchProfileListener {
        void onProfileFetchError(String error);
        void onProfileFetchSuccess(Response<ProfileResponse> response);
    }

    void cancelSettingsUpdate();
}
