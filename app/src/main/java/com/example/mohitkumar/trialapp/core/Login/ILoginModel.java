package com.example.mohitkumar.trialapp.core.Login;

import com.example.mohitkumar.trialapp.data.Login.LoginResponse;

import retrofit2.Response;

public interface ILoginModel {

    public interface OnLoginFinishedListener {
        void onError(Throwable throwable);
        void onLoginModelSuccess(Response<LoginResponse> response);
    }

    void login(String email, String password, OnLoginFinishedListener listener);

    void cancelLogin();
}
