package com.example.mohitkumar.trialapp.core.Settings;

import com.example.mohitkumar.trialapp.core.writeArticle.IWriteArticleModel;
import com.example.mohitkumar.trialapp.data.CreateAuthService;
import com.example.mohitkumar.trialapp.data.LoginSignUp.User;
import com.example.mohitkumar.trialapp.data.Settings.ProfileResponse;
import com.example.mohitkumar.trialapp.data.comment.SingleArticle;
import com.example.mohitkumar.trialapp.data.writearticle.WriteArticle;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingsModel implements ISettingsModel {

    Call<ProfileResponse> call;
    Call<User> updateCall;

    @Override
    public void updateSettings(String username , String object, OnSettingsUpdateListener listener) {
        updateCall = CreateAuthService.getApi().updateProfile(object);
        updateCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                listener.onSettingsUpdateSuccess(response);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                listener.onSettingsUpdateError(t.toString());
            }
        });


    }

    @Override
    public void getProfile(String username) {

    }

    @Override
    public void cancelSettingsUpdate() {

    }
}
