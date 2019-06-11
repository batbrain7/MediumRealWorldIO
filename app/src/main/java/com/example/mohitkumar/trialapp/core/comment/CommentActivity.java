package com.example.mohitkumar.trialapp.core.comment;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.example.mohitkumar.trialapp.core.Login.ILoginPresenter;
import com.example.mohitkumar.trialapp.core.Login.LoginPresenter;
import com.example.mohitkumar.trialapp.data.MainPage.Articles;
import com.example.mohitkumar.trialapp.databinding.CommentActivityBinding;

public class CommentActivity extends AppCompatActivity implements ICommentView {

    CommentActivityBinding activityBinding;
    ICommentPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_comment);
        String extra = getIntent().getStringExtra("slug");
        presenter = new CommentPresenter();
        presenter.onAttach(this);
        if (extra != null) {
            loadArticle(extra);
        }
    }

    private void loadArticle(String slug) {
        presenter.getArticleData(slug);
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
        }

    }

    @Override
    public void onArticleFetchError(String message) {

    }

    @Override
    public void onCommentsFetchSuccess() {

    }

    @Override
    public void onCommentsFetchError(String message) {

    }
}
