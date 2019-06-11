package com.example.mohitkumar.trialapp.core.comment;

import com.example.mohitkumar.trialapp.data.CreateService;
import com.example.mohitkumar.trialapp.data.comment.SingleArticle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentModel implements ICommentModel {

    Call<SingleArticle> call;
    @Override
    public void fetchArticle(String slug, OnArticleFetchFinishedListener listener) {
        call = CreateService.getApi().getSingleArticle(slug);

        call.enqueue(new Callback<SingleArticle>() {
            @Override
            public void onResponse(Call<SingleArticle> call, Response<SingleArticle> response) {
                listener.onArticleFetchSuccess(response);
            }

            @Override
            public void onFailure(Call<SingleArticle> call, Throwable t) {

            }
        });
    }

    @Override
    public void fetchComments(String slug, OnCommentFetchFinishListener listener) {

    }

    @Override
    public void cancelFetch() {

    }
}
