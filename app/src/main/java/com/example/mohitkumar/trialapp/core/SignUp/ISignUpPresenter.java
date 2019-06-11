package com.example.mohitkumar.trialapp.core.SignUp;

import com.example.mohitkumar.trialapp.core.Login.ILoginView;

public interface ISignUpPresenter {

    void signUp(String email, String password, String username);

    void cancelSignUp();

    void onAttach(ISignUpView signUpView);
}
