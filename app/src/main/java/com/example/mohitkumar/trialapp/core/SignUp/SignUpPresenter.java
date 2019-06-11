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
    public void onError(Throwable throwable) {
        signUpView.onSignUpError("Error", throwable.toString());
    }

    @Override
    public void onSignUpModelSuccess(String response) {
        if (response == null)
            Log.d(TAG, "Response is null" + " THIS IS THE TOKEN");
        JSONObject object = null;
        String token;
        try {
            object = new JSONObject(response);
            token = object.getJSONObject("user").getString("token");
            Log.d(TAG, token);
            PrefManager.putString(Constants.ACCESS_TOKEN, token);
            signUpView.onSignUpSuccess();
        } catch (JSONException e) {
            Log.d(TAG, "This user already exists");
            e.printStackTrace();
        }
    }
}
