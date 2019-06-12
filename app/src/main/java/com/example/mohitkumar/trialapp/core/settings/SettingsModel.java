package com.example.mohitkumar.trialapp.core.settings;

import com.example.mohitkumar.trialapp.data.AuthService;
import com.example.mohitkumar.trialapp.data.loginsignup.User;
import com.example.mohitkumar.trialapp.data.settings.ProfileResponse;
import com.example.mohitkumar.trialapp.data.settings.UserPOJO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingsModel implements ISettingsModel {

    Call<ProfileResponse> call;
    Call<User> updateCall;

    @Override
    public void updateSettings(UserPOJO object, OnSettingsUpdateListener listener) {
        updateCall = AuthService.getApi().updateProfile(object);
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
    public void getProfile(String username, ISettingsModel.OnFetchProfileListener listener) {
        call = AuthService.getApi().getProfile(username);

        call.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                listener.onProfileFetchSuccess(response);
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                listener.onProfileFetchError(t.toString());
            }
        });
    }

    @Override
    public void cancelSettingsUpdate() {

    }
}
