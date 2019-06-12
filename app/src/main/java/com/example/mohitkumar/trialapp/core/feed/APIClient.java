package com.example.mohitkumar.trialapp.core.feed;

import com.example.mohitkumar.trialapp.data.API;
import com.example.mohitkumar.trialapp.data.APIService;
import com.example.mohitkumar.trialapp.data.AuthService;
import com.example.mohitkumar.trialapp.data.Service;
import com.example.mohitkumar.trialapp.data.mainpage.GlobalFeedResponse;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;

public class APIClient implements APIService {

    API api;
    public int NO_AUTH = 0;
    public int AUTH = 1;

    public APIClient() {
    }

    @Override
    public Observable<GlobalFeedResponse> getGlobalFeed(long page, long offset) {
        api = Service.getApi();

        Observable<GlobalFeedResponse> responseObservable =  api.getGlobalFeed(page, offset).
                subscribeOn(Schedulers.io()).
                observeOn(mainThread());


        return responseObservable;
    }

    @Override
    public Observable<GlobalFeedResponse> getMyFeed(long page, long offset) {
        api = AuthService.getApi();
        Observable<GlobalFeedResponse> responseObservable =  api.getMyFeed(page, offset).
                subscribeOn(Schedulers.io()).
                observeOn(mainThread());

        return responseObservable;
    }
}
