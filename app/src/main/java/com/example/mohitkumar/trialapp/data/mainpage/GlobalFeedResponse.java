package com.example.mohitkumar.trialapp.data.mainpage;

import java.util.List;

public class GlobalFeedResponse {

    List<Articles> articles;
    long articlesCount;

    public List<Articles> getArticles() {
        return articles;
    }

    public long getArticlesCount() {
        return articlesCount;
    }

    public GlobalFeedResponse(List<Articles> articles, long articlesCount) {
        this.articles = articles;
        this.articlesCount = articlesCount;
    }

    public void setArticles(List<Articles> articles) {

        this.articles = articles;
    }

    public void setArticlesCount(long articlesCount) {
        this.articlesCount = articlesCount;
    }
}
