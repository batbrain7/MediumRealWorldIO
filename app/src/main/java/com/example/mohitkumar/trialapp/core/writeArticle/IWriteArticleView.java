package com.example.mohitkumar.trialapp.core.writeArticle;

public interface IWriteArticleView {

    void onArticlePostSuccess(String result);

    void onArticlePostError(String message);
}
