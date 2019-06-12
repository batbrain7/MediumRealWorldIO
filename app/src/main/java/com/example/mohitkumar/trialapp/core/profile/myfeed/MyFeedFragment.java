package com.example.mohitkumar.trialapp.core.profile.myfeed;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
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
import com.example.mohitkumar.trialapp.core.EditArticles.EditArticleActivity;
import com.example.mohitkumar.trialapp.core.PaginationScrollListener;
import com.example.mohitkumar.trialapp.core.comment.CommentActivity;
import com.example.mohitkumar.trialapp.core.feed.GlobalFeedAdapter;
import com.example.mohitkumar.trialapp.core.feed.YourFeedViewModel;
import com.example.mohitkumar.trialapp.data.mainpage.Articles;
import com.example.mohitkumar.trialapp.databinding.FeedBinding;
import com.example.mohitkumar.trialapp.util.Constants;
import com.example.mohitkumar.trialapp.util.PrefManager;
import com.example.mohitkumar.trialapp.util.Utils;

import java.util.List;

import static com.example.mohitkumar.trialapp.MainApplication.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFeedFragment extends Fragment {


    public MyFeedFragment() {
        // Required empty public constructor
    }

    FeedBinding binding;
    LinearLayoutManager linearLayoutManager;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int currentPage = 0;
    MyFeedAdapter adapter;
    YourFeedViewModel viewModel;
    private static final int TOTAL_PAGES = 500;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_feed, container, false);
        viewModel = ViewModelProviders.of(this).get(YourFeedViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter = new MyFeedAdapter(getActivity());
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        binding.teamsRecyclerView.setLayoutManager(linearLayoutManager);

        binding.teamsRecyclerView.setItemAnimator(new DefaultItemAnimator());

        binding.teamsRecyclerView.setAdapter(adapter);
        viewModel.getProgress().observe(this, binding.progressBar::setVisibility);
        loadData();

        adapter.setOnItemClickListener(new MyFeedAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v, String slug) {
                Intent intent = new Intent(getActivity(), EditArticleActivity.class);
                intent.putExtra("slug", slug);
                getActivity().startActivity(intent);
            }
        });

        binding.pullToRefresh.setOnRefreshListener(() -> {
            if (!Utils.hasNetwork()) {
                Toast.makeText(getActivity(), "No internet connection !!", Toast.LENGTH_LONG).show();
                binding.pullToRefresh.setRefreshing(false);
                return;
            }
            viewModel.getProgress().observe(this, binding.progressBar::setVisibility);
            loadData();
            binding.pullToRefresh.setRefreshing(false);
            return;
        });

    }

    private void loadData() {
//        binding.teamsRecyclerView.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
//            @Override
//            protected void loadMoreItems() {
//                isLoading = true;
//                currentPage += 20;
//
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        loadNextPage();
//                    }
//                }, 500);
//            }
//
//            @Override
//            public int getTotalPageCount() {
//                return TOTAL_PAGES;
//            }
//
//            @Override
//            public boolean isLastPage() {
//                return isLastPage;
//            }
//
//            @Override
//            public boolean isLoading() {
//                return isLoading;
//            }
//        });
        loadArticlesFirst();
    }

    private void loadArticlesFirst() {
        viewModel.getMyFeedArticles(0, PrefManager.getString(Constants.USERNAME, ""))
                .observe(this, globalFeedResponse -> {
                    binding.progressBar.setVisibility(View.GONE);
                    if (globalFeedResponse.getArticlesCount() == 0) {
                        binding.textNothinghere.setVisibility(View.VISIBLE);
                        //     Toast.makeText(getContext(), "No articles here.....yet", Toast.LENGTH_LONG).show();
                        return;
                    }
                    List<Articles> articlesList = globalFeedResponse.getArticles();
                    Log.d(TAG, articlesList.toString());
                    adapter.addAll(articlesList);

                    if (currentPage <= TOTAL_PAGES)
                        adapter.addLoadingFooter();
                    else
                        isLastPage = true;
                });
    }

    private void loadNextPage() {
        viewModel.getMyFeedArticles(currentPage, PrefManager.getString(Constants.USERNAME, ""))
                .observe(this, globalFeedResponse -> {
                    adapter.removeLoadingFooter();
                    isLoading = false;
                    if (globalFeedResponse.getArticlesCount() == 0) {
                        binding.textNothinghere.setVisibility(View.VISIBLE);
                        //       Toast.makeText(getContext(), "No articles here... yet", Toast.LENGTH_LONG).show();
                        Log.d(TAG, "No articles here... yet");
                        return;
                    }
                    List<Articles> results = globalFeedResponse.getArticles();
                    adapter.addAll(results);

                    if (currentPage < TOTAL_PAGES) adapter.addLoadingFooter();
                    else isLastPage = true;
                });
    }
}
