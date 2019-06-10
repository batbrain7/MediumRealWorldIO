package com.example.mohitkumar.trialapp.core.Feed;

import com.example.mohitkumar.trialapp.data.API;
import com.example.mohitkumar.trialapp.data.APIService;
import com.example.mohitkumar.trialapp.data.CreateService;
import com.example.mohitkumar.trialapp.data.MainPage.GlobalFeedResponse;

import io.reactivex.Observable;

public class APIClient implements APIService {

    API api;

    public APIClient() {
        api = CreateService.getApi();
    }

    @Override
    public Observable<GlobalFeedResponse> getGlobalFeed(long page, long offset) {
        return null;
    }
}
