package com.example.mohitkumar.trialapp.core.Login;

import retrofit2.Response;

public interface ILoginModel {

    interface OnLoginFinishedListener {
        void onError(Throwable throwable);
        void onLoginModelSuccess(Response<String> response);
    }

    void login(String email, String password, OnLoginFinishedListener listener);

    void cancelLogin();
}
