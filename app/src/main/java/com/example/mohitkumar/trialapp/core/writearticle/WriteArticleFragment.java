package com.example.mohitkumar.trialapp.core.writearticle;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import static com.example.mohitkumar.trialapp.MainApplication.TAG;
import com.example.mohitkumar.trialapp.R;
import com.example.mohitkumar.trialapp.data.writearticle.WArticle;
import com.example.mohitkumar.trialapp.data.writearticle.WriteArticle;
import com.example.mohitkumar.trialapp.databinding.WriteArticleBinding;

import java.util.ArrayList;

public class WriteArticleFragment extends Fragment implements IWriteArticleView{

    public WriteArticleFragment() {
        // Required empty public constructor
    }

    WriteArticleBinding binding;
    IWriteArticlePresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_write_article, container, false);
        presenter = new WriteArticlePresenter();
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.onAttach(this);
        binding.publishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                publish();
            }
        });
    }

    public void publish() {
        if (!TextUtils.isEmpty(binding.articleTitle.getText()) && !TextUtils.isEmpty(binding.articleAbout.getText()) &&
                    !TextUtils.isEmpty(binding.articleBody.getText())) {
            WArticle article = new WArticle();
            article.title = binding.articleTitle.getText().toString();
            article.description = binding.articleAbout.getText().toString();
            article.body = binding.articleBody.getText().toString();
            article.tagList = new ArrayList<>();
            WriteArticle article1 = new WriteArticle();
            article1.article = article;
            presenter.postArticle(article1);
        }
    }

    @Override
    public void onArticlePostSuccess(String message) {
        Toast.makeText(getActivity(), "Post Successful", Toast.LENGTH_LONG).show();
        Log.d(TAG, message);
        binding.articleAbout.setText("");
        binding.articleTitle.setText("");
        binding.articleBody.setText("");
    }

    @Override
    public void onArticlePostError(String message) {
        Toast.makeText(getActivity(), "Unable to post your article, error : " + message, Toast.LENGTH_LONG).show();
    }
}
