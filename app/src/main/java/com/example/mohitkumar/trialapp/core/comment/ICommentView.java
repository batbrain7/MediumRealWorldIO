package com.example.mohitkumar.trialapp.core.comment;

import com.example.mohitkumar.trialapp.data.comment.SingleArticle;
import com.example.mohitkumar.trialapp.data.mainpage.Article;
import com.example.mohitkumar.trialapp.data.comment.Comment;
import com.example.mohitkumar.trialapp.data.settings.ProfileResponse;

import java.util.List;

public interface ICommentView {

    void onArticleFetchSuccess(Article article);

    void onArticleFetchError(String message);

    void onCommentsFetchSuccess(List<Comment> comment);

    void onCommentsFetchError(String message);

    void onCommentPostSuccess(String message);

    void onCommentPostError(String message);

    void onFavoriteUnfavoriteSuccess(SingleArticle article);

    void onFavoriteUnfavoriteError(String erro);

    void onFollowUnFollowSuccess(ProfileResponse response);

    void onFollowUnFollowError(String error);

    void onDeleteComment();

    void displayProgress();
}
