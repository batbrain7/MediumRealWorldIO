package com.example.mohitkumar.trialapp.core.EditArticles;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.mohitkumar.trialapp.R;
import com.example.mohitkumar.trialapp.core.MainActivity;
import com.example.mohitkumar.trialapp.data.comment.SingleArticle;
import com.example.mohitkumar.trialapp.data.mainpage.Article;
import com.example.mohitkumar.trialapp.data.writearticle.WArticle;
import com.example.mohitkumar.trialapp.data.writearticle.WriteArticle;
import com.example.mohitkumar.trialapp.databinding.EditArticleBinding;

import java.util.ArrayList;

public class EditArticleActivity extends AppCompatActivity implements IEditArticleView {

    EditArticleBinding binding;
    String extra;
    IEditArticlePresenter presenter;
    WArticle article;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_article);
        extra = getIntent().getStringExtra("slug");
        presenter = new EditArticlePresenter();
        presenter.onAttach(this);
        presenter.fetchArticle(extra);
        article = new WArticle();
    }

    @Override
    public void onArticleFetchSuccess(Article article) {
        binding.progressBar.setVisibility(View.GONE);
        if (article.body != null) {
            this.article.body = article.body;
            binding.articleBody.setText(article.body);
        }
        if (article.title != null) {
            this.article.title = article.title;
            binding.articleTitle.setText(article.title);
        }
        if (article.description != null) {
            this.article.description = article.description;
            binding.articleAbout.setText(article.description);
        }
    }

    @Override
    public void onArticleFetchError(String error) {
        binding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onArticleUpdateSuccess(Article article) {
        binding.progressBar.setVisibility(View.GONE);
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void onArticleUpdateError(String error) {
        binding.progressBar.setVisibility(View.GONE);
        Toast.makeText(this, "Facing error while updating your article ", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onArticleDeleteError(String error) {
        binding.progressBar.setVisibility(View.GONE);
        Toast.makeText(this, "Facing error while deleting your article ", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onArticleDeleteSuccess(String success) {
        binding.progressBar.setVisibility(View.GONE);
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void displayProgress() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    public void updateArticle(View view) {
        if (!TextUtils.isEmpty(binding.articleTitle.getText()))
            this.article.title = binding.articleTitle.getText().toString();
        if (!TextUtils.isEmpty(binding.articleAbout.getText()))
            this.article.description = binding.articleAbout.getText().toString();
        if (!TextUtils.isEmpty(binding.articleBody.getText()))
            this.article.body = binding.articleBody.getText().toString();
        this.article.tagList = new ArrayList<>();
        WriteArticle writeArticle = new WriteArticle();
        writeArticle.article = this.article;
        presenter.updateArticle(extra, writeArticle);
    }

    public void deleteArticle(View view) {
        presenter.deleteArticle(extra);
    }
}
