package com.example.mohitkumar.trialapp.core.SignUp;

import com.example.mohitkumar.trialapp.data.LoginSignUp.User;

import retrofit2.Response;

public interface ISignUpModel {

    interface OnSignUpFinishedListener {
        void onError(String error);
        void onSignUpModelSuccess(String response);
    }

    void signUp(String email, String password, String username, ISignUpModel.OnSignUpFinishedListener listener);

    void cancelSignUp();
}
