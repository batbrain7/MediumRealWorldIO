package com.example.mohitkumar.trialapp.core.SignUp;

import android.content.SharedPreferences;
import android.util.Log;

import com.example.mohitkumar.trialapp.Util.Constants;
import com.example.mohitkumar.trialapp.Util.PrefManager;
import com.example.mohitkumar.trialapp.core.Login.ILoginModel;
import com.example.mohitkumar.trialapp.data.Login.LoginResponse;
import com.example.mohitkumar.trialapp.data.Login.User;
import com.google.gson.Gson;

import retrofit2.Response;

import static com.example.mohitkumar.trialapp.MainApplication.TAG;


public class LoginPresenter implements ILoginPresenter, ILoginModel.OnLoginFinishedListener{

    ILoginModel model;
    ILoginView loginView;
    SharedPreferences preferences;

    public LoginPresenter() {
        this.model = new SignUpModel();
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
    public void onLoginModelSuccess(Response<String> response) {
        if (response.body() == null)
            Log.d(TAG, "Response is null" + " THIS IS THE TOKEN");
        if (response.isSuccessful() && response.body() != null) {
            LoginResponse loginResponse = null;
//                JSONObject object = (JSONObject) response.body().get("user");
//                loginResponse = new LoginResponse(object.getString("email"), object.getString("token"), object.getString("username"),
//                        object.getString("bio"),object.getString("image"));
//                Log.d(TAG, loginResponse.getToken() + " THIS IS THE TOKEN");

                Gson g = new Gson();
                User p = g.fromJson(response.body(), User.class);
                loginResponse = p.getUser();
                Log.d(TAG, loginResponse.getToken().toString());
                PrefManager.putString(Constants.ACCESS_TOKEN, loginResponse.getToken());
                loginView.onLoginSuccess();

        }
    }
}
