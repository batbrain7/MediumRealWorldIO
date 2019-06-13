package com.example.mohitkumar.trialapp.core.login;

import android.util.Log;

import com.example.mohitkumar.trialapp.network.Service;
import com.example.mohitkumar.trialapp.data.loginsignup.LUser;
import com.example.mohitkumar.trialapp.data.loginsignup.LoginPOJO;
import com.example.mohitkumar.trialapp.data.loginsignup.User;

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

        LUser user = new LUser();
        LoginPOJO pojo = new LoginPOJO();
        pojo.email = email;
        pojo.password = password;
        user.user = pojo;

        call = Service.getApi().login(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d(TAG, response.toString() + "message in response " + response.message() + " code " + response.code());
                listener.onLoginModelSuccess(response);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                listener.onError(t.toString());
            }
        });

     //   CreatePost.newService(email, password, listener);

    }

    @Override
    public void cancelLogin() {
        call.cancel();
    }
}
