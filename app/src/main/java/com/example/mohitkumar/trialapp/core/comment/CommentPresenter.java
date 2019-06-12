package com.example.mohitkumar.trialapp.core.comment;

import android.util.Log;

import com.example.mohitkumar.trialapp.data.comment.Comment;
import com.example.mohitkumar.trialapp.data.comment.CommentResponse;
import com.example.mohitkumar.trialapp.data.comment.PostComment;
import com.example.mohitkumar.trialapp.data.comment.SingleArticle;
import com.example.mohitkumar.trialapp.data.settings.ProfileResponse;

import retrofit2.Response;

import static com.example.mohitkumar.trialapp.MainApplication.TAG;

public class CommentPresenter implements ICommentPresenter, ICommentModel.OnCommentFetchFinishListener,
                                    ICommentModel.OnArticleFetchFinishedListener, ICommentModel.OnCommentPostedListener,
                                            ICommentModel.OnFavoriteUnFavoriteListener, ICommentModel.OnFollowUnFollowListener {

    private ICommentModel model;
    private ICommentView commentView;

    CommentPresenter() {
        model = new CommentModel();
    }

    @Override
    public void onAttach(ICommentView commentView) {
        this.commentView = commentView;
    }

    @Override
    public void getArticleData(String slug) {
        model.fetchArticle(slug, (ICommentModel.OnArticleFetchFinishedListener) this);
        commentView.displayProgress();
    }

    @Override
    public void getComments(String slug) {
        model.fetchComments(slug, (ICommentModel.OnCommentFetchFinishListener) this);
        commentView.displayProgress();
    }

    @Override
    public void postComment(String slug, PostComment comment) {
        model.postComment(slug, comment, (ICommentModel.OnCommentPostedListener) this);
        commentView.displayProgress();
    }

    @Override
    public void favorite(String slug) {
        model.favoriteArticle(slug, (ICommentModel.OnFavoriteUnFavoriteListener) this);
        commentView.displayProgress();
    }

    @Override
    public void unFavorite(String slug) {
        model.unFavoriteArticle(slug, (ICommentModel.OnFavoriteUnFavoriteListener) this);
        commentView.displayProgress();
    }

    @Override
    public void follow(String username) {
        model.follow(username, this);
        commentView.displayProgress();
    }

    @Override
    public void unFollow(String username) {
        model.unFollow(username, this);
        commentView.displayProgress();
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
    public void onCommentsFetchSuccess(Response<CommentResponse> response) {
        if (response.body() == null) {
            Log.d(TAG, "Response Body is null, code : " + response.code());
        } else {
            Log.d(TAG, response.body().toString());
            CommentResponse commentResponse = response.body();
            commentView.onCommentsFetchSuccess(commentResponse.comments);
        }
    }

    @Override
    public void onCommentPostError(String error) {
        commentView.onCommentPostError(error);
    }

    @Override
    public void onCommentPostSuccess(Response<Comment> commentResponse) {

        Comment comment = commentResponse.body();
        commentView.onCommentPostSuccess(comment.toString());
    }

    @Override
    public void onFavoriteUnfFavoriteError(String error) {
        commentView.onFavoriteUnfavoriteError(error);
    }

    @Override
    public void onFavoriteUnfFavoriteSuccess(Response<SingleArticle> response) {

        SingleArticle singleArticle = response.body();
     //   Log.d(TAG, response.body().article.toString());
        commentView.onFavoriteUnfavoriteSuccess(singleArticle);
    }

    @Override
    public void onFollowUnFollowError(String error) {
        commentView.onFollowUnFollowError(error);
    }

    @Override
    public void onFollowUnFollowSuccess(Response<ProfileResponse> response) {
        ProfileResponse profileResponse = response.body();
        commentView.onFollowUnFollowSuccess(profileResponse);
    }
}
