package com.example.mohitkumar.trialapp.core.comment;


import com.example.mohitkumar.trialapp.data.comment.Comment;
import com.example.mohitkumar.trialapp.data.comment.CommentResponse;
import com.example.mohitkumar.trialapp.data.comment.PostComment;
import com.example.mohitkumar.trialapp.data.comment.SingleArticle;

import retrofit2.Response;

public interface ICommentModel {

    interface OnArticleFetchFinishedListener {
        void onArticleError(String error);
        void onArticleFetchSuccess(Response<SingleArticle> response);
    }

    void fetchArticle(String slug, ICommentModel.OnArticleFetchFinishedListener listener);

    void fetchComments(String slug, ICommentModel.OnCommentFetchFinishListener listener);

    void postComment(String slug, PostComment comment, ICommentModel.OnCommentPostedListener listener);

    void favoriteArticle(String slug, OnFavoriteUnFavoriteListener listener);

    void unFavoriteArticle(String slug, OnFavoriteUnFavoriteListener listener);

    interface OnCommentFetchFinishListener {
        void onCommentError(String error);
        void onCommentsFetchSuccess(Response<CommentResponse> response);
    }

    interface OnCommentPostedListener {
        void onCommentPostError(String error);
        void onCommentPostSuccess(Response<Comment> commentResponse);
    }

    interface OnFavoriteUnFavoriteListener {
        void onFavoriteUnfFavoriteError(String error);
        void onFavoriteUnfFavoriteSuccess(Response<SingleArticle> response);
    }

    void cancelFetch();
}
