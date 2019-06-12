package com.example.mohitkumar.trialapp.core.Settings;

import com.example.mohitkumar.trialapp.data.LoginSignUp.User;
import com.example.mohitkumar.trialapp.data.Settings.ProfileResponse;
import com.example.mohitkumar.trialapp.data.comment.SingleArticle;
import com.example.mohitkumar.trialapp.data.writearticle.WriteArticle;

import org.json.JSONObject;

import retrofit2.Response;

public interface ISettingsModel {

    void updateSettings(String username, String object, ISettingsModel.OnSettingsUpdateListener listener);

    void getProfile(String username);

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
