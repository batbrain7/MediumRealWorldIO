package com.example.mohitkumar.trialapp.core.comment;

public interface ICommentPresenter {

    void onAttach(ICommentView commentView);

    void getArticleData(String slug);

    void getComments(String slug);
}
