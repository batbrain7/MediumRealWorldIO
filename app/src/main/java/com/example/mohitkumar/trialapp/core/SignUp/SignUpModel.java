package com.example.mohitkumar.trialapp.core.SignUp;

import android.util.Log;

import com.example.mohitkumar.trialapp.core.Login.ILoginModel;
import com.example.mohitkumar.trialapp.data.CreateService;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.constraint.Constraints.TAG;

public class LoginModel implements ILoginModel{

    Call<String> call;

    @Override
    public void login(String email, String password, OnLoginFinishedListener listener) {
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

        call = CreateService.getApi().login(object.toString());

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
