package com.example.mohitkumar.trialapp.core.feed;

import android.annotation.SuppressLint;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.mohitkumar.trialapp.network.APIClient;
import com.example.mohitkumar.trialapp.network.APIService;
import com.example.mohitkumar.trialapp.data.comment.SingleArticle;
import com.example.mohitkumar.trialapp.data.mainpage.Article;
import com.example.mohitkumar.trialapp.data.mainpage.GlobalFeedResponse;

import java.util.List;

public class GlobalViewModel extends ViewModel {

    private APIService service;
    private List<Article> articleList;
    private final MutableLiveData<Integer> progress = new MutableLiveData<>();
    private final MutableLiveData<List<Article>> articles = new MutableLiveData<>();
    private final MutableLiveData<SingleArticle> singleArticleMutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<GlobalFeedResponse> globalFeedResponseMutableLiveData = new MutableLiveData<>();

    public GlobalViewModel() {
        service = new APIClient();
    }

    public MutableLiveData<List<Article>> getArticlesList(long offset) {
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

    public MutableLiveData<GlobalFeedResponse> getFeedFavoriteArticles(long offset, String favorite) {
        service.getFavoriteFeed(20, offset, favorite)
                .doOnSubscribe(disposable -> progress.setValue(0))
                .doFinally(() -> progress.setValue(8))
                .subscribe(globalFeedResponse -> {
                    globalFeedResponseMutableLiveData.setValue(globalFeedResponse);
                }, error -> {
                    Log.i("GlobalViewModel.class", "onStart: " + error);
                });
        return globalFeedResponseMutableLiveData;
    }
}
