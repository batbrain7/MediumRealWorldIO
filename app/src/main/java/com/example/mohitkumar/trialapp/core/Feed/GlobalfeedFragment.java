package com.example.mohitkumar.trialapp.core.Feed;


import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.mohitkumar.trialapp.R;
import com.example.mohitkumar.trialapp.Util.Utils;
import com.example.mohitkumar.trialapp.core.PaginationScrollListener;
import com.example.mohitkumar.trialapp.data.MainPage.Articles;
import com.example.mohitkumar.trialapp.databinding.FragmentGlobalfeedBinding;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GlobalfeedFragment extends Fragment {


    public GlobalfeedFragment() {
        // Required empty public constructor
    }

    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int currentPage = 0;
    private static final int TOTAL_PAGES = 500;
    GlobalFeedAdapter adapter;
    FragmentGlobalfeedBinding binding;
    LinearLayoutManager linearLayoutManager;
    GlobalViewModel viewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_globalfeed, container, false);
        viewModel = ViewModelProviders.of(this).get(GlobalViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter = new GlobalFeedAdapter(getActivity());
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        binding.teamsRecyclerView.setLayoutManager(linearLayoutManager);

        binding.teamsRecyclerView.setItemAnimator(new DefaultItemAnimator());

        binding.teamsRecyclerView.setAdapter(adapter);
        loadData();

        adapter.setOnItemClickListener(new GlobalFeedAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {

            }
        });

        binding.pullToRefresh.setOnRefreshListener(() -> {
            if (!Utils.hasNetwork()) {
                Toast.makeText(getActivity(), "No internet connection !!", Toast.LENGTH_LONG).show();
                binding.pullToRefresh.setRefreshing(false);
                return;
            }
            loadData();
            binding.pullToRefresh.setRefreshing(false);
            return;
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

        loadArticlesFirst();

    }

    private void loadArticlesFirst() {
        viewModel.getArticlesList(0).observe(this, list -> {
            List<Articles> articlesList = list;
            binding.progressBar.setVisibility(View.GONE);
            adapter.addAll(articlesList);

            if (currentPage <= TOTAL_PAGES)
                adapter.addLoadingFooter();
            else
                isLastPage = true;
        });
    }

    private void loadNextPage() {
        viewModel.getArticlesList(currentPage).observe(this, list -> {
            adapter.removeLoadingFooter();
            isLoading = false;

            List<Articles> results = list;
            adapter.addAll(results);

            if (currentPage != TOTAL_PAGES) adapter.addLoadingFooter();
            else isLastPage = true;
        });
    }

}
