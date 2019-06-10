package com.example.mohitkumar.trialapp.core.Login;

import android.util.Log;

import com.example.mohitkumar.trialapp.data.API;
import com.example.mohitkumar.trialapp.data.CreateService;
import com.example.mohitkumar.trialapp.data.Login.LoginResponse;
import com.example.mohitkumar.trialapp.data.Login.User;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.constraint.Constraints.TAG;

public class LoginModel implements ILoginModel{

    Call<String> call;

    @Override
    public void login(String email, String password, ILoginModel.OnLoginFinishedListener listener) {
        JSONObject object = new JSONObject();
        JSONObject item = new JSONObject();

        try {
            object.put("user", item);
            item.put("email", email);
            item.put("password",password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d(TAG, object.toString());

        String val = "{"  + "user:" + "{" + "email:" + email + "," + "password:" + password + "}}";
        call = CreateService.getApi().login(val);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                listener.onLoginModelSuccess(response);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                listener.onError(t);
            }
        });
    }

    @Override
    public void cancelLogin() {
        call.cancel();
    }
}
