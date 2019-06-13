package com.example.mohitkumar.trialapp.core.editarticles;

import com.example.mohitkumar.trialapp.data.writearticle.WriteArticlePOJO;

public interface IEditArticlePresenter {

    void onAttach(IEditArticleView articleView);

    void fetchArticle(String slug);

    void updateArticle(String slug, WriteArticlePOJO writeArticlePOJO);

    void deleteArticle(String slug);
}
