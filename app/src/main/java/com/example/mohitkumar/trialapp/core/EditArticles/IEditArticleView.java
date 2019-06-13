package com.example.mohitkumar.trialapp.core.EditArticles;

import com.example.mohitkumar.trialapp.data.mainpage.Article;

public interface IEditArticleView {

    void onArticleFetchSuccess(Article article);

    void onArticleFetchError(String error);

    void onArticleUpdateSuccess(Article article);

    void onArticleUpdateError(String error);
}
