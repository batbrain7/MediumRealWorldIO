package com.example.mohitkumar.trialapp.core.EditArticles;

import com.example.mohitkumar.trialapp.data.comment.SingleArticle;
import com.example.mohitkumar.trialapp.data.writearticle.WriteArticle;

import retrofit2.Response;

public interface IEditArticleModel {

    void fetchArticle(String slug, IEditArticleModel.OnArticleFetchListener listener);

    void updateArticle(String slug, WriteArticle writeArticle, IEditArticleModel.OnArticleUpdateListener listener);

    void deleteArticle(String slug, IEditArticleModel.OnArticleDeleteListener listener);

    interface OnArticleFetchListener {
        void onArticleFetchSuccess(Response<SingleArticle> articleResponse);
        void onArticleFetchError(String error);
    }

    interface OnArticleUpdateListener {
        void onUpdateArticleSuccess(Response<SingleArticle> articleResponse);
        void onUpdateArticleError(String error);
    }

    interface OnArticleDeleteListener {
        void onDeleteArticleSuccess(Response<String> string);
        void onDeleteArticleError(String error);
    }

}
