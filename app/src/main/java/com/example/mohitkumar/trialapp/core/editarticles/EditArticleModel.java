package com.example.mohitkumar.trialapp.core.editarticles;

import android.util.Log;

import com.example.mohitkumar.trialapp.data.comment.SingleArticle;
import com.example.mohitkumar.trialapp.data.writearticle.WriteArticlePOJO;
import com.example.mohitkumar.trialapp.network.AuthService;
import com.example.mohitkumar.trialapp.network.Service;
import static com.example.mohitkumar.trialapp.MainApplication.TAG;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditArticleModel implements IEditArticleModel {

    Call<SingleArticle> call;

    Call<String> deleteCall;

    @Override
    public void fetchArticle(String slug, IEditArticleModel.OnArticleFetchListener listener) {
        call = Service.getApi().getSingleArticle(slug);

        call.enqueue(new Callback<SingleArticle>() {
            @Override
            public void onResponse(Call<SingleArticle> call, Response<SingleArticle> response) {
                listener.onArticleFetchSuccess(response);
            }

            @Override
            public void onFailure(Call<SingleArticle> call, Throwable t) {
                listener.onArticleFetchError(t.toString());
            }
        });
    }

    @Override
    public void updateArticle(String slug, WriteArticlePOJO article, OnArticleUpdateListener listener) {
        call = AuthService.getApi().upDateArticle(slug, article);

        call.enqueue(new Callback<SingleArticle>() {
            @Override
            public void onResponse(Call<SingleArticle> call, Response<SingleArticle> response) {
                listener.onUpdateArticleSuccess(response);
            }

            @Override
            public void onFailure(Call<SingleArticle> call, Throwable t) {
                listener.onUpdateArticleError(t.toString());
            }
        });
    }

    @Override
    public void deleteArticle(String slug, OnArticleDeleteListener listener) {
        deleteCall = AuthService.getApi().deleteArticle(slug);

        deleteCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                listener.onDeleteArticleSuccess(response);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d(TAG, "INSIDE DELETE ERROR");
                listener.onDeleteArticleError(t);
            }
        });
    }
}
