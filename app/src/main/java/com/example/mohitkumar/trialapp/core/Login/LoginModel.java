package com.example.mohitkumar.trialapp.core.Login;

import android.util.Log;

import com.example.mohitkumar.trialapp.data.CreateService;
import com.example.mohitkumar.trialapp.data.Login.User;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mohitkumar.trialapp.MainApplication.TAG;

public class LoginModel implements ILoginModel{

    Call<User> call;

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

        call = CreateService.getApi().login(object);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d(TAG, response.toString() + "message in response " + response.message() + " code " + response.code());
                listener.onLoginModelSuccess(response);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                listener.onError(t);
            }
        });

    }

    @Override
    public void cancelLogin() {
        call.cancel();
    }
}
