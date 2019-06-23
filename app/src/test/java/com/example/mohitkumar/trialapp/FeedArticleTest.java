package com.example.mohitkumar.trialapp;

import com.example.mohitkumar.trialapp.data.mainpage.Article;
import com.example.mohitkumar.trialapp.data.mainpage.FeedResponse;
import com.example.mohitkumar.trialapp.network.API;
import com.example.mohitkumar.trialapp.network.APIClient;
import com.example.mohitkumar.trialapp.network.APIService;
import com.example.mohitkumar.trialapp.network.Service;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;


public class FeedArticleTest {

    @Test
    public void getGlobalFeedArticles() {

        APIService apiService = new APIClient();
        apiService.getGlobalFeed(20, 20)
                .subscribe(feedResponse -> {
                    Assert.assertNotNull(feedResponse.getArticles());
                    Assert.assertEquals(20, feedResponse.getArticlesCount());
                });
    }
}
