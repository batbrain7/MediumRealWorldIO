package com.example.mohitkumar.trialapp.core.writearticle;

import android.util.Log;
import static com.example.mohitkumar.trialapp.MainApplication.TAG;

import com.example.mohitkumar.trialapp.data.comment.SingleArticle;
import com.example.mohitkumar.trialapp.data.writearticle.WriteArticle;

import retrofit2.Response;

public class WriteArticlePresenter implements IWriteArticlePresenter, IWriteArticleModel.OnArticlePostListener {

    IWriteArticleModel model;
    IWriteArticleView view;

    public WriteArticlePresenter() {
        this.model = new WriteArticleModel() ;
    }

    @Override
    public void onAttach(IWriteArticleView articleView) {
        view = articleView;
    }

    @Override
    public void postArticle(WriteArticle article) {
        model.postArticle(article, (IWriteArticleModel.OnArticlePostListener) this);
    }

    @Override
    public void onPostArticleError(String error) {
        view.onArticlePostError(error);
    }

    @Override
    public void onArticlePostSuccess(Response<SingleArticle> response) {

        Log.d(TAG,"Inside presenter : " + response.body().toString());
        view.onArticlePostSuccess("Response Successful");

    }
}
