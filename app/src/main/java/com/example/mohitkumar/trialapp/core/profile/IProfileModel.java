package com.example.mohitkumar.trialapp.core.profile;

import com.example.mohitkumar.trialapp.data.settings.ProfileResponse;

import retrofit2.Response;

public interface IProfileModel {
    void getProfile(String username, IProfileModel.OnFetchProfileListener listener);

    interface OnFetchProfileListener {
        void onProfileFetchError(String error);

        void onProfileFetchSuccess(Response<ProfileResponse> response);
    }
}
