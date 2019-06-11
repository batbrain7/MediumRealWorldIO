package com.example.mohitkumar.trialapp.core.SignUp;

import com.example.mohitkumar.trialapp.data.CreatePost;
import com.example.mohitkumar.trialapp.data.CreateService;
import com.example.mohitkumar.trialapp.data.LoginSignUp.SUser;
import com.example.mohitkumar.trialapp.data.LoginSignUp.SignUp;
import com.example.mohitkumar.trialapp.data.LoginSignUp.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpModel implements ISignUpModel {

    Call<User> call;

    @Override
    public void signUp(String email, String password, String username, OnSignUpFinishedListener listener) {
        SignUp signUp = new SignUp();
        signUp.email = email;
        signUp.password = password;
        signUp.username = username;

        SUser sUser = new SUser();
//        call = CreateService.getApi().signUp(sUser);
//
//        call.enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
//                listener.onSignUpModelSuccess(response);
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//                listener.onError(t);
//            }
//        });

        CreatePost.newSignUpService(username, email, password, listener);
    }

    @Override
    public void cancelSignUp() {
        call.cancel();
    }
}
