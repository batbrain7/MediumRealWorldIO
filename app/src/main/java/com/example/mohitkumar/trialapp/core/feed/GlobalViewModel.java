package com.example.mohitkumar.trialapp.core.feed;

import android.annotation.SuppressLint;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.mohitkumar.trialapp.data.APIService;
import com.example.mohitkumar.trialapp.data.AuthService;
import com.example.mohitkumar.trialapp.data.comment.SingleArticle;
import com.example.mohitkumar.trialapp.data.mainpage.Articles;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GlobalViewModel extends ViewModel {

    private APIService service;
    private List<Articles> articlesList;
    private final MutableLiveData<Integer> progress = new MutableLiveData<>();
    private final MutableLiveData<List<Articles>> articles = new MutableLiveData<>();
    private final MutableLiveData<SingleArticle> singleArticleMutableLiveData = new MutableLiveData<>();

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
                    Log.i("GlobalViewModel.class", "onStart: " + error);
                });
        return articles;
    }

    public MutableLiveData<Integer> getProgress() {
        return progress;
    }

    @SuppressLint("CheckResult")
    public MutableLiveData<SingleArticle> favoriteArticle(String slug) {
        service.postFavorite(slug).subscribe(singleArticle -> {
            singleArticleMutableLiveData.postValue(singleArticle);
        }, error -> {
            Log.i("GlobalViewModel.class", "onStart: " + error);
        });
        return singleArticleMutableLiveData;
    }
}
