package com.example.mohitkumar.trialapp.core.signup;

import com.example.mohitkumar.trialapp.data.Service;
import com.example.mohitkumar.trialapp.data.loginsignup.SUser;
import com.example.mohitkumar.trialapp.data.loginsignup.SignUp;
import com.example.mohitkumar.trialapp.data.loginsignup.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpModel implements ISignUpModel {

    Call<User> call;

    @Override
    public void signUp(String email, String password, String username, OnSignUpFinishedListener listener) {
        SUser user = new SUser();
        SignUp signUp = new SignUp();
        signUp.email = email;
        signUp.username = username;
        signUp.password = password;
        user.user = signUp;

        call = Service.getApi().signUp(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                listener.onSignUpModelSuccess(response);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                listener.onError(t.toString());
            }
        });
    }

    @Override
    public void cancelSignUp() {
        call.cancel();
    }
}
