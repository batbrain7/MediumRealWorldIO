package com.example.mohitkumar.trialapp.core.writearticle;

import com.example.mohitkumar.trialapp.data.writearticle.WriteArticle;

public interface IWriteArticlePresenter {
    void onAttach(IWriteArticleView articleView);

    void postArticle(WriteArticle article);
}
