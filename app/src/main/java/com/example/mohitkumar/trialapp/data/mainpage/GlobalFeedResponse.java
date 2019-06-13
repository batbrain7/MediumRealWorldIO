package com.example.mohitkumar.trialapp.data.mainpage;

import java.util.List;

public class GlobalFeedResponse {

    List<Article> articles;
    long articlesCount;

    public List<Article> getArticles() {
        return articles;
    }

    public long getArticlesCount() {
        return articlesCount;
    }

    public GlobalFeedResponse(List<Article> articles, long articlesCount) {
        this.articles = articles;
        this.articlesCount = articlesCount;
    }

    public void setArticles(List<Article> articles) {

        this.articles = articles;
    }

    public void setArticlesCount(long articlesCount) {
        this.articlesCount = articlesCount;
    }
}
