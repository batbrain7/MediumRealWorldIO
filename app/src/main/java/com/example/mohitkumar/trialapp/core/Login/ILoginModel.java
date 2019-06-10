package com.example.mohitkumar.trialapp.core.Login;

import com.example.mohitkumar.trialapp.data.Login.User;

import retrofit2.Response;

public interface ILoginModel {

    interface OnLoginFinishedListener {
        void onError(Throwable throwable);
        void onLoginModelSuccess(Response<User> response);
    }

    void login(String email, String password, OnLoginFinishedListener listener);

    void cancelLogin();
}
