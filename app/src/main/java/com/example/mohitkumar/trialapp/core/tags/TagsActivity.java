package com.example.mohitkumar.trialapp.core.tags;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.mohitkumar.trialapp.R;
import com.example.mohitkumar.trialapp.core.MainActivity;
import com.example.mohitkumar.trialapp.core.PaginationScrollListener;
import com.example.mohitkumar.trialapp.core.comment.CommentActivity;
import com.example.mohitkumar.trialapp.core.feed.GlobalFeedAdapter;
import com.example.mohitkumar.trialapp.data.mainpage.Article;
import com.example.mohitkumar.trialapp.databinding.ActivityTagsBinding;
import com.example.mohitkumar.trialapp.util.Utils;
import static com.example.mohitkumar.trialapp.MainApplication.TAG;

import java.util.List;

public class TagsActivity extends AppCompatActivity {

    private ActivityTagsBinding binding;
    private TagsViewModel viewModel;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int currentPage = 0;
    private static final int TOTAL_PAGES = 500;
    private GlobalFeedAdapter adapter;
    private TagsRecyclerAdapter recyclerAdapter;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tags);
        viewModel = ViewModelProviders.of(this).get(TagsViewModel.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        viewModel.getProgress().observe(this, binding.progressBar::setVisibility);
        viewModel.getTags().observe(this, list -> {
            if (list != null)
                setHorizontalRecyclerView(list);
        });
        linearLayoutManager = new LinearLayoutManager(TagsActivity.this, LinearLayoutManager.VERTICAL, false);
        binding.teamsRecyclerView.setLayoutManager(linearLayoutManager);
    }

    private void setHorizontalRecyclerView(List<String> list) {
        recyclerAdapter = new TagsRecyclerAdapter(list);
        binding.tagRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        binding.tagRecyclerView.setAdapter(recyclerAdapter);
        Log.d(TAG, "Adapter is set");

        recyclerAdapter.setOnItemClickListener(new TagsRecyclerAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v, String s) {
                Log.d(TAG, "Inside click method");

                binding.frameLayout.setVisibility(View.VISIBLE);
                adapter = new GlobalFeedAdapter(TagsActivity.this);
                adapter.setOnItemClickListener(new GlobalFeedAdapter.ClickListener() {
                    @Override
                    public void onItemClick(int position, View v, String s) {
                        Intent intent = new Intent(TagsActivity.this, CommentActivity.class);
                        intent.putExtra("slug", s);
                        startActivity(intent);
                    }
                });
                binding.teamsRecyclerView.setItemAnimator(new DefaultItemAnimator());

                binding.teamsRecyclerView.setAdapter(adapter);
                viewModel.getProgress().observe(TagsActivity.this, binding.progressBar::setVisibility);
                loadData(s);

                binding.pullToRefresh.setOnRefreshListener(() -> {
                    if (!Utils.hasNetwork()) {
                        Toast.makeText(TagsActivity.this, "No internet connection !!", Toast.LENGTH_LONG).show();
                        binding.pullToRefresh.setRefreshing(false);
                        return;
                    }
                    viewModel.getProgress().observe(TagsActivity.this, binding.progressBar::setVisibility);
                    loadData(s);
                    binding.pullToRefresh.setRefreshing(false);
                    return;
                });
            }
        });
    }

    private void loadData(String tag) {
        binding.teamsRecyclerView.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage += 20;

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadNextPage(tag);
                    }
                }, 500);
            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });
        loadArticlesFirst(tag);
    }

    private void loadArticlesFirst(String tag) {
        viewModel.getTagFeed(0, tag).observe(this, feedResponse -> {
            List<Article> articleList = feedResponse.getArticles();
            binding.progressBar.setVisibility(View.GONE);
            adapter.addAll(articleList);
            if (articleList.size() == 0) {
                Toast.makeText(this, "No articles here... yet", Toast.LENGTH_LONG).show();
            }

            if (currentPage <= TOTAL_PAGES)
                adapter.addLoadingFooter();
            else
                isLastPage = true;
        });
    }

    private void loadNextPage(String tag) {
        viewModel.getTagFeed(currentPage, tag).observe(this, feedResponse -> {
            adapter.removeLoadingFooter();
            isLoading = false;

            List<Article> results = feedResponse.getArticles();
            adapter.addAll(results);

            if (currentPage <= TOTAL_PAGES) adapter.addLoadingFooter();
            else isLastPage = true;
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
