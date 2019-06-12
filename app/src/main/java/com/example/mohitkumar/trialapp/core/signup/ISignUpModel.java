package com.example.mohitkumar.trialapp.core.signup;

import com.example.mohitkumar.trialapp.data.loginsignup.User;

import retrofit2.Response;

public interface ISignUpModel {

    interface OnSignUpFinishedListener {
        void onError(String error);
        void onSignUpModelSuccess(Response<User> response);
    }

    void signUp(String email, String password, String username, ISignUpModel.OnSignUpFinishedListener listener);

    void cancelSignUp();
}
