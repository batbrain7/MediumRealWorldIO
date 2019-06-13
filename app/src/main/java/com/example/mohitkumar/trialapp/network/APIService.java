package com.example.mohitkumar.trialapp.network;

import com.example.mohitkumar.trialapp.data.comment.SingleArticle;
import com.example.mohitkumar.trialapp.data.mainpage.FeedResponse;
import com.example.mohitkumar.trialapp.data.tags.TagsResponse;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;

public interface APIService {

    @NonNull
    Observable<FeedResponse> getGlobalFeed(long page, long offset);

    @NonNull
    Observable<FeedResponse> getYourFeed(long page, long offset);

    @NonNull
    Observable<SingleArticle> postFavorite(String slug);

    @NonNull
    Observable<FeedResponse> getMyFeed(long page, long offset, String author);

    @NonNull
    Observable<FeedResponse> getFavoriteFeed(long page, long offset, String favorite);

    @NonNull
    Observable<TagsResponse> getTags();

    @NonNull
    Observable<FeedResponse> getTagFeed(long page, long offset, String tag);
}
