package com.example.mohitkumar.trialapp.core.signup;

import android.util.Log;

import com.example.mohitkumar.trialapp.data.loginsignup.LoginSignUpResponse;
import com.example.mohitkumar.trialapp.data.loginsignup.User;
import com.example.mohitkumar.trialapp.util.Constants;
import com.example.mohitkumar.trialapp.util.PrefManager;

import retrofit2.Response;

import static com.example.mohitkumar.trialapp.MainApplication.TAG;

public class SignUpPresenter implements ISignUpPresenter, ISignUpModel.OnSignUpFinishedListener {

    ISignUpModel signUpModel;
    ISignUpView signUpView;

    @Override
    public void signUp(String email, String password, String username) {
        signUpModel.signUp(email, password, username, this);
    }

    @Override
    public void onAttach(ISignUpView signUpView) {
        this.signUpView =  signUpView;
        this.signUpModel = new SignUpModel();
    }

    @Override
    public void onError(String error) {
        signUpView.onSignUpError("Error", error);
    }

    @Override
    public void onSignUpModelSuccess(Response<User> response) {
        if (response.body() == null)
            Log.d(TAG, "Response is null" + " THIS IS THE TOKEN");
        else {
            User user = response.body();
            LoginSignUpResponse upResponse;
            upResponse = user.user;
            PrefManager.putString(Constants.ACCESS_TOKEN, upResponse.token);
            PrefManager.putString(Constants.USER_NAME, upResponse.username);
            PrefManager.putString(Constants.EMAIL, upResponse.email);
            signUpView.onSignUpSuccess();
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
//            PrefManager.putString(Constants.USER_NAME, username);
//            PrefManager.putString(Constants.BIO, bio);
//            signUpView.onSignUpSuccess();
//        } catch (JSONException e) {
//            Log.d(TAG, "This user already exists");
//            e.printStackTrace();
//        }
    }
}
