package com.example.mohitkumar.trialapp.network;

import com.example.mohitkumar.trialapp.data.comment.SingleArticle;
import com.example.mohitkumar.trialapp.data.mainpage.FeedResponse;
import com.example.mohitkumar.trialapp.data.tags.TagsResponse;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;

public class APIClient implements APIService {

    private API api;

    @Override
    public Observable<FeedResponse> getGlobalFeed(long page, long offset) {
        api = Service.getApi();
        return api.getGlobalFeed(page, offset).
                subscribeOn(Schedulers.io()).
                observeOn(mainThread());
    }

    @Override
    public Observable<FeedResponse> getYourFeed(long page, long offset) {
        api = AuthService.getApi();
        return api.getYourFeed(page, offset).
                subscribeOn(Schedulers.io()).
                observeOn(mainThread());
    }

    @Override
    public Observable<SingleArticle> postFavorite(String slug) {
        api = AuthService.getApi();
        return api.favoriteArticle(slug)
                .subscribeOn(Schedulers.io())
                .observeOn(mainThread());
    }

    @Override
    public Observable<FeedResponse> getMyFeed(long page, long offset, String author) {
        api = AuthService.getApi();
        return api.getMyFeed(page, offset, author)
                .subscribeOn(Schedulers.io())
                .observeOn(mainThread());
    }

    @Override
    public Observable<FeedResponse> getFavoriteFeed(long page, long offset, String favorite) {
        api = AuthService.getApi();
        return api.getFavoriteFeed(page, offset, favorite)
                .subscribeOn(Schedulers.io())
                .observeOn(mainThread());
    }

    @Override
    public Observable<TagsResponse> getTags() {
        api = Service.getApi();
        return api.getTags()
                .subscribeOn(Schedulers.io())
                .observeOn(mainThread());
    }

    @Override
    public Observable<FeedResponse> getTagFeed(long page, long offset, String tag) {
        api = Service.getApi();
        return api.getTagFeed(20, offset, tag)
                .subscribeOn(Schedulers.io())
                .observeOn(mainThread());
    }
}
