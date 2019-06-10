package com.example.mohitkumar.trialapp.core.Feed;

import android.util.Log;

import com.example.mohitkumar.trialapp.data.API;
import com.example.mohitkumar.trialapp.data.APIService;
import com.example.mohitkumar.trialapp.data.CreateService;
import com.example.mohitkumar.trialapp.data.MainPage.GlobalFeedResponse;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.example.mohitkumar.trialapp.MainApplication.TAG;
import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;

public class APIClient implements APIService {

    API api;

    public APIClient() {
        api = CreateService.getApi();
    }

    @Override
    public Observable<GlobalFeedResponse> getGlobalFeed(long page, long offset) {
        Observable<GlobalFeedResponse> responseObservable =  api.getGlobalFeed(page, offset).
                subscribeOn(Schedulers.io()).
                observeOn(mainThread());


        return responseObservable;
    }
}
