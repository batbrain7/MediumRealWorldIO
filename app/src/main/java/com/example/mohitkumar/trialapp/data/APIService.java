package com.example.mohitkumar.trialapp.data;

import com.example.mohitkumar.trialapp.data.mainpage.GlobalFeedResponse;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;

public interface APIService {

    @NonNull
    Observable<GlobalFeedResponse> getGlobalFeed(long page, long offset);

    @NonNull
    Observable<GlobalFeedResponse> getMyFeed(long page, long offset);

}
