package com.example.mohitkumar.trialapp.core.writeArticle;

import com.example.mohitkumar.trialapp.core.PersonalActivity;
import com.example.mohitkumar.trialapp.data.CreateAuthService;
import com.example.mohitkumar.trialapp.data.comment.SingleArticle;
import com.example.mohitkumar.trialapp.data.writearticle.WriteArticle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WriteArticleModel implements IWriteArticleModel {

    Call<SingleArticle> call;

    @Override
    public void postArticle(WriteArticle article, OnArticlePostListener listener) {
        call = CreateAuthService.getApi().postArticle(article);

        call.enqueue(new Callback<SingleArticle>() {
            @Override
            public void onResponse(Call<SingleArticle> call, Response<SingleArticle> response) {
                listener.onArticlePostSuccess(response);
            }

            @Override
            public void onFailure(Call<SingleArticle> call, Throwable t) {
                listener.onPostArticleError(t.toString());
            }
        });
    }

    @Override
    public void cancelPostArticle() {
        call.cancel();
    }
}
