package com.example.mohitkumar.trialapp.core.comment;

import com.example.mohitkumar.trialapp.data.CreateAuthService;
import com.example.mohitkumar.trialapp.data.CreateService;
import com.example.mohitkumar.trialapp.data.comment.Comment;
import com.example.mohitkumar.trialapp.data.comment.Comments;
import com.example.mohitkumar.trialapp.data.comment.PostComment;
import com.example.mohitkumar.trialapp.data.comment.SingleArticle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentModel implements ICommentModel {

    Call<SingleArticle> call;

    Call<Comments> commentsCall;

    Call<Comment> commentPostCall;

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
                listener.onArticleError(t.toString());
            }
        });
    }

    @Override
    public void fetchComments(String slug, OnCommentFetchFinishListener listener) {
        commentsCall = CreateService.getApi().getCommentsArticle(slug);

        commentsCall.enqueue(new Callback<Comments>() {
            @Override
            public void onResponse(Call<Comments> call, Response<Comments> response) {
                listener.onCommentsFetchSuccess(response);
            }

            @Override
            public void onFailure(Call<Comments> call, Throwable t) {
                listener.onCommentError(t.toString());
            }
        });
    }

    @Override
    public void postComment(String slug, PostComment comment, OnCommentPostedListener listener) {
        commentPostCall = CreateAuthService.getApi().postComment(slug, comment);

        commentPostCall.enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(Call<Comment> call, Response<Comment> response) {
                listener.onCommentPostSuccess(response);
            }

            @Override
            public void onFailure(Call<Comment> call, Throwable t) {
                listener.onCommentPostError(t.toString());
            }
        });
    }

    @Override
    public void cancelFetch() {

    }
}
