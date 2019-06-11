package com.example.mohitkumar.trialapp.core.comment;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.mohitkumar.trialapp.R;
import static com.example.mohitkumar.trialapp.MainApplication.TAG;

import com.example.mohitkumar.trialapp.Util.Utils;
import com.example.mohitkumar.trialapp.data.MainPage.Articles;
import com.example.mohitkumar.trialapp.data.comment.Comment;
import com.example.mohitkumar.trialapp.data.comment.CommentBody;
import com.example.mohitkumar.trialapp.data.comment.PostComment;
import com.example.mohitkumar.trialapp.databinding.CommentActivityBinding;

import java.util.List;

public class CommentActivity extends AppCompatActivity implements ICommentView {

    CommentActivityBinding activityBinding;
    ICommentPresenter presenter;
    CommentRecyclerAdapter adapter;
    String extra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_comment);
        extra = getIntent().getStringExtra("slug");
        presenter = new CommentPresenter();
        presenter.onAttach(this);
        if (extra != null) {
            loadArticle(extra);
        }
    }

    private void loadArticle(String slug) {
        presenter.getArticleData(slug);
    }

    private void loadComments(String slug) {
        presenter.getComments(slug);
    }

    @Override
    public void onArticleFetchSuccess(Articles article) {
        Toast.makeText(this, "Fetched the article", Toast.LENGTH_SHORT).show();
        activityBinding.titleText.setText(article.title);
        activityBinding.articleBody.setText(article.body);
        activityBinding.userArticle.setText(article.author.username);
        activityBinding.date.setText(article.createdAt);
        activityBinding.favoriteCount.setText(Integer.toString(article.favoritesCount));
        Glide.with(this)
                .asBitmap()
                .load(article.author.image)
                .listener(new RequestListener<Bitmap>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                       // Toast.makeText(this, "ERROR in image loading", Toast.LENGTH_LONG).show();
                        Log.d(TAG, "Error in loading the image");
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource,
                                                   boolean isFirstResource) {
                        activityBinding.imageArticle.setImageBitmap(resource);
                        return true;
                    }
                }).submit();
        if (Utils.isLoggedIn()) {
            activityBinding.commentField.setVisibility(View.VISIBLE);
            activityBinding.postComment.setVisibility(View.VISIBLE);

            loadComments(extra);
        }
    }

    @Override
    public void onArticleFetchError(String message) {
        Toast.makeText(this, "Unable to load article " + message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCommentsFetchSuccess(List<Comment> comments) {
        adapter = new CommentRecyclerAdapter(this, comments);
        activityBinding.recyclerComments.setLayoutManager(new LinearLayoutManager(this));
        activityBinding.recyclerComments.setAdapter(adapter);
    }

    @Override
    public void onCommentsFetchError(String message) {
        Toast.makeText(this, "Unable to load comments " + message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCommentPostSuccess(String message) {
        Toast.makeText(this, "Comment Posted",Toast.LENGTH_LONG).show();
        activityBinding.commentField.setText("");
        recreate();
    }

    @Override
    public void onCommentPostError(String message) {
        Toast.makeText(this, "Unable to post your comment, error : " + message, Toast.LENGTH_LONG).show();
    }

    public void postComment(View view) {
        if(!TextUtils.isEmpty(activityBinding.commentField.getText().toString())) {
            CommentBody commentBody = new CommentBody();
            commentBody.body = activityBinding.commentField.getText().toString();
            PostComment post = new PostComment();
            post.comment = commentBody;
            presenter.postComment(extra, post);
        }
    }
}
