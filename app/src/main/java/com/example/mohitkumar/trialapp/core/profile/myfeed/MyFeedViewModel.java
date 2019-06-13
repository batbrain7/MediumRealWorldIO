package com.example.mohitkumar.trialapp.core.profile.myfeed;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.mohitkumar.trialapp.network.APIClient;
import com.example.mohitkumar.trialapp.network.APIService;
import com.example.mohitkumar.trialapp.data.mainpage.Article;
import com.example.mohitkumar.trialapp.data.mainpage.GlobalFeedResponse;

import java.util.List;

public class MyFeedViewModel extends ViewModel {

    private APIService service;
    private List<Article> articleList;
    private final MutableLiveData<Integer> progress = new MutableLiveData<>();
    private final MutableLiveData<GlobalFeedResponse> articles = new MutableLiveData<>();

    public MyFeedViewModel() {
        this.service = new APIClient();
    }

    public MutableLiveData<GlobalFeedResponse> getArticlesList(long offset) {
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
}
