package com.example.mohitkumar.trialapp.core.comment;

import android.util.Log;

import com.example.mohitkumar.trialapp.data.MainPage.Articles;
import com.example.mohitkumar.trialapp.data.comment.Comments;
import com.example.mohitkumar.trialapp.data.comment.SingleArticle;

import retrofit2.Response;

import static com.example.mohitkumar.trialapp.MainApplication.TAG;

public class CommentPresenter implements ICommentPresenter, ICommentModel.OnCommentFetchFinishListener,
                                    ICommentModel.OnArticleFetchFinishedListener {

    private ICommentModel model;
    private ICommentView commentView;

    CommentPresenter() {
        model = new CommentModel();
    }

    @Override
    public void onAttach(ICommentView commentView) {
        this.commentView  = commentView;
    }

    @Override
    public void getArticleData(String slug) {
        model.fetchArticle(slug, (ICommentModel.OnArticleFetchFinishedListener)this);
    }

    @Override
    public void getComments(String slug) {
        model.fetchComments(slug, (ICommentModel.OnCommentFetchFinishListener)this);
    }

    @Override
    public void onArticleError(String error) {
        commentView.onArticleFetchError(error);
    }

    @Override
    public void onArticleFetchSuccess(Response<SingleArticle> response) {
        if (response.body() == null) {
            Log.d(TAG, "Response Body is null, code : " + response.code());
        } else {
            Log.d(TAG, response.body().toString());
            SingleArticle article = response.body();
            commentView.onArticleFetchSuccess(article.article);
        }

    }

    @Override
    public void onCommentError(String error) {
        commentView.onCommentsFetchError(error);
    }

    @Override
    public void onCommentsFetchSuccess(Response<Comments> response) {
        if (response.body() == null) {
            Log.d(TAG, "Response Body is null, code : " + response.code());
        } else {
            Log.d(TAG, response.body().toString());
            Comments comments = response.body();
            commentView.onCommentsFetchSuccess(comments.comments);
        }
    }
}
