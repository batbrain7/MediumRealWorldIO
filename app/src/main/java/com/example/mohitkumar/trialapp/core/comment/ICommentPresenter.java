package com.example.mohitkumar.trialapp.core.comment;

import com.example.mohitkumar.trialapp.data.comment.PostComment;

public interface ICommentPresenter {

    void onAttach(ICommentView commentView);

    void getArticleData(String slug);

    void getComments(String slug);

    void postComment(String slug, PostComment comment);

    
}
