package com.example.mohitkumar.trialapp.core.comment;

import com.example.mohitkumar.trialapp.data.MainPage.Articles;

public interface ICommentView {

    void onArticleFetchSuccess(Articles article);

    void onArticleFetchError(String message);

    void onCommentsFetchSuccess();

    void onCommentsFetchError(String message);
}
