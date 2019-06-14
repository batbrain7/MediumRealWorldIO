package com.example.mohitkumar.trialapp.core.comment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
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

import com.example.mohitkumar.trialapp.core.tags.TagsActivity;
import com.example.mohitkumar.trialapp.data.comment.SingleArticle;
import com.example.mohitkumar.trialapp.data.settings.ProfileResponse;
import com.example.mohitkumar.trialapp.util.Utils;
import com.example.mohitkumar.trialapp.data.mainpage.Article;
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
    boolean back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_comment);
        extra = getIntent().getStringExtra("slug");
        back = getIntent().getBooleanExtra("act", false);
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
    public void onArticleFetchSuccess(Article article) {
        // Toast.makeText(this, "Fetched the article", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "Article :  " + article.favorited + " " + extra);
        setUI(article);
        if (Utils.isLoggedIn()) {
            activityBinding.commentField.setVisibility(View.VISIBLE);
            activityBinding.postComment.setVisibility(View.VISIBLE);
            loadComments(extra);
        }
        activityBinding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onArticleFetchError(String message) {
        Toast.makeText(this, "Unable to load article " + message, Toast.LENGTH_LONG).show();
        activityBinding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onCommentsFetchSuccess(List<Comment> comments) {
        adapter = new CommentRecyclerAdapter(extra, comments, presenter);
        activityBinding.recyclerComments.setLayoutManager(new LinearLayoutManager(this));
        activityBinding.recyclerComments.setAdapter(adapter);
        activityBinding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onCommentsFetchError(String message) {
        Toast.makeText(this, "Unable to load comments " + message, Toast.LENGTH_LONG).show();
        activityBinding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onCommentPostSuccess(String message) {
        Toast.makeText(this, "Comment Posted", Toast.LENGTH_LONG).show();
        activityBinding.commentField.setText("");
        activityBinding.progressBar.setVisibility(View.GONE);
        recreate();
    }

    @Override
    public void onCommentPostError(String message) {
        Toast.makeText(this, "Unable to post your comment, error : " + message, Toast.LENGTH_LONG).show();
        activityBinding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onFavoriteUnfavoriteSuccess(SingleArticle article) {
        // setButtonColors(isFavorited);
        activityBinding.progressBar.setVisibility(View.GONE);
        // Toast.makeText(this, "FAV" + article.article.favorited, Toast.LENGTH_LONG).show();
        Log.d(TAG, "VALUE : " + article.article.favorited);

        setUI(article.article);
        // loadArticle(extra);
        // Intent intent = new Intent(this, CommentActivity.class);
        //  intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
        //  intent.putExtra("slug", extra);
        //  startActivity(intent);
        // finish();
    }

    @Override
    public void onFavoriteUnfavoriteError(String error) {
        Toast.makeText(this, "ERRRORRR", Toast.LENGTH_LONG).show();
        activityBinding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onFollowUnFollowSuccess(ProfileResponse response) {
        activityBinding.progressBar.setVisibility(View.GONE);
        activityBinding.progressBar.setVisibility(View.GONE);
        setFollowButton(response.profile.following);
    }

    @Override
    public void onFollowUnFollowError(String error) {
        activityBinding.progressBar.setVisibility(View.GONE);
        Toast.makeText(this, "ERRRORRR", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDeleteComment() {
        Toast.makeText(this, "Successfully deleted the comment", Toast.LENGTH_LONG).show();
        activityBinding.progressBar.setVisibility(View.GONE);
        recreate();
    }

    @Override
    public void displayProgress() {
        activityBinding.progressBar.setVisibility(View.VISIBLE);
    }

    public void postComment(View view) {
        if (!TextUtils.isEmpty(activityBinding.commentField.getText().toString())) {
            CommentBody commentBody = new CommentBody();
            commentBody.body = activityBinding.commentField.getText().toString();
            PostComment post = new PostComment();
            post.comment = commentBody;
            presenter.postComment(extra, post);
        }
    }

    public void follow(View view) {
        if (Utils.isLoggedIn()) {
            if (activityBinding.followButton.getText().equals("FOLLOW")) {
                presenter.follow(activityBinding.userArticle.getText().toString());
            } else {
                presenter.unFollow(activityBinding.userArticle.getText().toString());
            }
        } else {
            Toast.makeText(this, "Please either login or sign up first", Toast.LENGTH_LONG).show();
        }
    }

    public void favorite(View view) {
        if (Utils.isLoggedIn()) {
            if (activityBinding.favoriteButton.getText().equals("FAVORITE")) {
                presenter.favorite(extra);
            } else {
                presenter.unFavorite(extra);
            }
        } else {
            Toast.makeText(this, "Please either login or sign up first", Toast.LENGTH_LONG).show();
        }
    }

    public void setFavoriteButtonColors(boolean isFavorite) {
        if (isFavorite) {
            activityBinding.favoriteButton.setText("UNFAVORITE");
            activityBinding.favoriteButton.setBackground(ContextCompat.getDrawable(this, R.drawable.round_shape_button_green));
            activityBinding.favoriteButton.setTextColor(ContextCompat.getColor(this, android.R.color.white));
        } else {
            activityBinding.favoriteButton.setText("FAVORITE");
            activityBinding.favoriteButton.setBackground(ContextCompat.getDrawable(this, R.drawable.round_shape_button_white));
            activityBinding.favoriteButton.setTextColor(ContextCompat.getColor(this, android.R.color.holo_green_dark));
        }
    }

    public void setFollowButton(boolean isFollowing) {
        if (isFollowing) {
            activityBinding.followButton.setText("UNFOLLOW");
        } else {
            activityBinding.followButton.setText("FOLLOW");
        }
    }

    public void setUI(Article article) {
        activityBinding.titleText.setText(article.title);
        activityBinding.articleBody.setText(article.body);
        activityBinding.userArticle.setText(article.author.username);
        activityBinding.date.setText(article.createdAt);
        activityBinding.favoriteCount.setText(Integer.toString(article.favoritesCount));
        setFavoriteButtonColors(article.favorited);
        setFollowButton(article.author.following);
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

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (back) {
            Intent intent = new Intent(this, TagsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
    }
}
