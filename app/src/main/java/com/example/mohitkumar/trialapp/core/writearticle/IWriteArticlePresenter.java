package com.example.mohitkumar.trialapp.core.writearticle;

import com.example.mohitkumar.trialapp.data.writearticle.WriteArticlePOJO;

public interface IWriteArticlePresenter {
    void onAttach(IWriteArticleView articleView);

    void postArticle(WriteArticlePOJO article);
}
