package com.example.mohitkumar.trialapp.network;

import com.example.mohitkumar.trialapp.data.comment.SingleArticle;
import com.example.mohitkumar.trialapp.data.mainpage.GlobalFeedResponse;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import retrofit2.Call;

public interface APIService {

    @NonNull
    Observable<GlobalFeedResponse> getGlobalFeed(long page, long offset);

    @NonNull
    Observable<GlobalFeedResponse> getYourFeed(long page, long offset);

    @NonNull
    Observable<SingleArticle> postFavorite(String slug);

    @NonNull
    Observable<GlobalFeedResponse> getMyFeed(long page, long offset, String author);

    @NonNull
    Observable<GlobalFeedResponse> getFavoriteFeed(long page, long offset, String favorite);
}
