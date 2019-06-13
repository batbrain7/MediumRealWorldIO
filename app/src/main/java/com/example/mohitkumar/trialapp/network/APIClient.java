package com.example.mohitkumar.trialapp.network;

import com.example.mohitkumar.trialapp.data.comment.SingleArticle;
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
    public Observable<GlobalFeedResponse> getYourFeed(long page, long offset) {
        api = AuthService.getApi();
        Observable<GlobalFeedResponse> responseObservable =  api.getYourFeed(page, offset).
                subscribeOn(Schedulers.io()).
                observeOn(mainThread());

        return responseObservable;
    }

    @Override
    public Observable<SingleArticle> postFavorite(String slug) {
        api = AuthService.getApi();
        return api.favoriteArticle(slug)
                .subscribeOn(Schedulers.io())
                .observeOn(mainThread());
    }

    @Override
    public Observable<GlobalFeedResponse> getMyFeed(long page, long offset, String author) {
        api = AuthService.getApi();
        return api.getMyFeed(page, offset, author)
                .subscribeOn(Schedulers.io())
                .observeOn(mainThread());
    }

    @Override
    public Observable<GlobalFeedResponse> getFavoriteFeed(long page, long offset, String favorite) {
        api = AuthService.getApi();
        return api.getFavoriteFeed(page, offset, favorite)
                .subscribeOn(Schedulers.io())
                .observeOn(mainThread());
    }
}
