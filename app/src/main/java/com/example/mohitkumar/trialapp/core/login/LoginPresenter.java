package com.example.mohitkumar.trialapp.core.login;

import android.util.Log;
import static com.example.mohitkumar.trialapp.MainApplication.TAG;

import com.example.mohitkumar.trialapp.data.loginsignup.LoginSignUpResponse;
import com.example.mohitkumar.trialapp.data.loginsignup.User;
import com.example.mohitkumar.trialapp.util.Constants;
import com.example.mohitkumar.trialapp.util.PrefManager;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Response;

public class LoginPresenter implements ILoginPresenter, ILoginModel.OnLoginFinishedListener {

    ILoginModel model;
    ILoginView loginView;

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
    public void onError(String throwable) {
        Log.d(TAG, throwable + " THIS IS THE ERROR");
        loginView.onLoginError("ERROR", throwable);
    }

    @Override
    public void onLoginModelSuccess(Response<User> response) {
        if (response.body() == null)
            Log.d(TAG, "Response is null" + " THIS IS THE TOKEN");
        else {
            User user = response.body();
            LoginSignUpResponse upResponse;
            upResponse = user.user;
            PrefManager.putString(Constants.ACCESS_TOKEN, upResponse.token);
            PrefManager.putString(Constants.EMAIL, upResponse.email);
            PrefManager.putString(Constants.USERNAME, upResponse.username);
            loginView.onLoginSuccess();
        }


//        JSONObject object = null;
//        String token;
//        String email;
//        String username;
//        String bio;
//        try {
//            object = new JSONObject(response);
//            token = object.getJSONObject("user").getString("token");
//            email = object.getJSONObject("user").getString("email");
//            username = object.getJSONObject("user").getString("username");
//            bio = object.getJSONObject("user").getString("bio");
//            Log.d(TAG, token);
//            PrefManager.putString(Constants.ACCESS_TOKEN, token);
//            PrefManager.putString(Constants.EMAIL, email);
//            PrefManager.putString(Constants.USERNAME, username);
//            PrefManager.putString(Constants.BIO, bio);
//            loginView.onLoginSuccess();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
    }
}
