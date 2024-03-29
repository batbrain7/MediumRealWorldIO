package com.example.mohitkumar.trialapp.core.comment;

import com.example.mohitkumar.trialapp.network.AuthService;
import com.example.mohitkumar.trialapp.network.Service;
import com.example.mohitkumar.trialapp.data.comment.Comment;
import com.example.mohitkumar.trialapp.data.comment.CommentResponse;
import com.example.mohitkumar.trialapp.data.comment.PostComment;
import com.example.mohitkumar.trialapp.data.comment.SingleArticle;
import com.example.mohitkumar.trialapp.data.settings.ProfileResponse;
import com.example.mohitkumar.trialapp.util.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentModel implements ICommentModel {

    private Call<SingleArticle> call;

    private Call<CommentResponse> commentFetchCall;

    private Call<Comment> commentPostCall;

    private Call<ProfileResponse> followCall;

    private Call<String> deleteCommentCall;

    @Override
    public void fetchArticle(String slug, OnArticleFetchFinishedListener listener) {
        if (Utils.isLoggedIn())
            call = AuthService.getApi().getSingleArticle(slug);
        else
            call = Service.getApi().getSingleArticle(slug);

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
        commentFetchCall = Service.getApi().getCommentsArticle(slug);

        commentFetchCall.enqueue(new Callback<CommentResponse>() {
            @Override
            public void onResponse(Call<CommentResponse> call, Response<CommentResponse> response) {
                listener.onCommentsFetchSuccess(response);
            }

            @Override
            public void onFailure(Call<CommentResponse> call, Throwable t) {
                listener.onCommentError(t.toString());
            }
        });
    }

    @Override
    public void postComment(String slug, PostComment comment, OnCommentPostedListener listener) {
        commentPostCall = AuthService.getApi().postComment(slug, comment);

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
    public void favoriteArticle(String slug, OnFavoriteUnFavoriteListener listener) {
        call = AuthService.getApi().favoriteArticleInCommentActivity(slug);

        call.enqueue(new Callback<SingleArticle>() {
            @Override
            public void onResponse(Call<SingleArticle> call, Response<SingleArticle> response) {
                listener.onFavoriteUnfFavoriteSuccess(response);
            }

            @Override
            public void onFailure(Call<SingleArticle> call, Throwable t) {
                listener.onFavoriteUnfFavoriteError(t.toString());
            }
        });
    }

    @Override
    public void unFavoriteArticle(String slug, OnFavoriteUnFavoriteListener listener) {
        call = AuthService.getApi().unFavoriteArticle(slug);

        call.enqueue(new Callback<SingleArticle>() {
            @Override
            public void onResponse(Call<SingleArticle> call, Response<SingleArticle> response) {
                listener.onFavoriteUnfFavoriteSuccess(response);
            }

            @Override
            public void onFailure(Call<SingleArticle> call, Throwable t) {
                listener.onFavoriteUnfFavoriteError(t.toString());
            }
        });
    }

    @Override
    public void follow(String username, OnFollowUnFollowListener listener) {
        followCall = AuthService.getApi().followUser(username);

        followCall.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                listener.onFollowUnFollowSuccess(response);
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                listener.onFollowUnFollowError(t.toString());
            }
        });
    }

    @Override
    public void unFollow(String username, OnFollowUnFollowListener listener) {
        followCall = AuthService.getApi().unFollowUser(username);

        followCall.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                listener.onFollowUnFollowSuccess(response);
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                listener.onFollowUnFollowError(t.toString());
            }
        });
    }

    @Override
    public void deleteComment(String slug, int id, OnDeleteCommentListener listener) {
        deleteCommentCall = AuthService.getApi().deleteComment(slug, id);

        deleteCommentCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                listener.onDeleteComment();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                listener.onDeleteComment();
            }
        });
    }
}
