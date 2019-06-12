package com.example.mohitkumar.trialapp.core.comment;

import com.example.mohitkumar.trialapp.data.comment.SingleArticle;
import com.example.mohitkumar.trialapp.data.mainpage.Articles;
import com.example.mohitkumar.trialapp.data.comment.Comment;

import java.util.List;

public interface ICommentView {

    void onArticleFetchSuccess(Articles article);

    void onArticleFetchError(String message);

    void onCommentsFetchSuccess(List<Comment> comment);

    void onCommentsFetchError(String message);

    void onCommentPostSuccess(String message);

    void onCommentPostError(String message);

    void onFavoriteUnfavoriteSuccess(SingleArticle article);

    void onFavoriteUnfavoriteError(String erro);

    void displayProgress();
}
