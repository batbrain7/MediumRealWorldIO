package com.example.mohitkumar.trialapp.data;


import com.example.mohitkumar.trialapp.data.Login.LoginResponse;
import com.example.mohitkumar.trialapp.data.Login.User;
import com.example.mohitkumar.trialapp.data.MainPage.GlobalFeedResponse;

import org.json.JSONObject;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface API {

    @GET("articles")
    Observable<GlobalFeedResponse> getGlobalFeed(@Query("limit") long limit, @Query("offset") long offset);

    @POST("users/login")
    Call<String> login(@Body String object);
}
