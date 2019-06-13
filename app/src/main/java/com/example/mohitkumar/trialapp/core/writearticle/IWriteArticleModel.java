package com.example.mohitkumar.trialapp.core.writearticle;

import com.example.mohitkumar.trialapp.data.comment.SingleArticle;
import com.example.mohitkumar.trialapp.data.writearticle.WriteArticlePOJO;

import retrofit2.Response;

public interface IWriteArticleModel {

    void postArticle(WriteArticlePOJO article, IWriteArticleModel.OnArticlePostListener listener);

    interface OnArticlePostListener {
        void onPostArticleError(String error);
        void onArticlePostSuccess(Response<SingleArticle> response);
    }
}
