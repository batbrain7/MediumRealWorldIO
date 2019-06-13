package com.example.mohitkumar.trialapp.core.EditArticles;

import com.example.mohitkumar.trialapp.data.writearticle.WriteArticle;

public interface IEditArticlePresenter {

    void onAttach(IEditArticleView articleView);

    void fetchArticle(String slug);

    void updateArticle(String slug, WriteArticle writeArticle);

    void deleteArticle(String slug);
}
