package com.example.mohitkumar.trialapp.core.Login;

import android.content.SharedPreferences;
import android.util.Log;

import com.example.mohitkumar.trialapp.Util.PrefManager;
import com.example.mohitkumar.trialapp.data.Login.LoginResponse;

import retrofit2.Response;


public class LoginPresenter implements ILoginPresenter, ILoginModel.OnLoginFinishedListener{

    ILoginModel model;
    ILoginView loginView;
    SharedPreferences preferences;
    public static final String TAG = "TrialAPP";

    public LoginPresenter() {
        this.model = new LoginModel();
    }



    @Override
    public void login(String email, String password) {
        model.login(email, password, (ILoginModel.OnLoginFinishedListener) this);
    }

    @Override
    public void cancelLogin() {
        model.cancelLogin();
    }

    @Override
    public void onAttach(ILoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void onError(Throwable throwable) {
        Log.d(TAG, throwable.toString() + " THIS IS THE ERROR");
        loginView.onLoginError("ERROR", throwable.toString());
    }

    @Override
    public void onLoginModelSuccess(Response<LoginResponse> response) {
        if (response.isSuccessful() && response.body() != null) {
            LoginResponse loginResponse = response.body();
            Log.d(TAG, loginResponse.getToken().toString() + " THIS IS THE TOKEN");
        }
        loginView.onLoginSuccess();
    }
}
