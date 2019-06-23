package com.example.mohitkumar.trialapp;

import com.example.mohitkumar.trialapp.data.loginsignup.LUser;
import com.example.mohitkumar.trialapp.data.loginsignup.LoginPOJO;
import com.example.mohitkumar.trialapp.data.loginsignup.LoginSignUpResponse;
import com.example.mohitkumar.trialapp.data.loginsignup.User;
import com.example.mohitkumar.trialapp.network.API;
import com.example.mohitkumar.trialapp.network.Service;

import org.junit.Test;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

import static org.junit.Assert.assertTrue;

public class LoginPresenterTest {

    @Test
    public void loginSuccess() {

        API apiEndpoints = Service.getApi();

        LUser user = new LUser();
        LoginPOJO loginPOJO = new LoginPOJO();
        loginPOJO.email = "kumar.mohit983@gmail.com";
        loginPOJO.password = "mohit983.";
        user.user = loginPOJO;
        Call<User> call = apiEndpoints.login(user);

        try {
            //Magic is here at .execute() instead of .enqueue()
            Response<User> response = call.execute();
            User userResponse = response.body();
            LoginSignUpResponse loginSignUpResponse = userResponse.user;

            assertTrue(response.isSuccessful() && userResponse != null  && loginSignUpResponse.email != null &&
                    loginSignUpResponse.token != null && loginSignUpResponse.username != null);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
