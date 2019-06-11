package com.example.mohitkumar.trialapp.core.comment;

import com.example.mohitkumar.trialapp.data.MainPage.Articles;
import com.example.mohitkumar.trialapp.data.comment.Comment;
import com.example.mohitkumar.trialapp.data.comment.Comments;

import java.util.List;

public interface ICommentView {

    void onArticleFetchSuccess(Articles article);

    void onArticleFetchError(String message);

    void onCommentsFetchSuccess(List<Comment> comment);

    void onCommentsFetchError(String message);

    void onCommentPostSuccess(String message);

    void onCommentPostError(String message);
}
