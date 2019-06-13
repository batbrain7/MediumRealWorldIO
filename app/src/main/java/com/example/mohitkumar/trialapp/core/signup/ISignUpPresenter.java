package com.example.mohitkumar.trialapp.core.signup;

public interface ISignUpPresenter {

    void signUp(String email, String password, String username);

    void onAttach(ISignUpView signUpView);
}
