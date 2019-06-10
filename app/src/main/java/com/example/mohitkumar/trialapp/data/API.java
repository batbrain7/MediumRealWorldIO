package com.example.mohitkumar.trialapp.data;


import com.example.mohitkumar.trialapp.data.Login.LoginResponse;
import com.example.mohitkumar.trialapp.data.MainPage.GlobalFeedResponse;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface API {

    @GET("/articles")
    Observable<GlobalFeedResponse> getGlobalFeed(@Query("limit") long limit, @Query("offset") long offset);

    @POST("/login")
    Call<LoginResponse> login(@Query("email") String email, @Query("password") String password);
}
