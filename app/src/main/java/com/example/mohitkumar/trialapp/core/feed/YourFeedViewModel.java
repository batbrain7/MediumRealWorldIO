package com.example.mohitkumar.trialapp.core.feed;


import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.mohitkumar.trialapp.network.APIClient;
import com.example.mohitkumar.trialapp.network.APIService;
import com.example.mohitkumar.trialapp.data.mainpage.Article;
import com.example.mohitkumar.trialapp.data.mainpage.FeedResponse;

import java.util.List;

public class YourFeedViewModel extends ViewModel {

    private APIService service;
    private List<Article> articleList;
    private final MutableLiveData<Integer> progress = new MutableLiveData<>();
    private final MutableLiveData<FeedResponse> articles = new MutableLiveData<>();

    public YourFeedViewModel() {
        service = new APIClient();
    }

    public MutableLiveData<FeedResponse> getArticlesList(long offset) {
        service.getYourFeed(20, offset)
                .doOnSubscribe(disposable -> progress.setValue(0))
                .doFinally(() -> progress.setValue(8))
                .subscribe(status -> {
                    articles.setValue(status);
                }, error -> {
                    Log.i("HeadlineFragment.class", "onStart: " + error);
                });
        return articles;
    }

    public MutableLiveData<Integer> getProgress() {
        return progress;
    }

    public MutableLiveData<FeedResponse> getMyFeedArticles(long offset, String author) {
        service.getMyFeed(20, offset, author)
                .doOnSubscribe(disposable -> progress.setValue(0))
                .doFinally(() -> progress.setValue(8))
                .subscribe(globalFeedResponse -> {
                    articles.setValue(globalFeedResponse);
                }, error -> {
                    Log.i("GlobalViewModel.class", "onStart: " + error);
                });
        return articles;
    }
}
