package com.example.mohitkumar.trialapp.core.writeArticle;

import com.example.mohitkumar.trialapp.data.MainPage.Articles;

public interface IWriteArticleView {

    void onArticlePostSuccess(String result);

    void onArticlePostError(String message);
}
