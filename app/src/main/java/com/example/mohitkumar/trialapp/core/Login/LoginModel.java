package com.example.mohitkumar.trialapp.core.Login;

import com.example.mohitkumar.trialapp.data.API;
import com.example.mohitkumar.trialapp.data.CreateService;
import com.example.mohitkumar.trialapp.data.Login.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginModel implements ILoginModel{

    Call<LoginResponse> call;

    @Override
    public void login(String email, String password, ILoginModel.OnLoginFinishedListener listener) {
        call = CreateService.getApi().login(email, password);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                listener.onLoginModelSuccess(response);
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                listener.onError(t);
            }
        });
    }

    @Override
    public void cancelLogin() {
        call.cancel();
    }
}
