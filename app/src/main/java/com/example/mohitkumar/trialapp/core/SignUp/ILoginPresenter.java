package com.example.mohitkumar.trialapp.core.SignUp;

public interface ILoginPresenter {

    void login(String email, String password);

    void cancelLogin();

    void onAttach(ILoginView loginView);

}
