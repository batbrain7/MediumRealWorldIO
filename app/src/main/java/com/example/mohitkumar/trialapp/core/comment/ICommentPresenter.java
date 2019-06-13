package com.example.mohitkumar.trialapp.core.comment;

import com.example.mohitkumar.trialapp.data.comment.PostComment;

public interface ICommentPresenter {

    void onAttach(ICommentView commentView);

    void getArticleData(String slug);

    void getComments(String slug);

    void postComment(String slug, PostComment comment);

    void favorite(String slug);

    void unFavorite(String slug);

    void follow(String username);

    void unFollow(String username);

    void deleteComment(String slug, int id);
}
