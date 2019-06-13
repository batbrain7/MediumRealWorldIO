package com.example.mohitkumar.trialapp.core.profile;


import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mohitkumar.trialapp.R;
import com.example.mohitkumar.trialapp.core.PaginationScrollListener;
import com.example.mohitkumar.trialapp.core.feed.GlobalFeedAdapter;
import com.example.mohitkumar.trialapp.core.feed.GlobalViewModel;
import com.example.mohitkumar.trialapp.data.mainpage.Article;
import com.example.mohitkumar.trialapp.databinding.FavoriteFeedBinding;
import com.example.mohitkumar.trialapp.util.Constants;
import com.example.mohitkumar.trialapp.util.PrefManager;
import com.example.mohitkumar.trialapp.util.Utils;

import java.util.List;

import static com.example.mohitkumar.trialapp.MainApplication.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {


    public FavoriteFragment() {
        // Required empty public constructor
    }

    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int currentPage = 0;
    private static long TOTAL_PAGES = 500;
    GlobalFeedAdapter adapter;
    LinearLayoutManager linearLayoutManager;
    GlobalViewModel viewModel;
    FavoriteFeedBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite, container, false);
        viewModel = ViewModelProviders.of(this).get(GlobalViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter = new GlobalFeedAdapter(getActivity(), viewModel);
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        binding.teamsRecyclerView.setLayoutManager(linearLayoutManager);

        binding.teamsRecyclerView.setItemAnimator(new DefaultItemAnimator());

        binding.teamsRecyclerView.setAdapter(adapter);
        viewModel.getProgress().observe(this, binding.progressBar::setVisibility);
        loadData();

        binding.pullToRefresh.setOnRefreshListener(() -> {
            if (!Utils.hasNetwork()) {
                Toast.makeText(getActivity(), "No internet connection !!", Toast.LENGTH_LONG).show();
                binding.pullToRefresh.setRefreshing(false);
                return;
            }
            viewModel.getProgress().observe(this, binding.progressBar::setVisibility);
            loadData();
            binding.pullToRefresh.setRefreshing(false);
        });

    }

    private void loadData() {
        binding.teamsRecyclerView.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage += 20;

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadNextPage();
                    }
                }, 500);
            }

            @Override
            public int getTotalPageCount() {
                return (int)TOTAL_PAGES;
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
        loadArticlesFirst();
    }

    private void loadArticlesFirst() {
        viewModel.getFeedFavoriteArticles(0, PrefManager.getString(Constants.USERNAME, ""))
                .observe(this, globalFeedResponse -> {
                    binding.progressBar.setVisibility(View.GONE);
                    if (globalFeedResponse.getArticlesCount() == 0) {
                        binding.textNothinghere.setVisibility(View.VISIBLE);
                        //     Toast.makeText(getContext(), "No articles here.....yet", Toast.LENGTH_LONG).show();
                        return;
                    }
                    TOTAL_PAGES = globalFeedResponse.getArticlesCount();
                    List<Article> articleList = globalFeedResponse.getArticles();
                    Log.d(TAG, "In here : " + articleList.toString());
                    adapter.addAll(articleList);

                    if (currentPage <= TOTAL_PAGES)
                        adapter.addLoadingFooter();
                    else
                        isLastPage = true;
                });
    }

    private void loadNextPage() {
        viewModel.getFeedFavoriteArticles(currentPage, PrefManager.getString(Constants.USERNAME, ""))
                .observe(this, globalFeedResponse -> {
                    adapter.removeLoadingFooter();
                    isLoading = false;
                    if (globalFeedResponse.getArticlesCount() == 0) {
                        binding.textNothinghere.setVisibility(View.VISIBLE);
                        //       Toast.makeText(getContext(), "No articles here... yet", Toast.LENGTH_LONG).show();
                        Log.d(TAG, "No articles here... yet");
                        return;
                    }
                    List<Article> results = globalFeedResponse.getArticles();
                    adapter.addAll(results);

                    if (currentPage < TOTAL_PAGES)
                        adapter.addLoadingFooter();
                    else isLastPage = true;
                });
    }
}
