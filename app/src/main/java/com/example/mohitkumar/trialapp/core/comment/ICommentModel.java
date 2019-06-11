package com.example.mohitkumar.trialapp.core.comment;


import com.example.mohitkumar.trialapp.data.comment.Comments;
import com.example.mohitkumar.trialapp.data.comment.SingleArticle;

import retrofit2.Response;

public interface ICommentModel {

    interface OnArticleFetchFinishedListener {
        void onArticleError(String error);
        void onArticleFetchSuccess(Response<SingleArticle> response);
    }

    void fetchArticle(String slug, ICommentModel.OnArticleFetchFinishedListener listener);

    void fetchComments(String slug, ICommentModel.OnCommentFetchFinishListener listener);

    interface OnCommentFetchFinishListener {
        void onCommentError(String error);
        void onCommentsFetchSuccess(Response<Comments> response);
    }

    void cancelFetch();
}
