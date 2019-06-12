package com.example.mohitkumar.trialapp.core.login;

import com.example.mohitkumar.trialapp.data.loginsignup.User;

import retrofit2.Response;

public interface ILoginModel {

    interface OnLoginFinishedListener {
        void onError(String error);
        void onLoginModelSuccess(Response<User> response);
    }

    void login(String email, String password, OnLoginFinishedListener listener);

    void cancelLogin();
}
