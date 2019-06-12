package com.example.mohitkumar.trialapp.core.login;

public interface ILoginPresenter {

    void login(String email, String password);

    void cancelLogin();

    void onAttach(ILoginView loginView);

}
