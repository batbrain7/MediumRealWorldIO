package com.example.mohitkumar.trialapp.core.SignUp;

import android.util.Log;

import com.example.mohitkumar.trialapp.Util.Constants;
import com.example.mohitkumar.trialapp.Util.PrefManager;
import com.example.mohitkumar.trialapp.data.LoginSignUp.LoginSignUpResponse;
import com.example.mohitkumar.trialapp.data.LoginSignUp.User;

import org.json.JSONException;
import org.json.JSONObject;

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
    public void cancelSignUp() {
        signUpModel.cancelSignUp();
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
    public void onSignUpModelSuccess(String response) {
        if (response == null)
            Log.d(TAG, "Response is null" + " THIS IS THE TOKEN");
        JSONObject object = null;
        String token;
        String email;
        String username;
        String bio;
        try {
            object = new JSONObject(response);
            token = object.getJSONObject("user").getString("token");
            email = object.getJSONObject("user").getString("email");
            username = object.getJSONObject("user").getString("username");
            bio = object.getJSONObject("user").getString("bio");
            Log.d(TAG, token);
            PrefManager.putString(Constants.ACCESS_TOKEN, token);
            PrefManager.putString(Constants.EMAIL, email);
            PrefManager.putString(Constants.USERNAME, username);
            PrefManager.putString(Constants.BIO, bio);
            signUpView.onSignUpSuccess();
        } catch (JSONException e) {
            Log.d(TAG, "This user already exists");
            e.printStackTrace();
        }
    }
}
