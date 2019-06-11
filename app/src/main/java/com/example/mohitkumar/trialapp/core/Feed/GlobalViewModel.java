package com.example.mohitkumar.trialapp.core.Feed;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.mohitkumar.trialapp.data.APIService;
import com.example.mohitkumar.trialapp.data.MainPage.Articles;

import java.util.List;

public class GlobalViewModel extends ViewModel {

    private APIService service;
    private List<Articles> articlesList;
    private final MutableLiveData<Integer> progress = new MutableLiveData<>();
    private final MutableLiveData<List<Articles>> articles = new MutableLiveData<>();

    public GlobalViewModel() {
        service = new APIClient();
    }

    public MutableLiveData<List<Articles>> getArticlesList(long offset) {
        service.getGlobalFeed(20, offset)
                .doOnSubscribe(disposable -> progress.setValue(0))
                .doFinally(() -> progress.setValue(8))
                .subscribe(status -> {
                    articles.setValue(status.getArticles());
                }, error -> {
                    Log.i("HeadlineFragment.class", "onStart: " + error);
                });
        return articles;
    }

    public MutableLiveData<Integer> getProgress() {
        return progress;
    }
}
