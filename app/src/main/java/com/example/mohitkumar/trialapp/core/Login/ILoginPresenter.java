package com.example.mohitkumar.trialapp.core.Login;

public interface ILoginPresenter {

    void login(String email, String password);

    void cancelLogin();

    void onAttach(ILoginView loginView);

}
