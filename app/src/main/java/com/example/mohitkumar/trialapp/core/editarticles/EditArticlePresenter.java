package com.example.mohitkumar.trialapp.core.editarticles;

import android.util.Log;

import com.example.mohitkumar.trialapp.data.comment.SingleArticle;
import com.example.mohitkumar.trialapp.data.writearticle.WriteArticlePOJO;
import com.example.mohitkumar.trialapp.util.Utils;

import retrofit2.Response;

public class EditArticlePresenter implements IEditArticlePresenter, IEditArticleModel.OnArticleDeleteListener,
                                    IEditArticleModel.OnArticleUpdateListener, IEditArticleModel.OnArticleFetchListener{

    IEditArticleView articleView;
    IEditArticleModel model;

    public EditArticlePresenter() {
        model = new EditArticleModel();
    }


    @Override
    public void onAttach(IEditArticleView articleView) {
        this.articleView = articleView;
    }

    @Override
    public void fetchArticle(String slug) {
        model.fetchArticle(slug, this);
        articleView.displayProgress();
    }

    @Override
    public void updateArticle(String slug, WriteArticlePOJO writeArticlePOJO) {
        model.updateArticle(slug, writeArticlePOJO,this);
        articleView.displayProgress();
    }

    @Override
    public void deleteArticle(String slug) {
        model.deleteArticle(slug, this);
        articleView.displayProgress();
    }

    @Override
    public void onArticleFetchSuccess(Response<SingleArticle> articleResponse) {
        if (articleResponse.body() != null)
            articleView.onArticleFetchSuccess(articleResponse.body().article);
        else
            Log.d("TrialApp", "articleResponse is null");
    }

    @Override
    public void onArticleFetchError(String error) {
        articleView.onArticleFetchError(error);
    }

    @Override
    public void onUpdateArticleSuccess(Response<SingleArticle> articleResponse) {
        articleView.onArticleUpdateSuccess(articleResponse.body().article);
    }

    @Override
    public void onUpdateArticleError(String error) {
        articleView.onArticleUpdateError(error);
    }

    @Override
    public void onDeleteArticleSuccess(Response<String> string) {
        articleView.onArticleDeleteSuccess(string.body().toString());
    }

    @Override
    public void onDeleteArticleError(Throwable error) {
        if (Utils.hasNetwork()) {
            articleView.onArticleDeleteSuccess("Successfully deleted the article");
        } else {
            articleView.onArticleDeleteError(error.toString());
        }
    }
}
