package com.example.mohitkumar.trialapp.data;


import com.example.mohitkumar.trialapp.data.MainPage.GlobalFeedResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface API {

    @GET("/articles")
    Observable<GlobalFeedResponse> getGlobalFeed();
}
