package com.example.mohitkumar.trialapp.core.feed;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohitkumar.trialapp.R;
import com.example.mohitkumar.trialapp.data.comment.SingleArticle;
import com.example.mohitkumar.trialapp.util.Utils;
import com.example.mohitkumar.trialapp.core.PaginationScrollListener;
import com.example.mohitkumar.trialapp.core.comment.CommentActivity;
import com.example.mohitkumar.trialapp.data.mainpage.Articles;
import com.example.mohitkumar.trialapp.databinding.FragmentGlobalfeedBinding;

import java.util.List;

import static com.example.mohitkumar.trialapp.MainApplication.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class GlobalFeedFragment extends Fragment {


    public GlobalFeedFragment() {
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
    int type = 0;

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
        viewModel.getProgress().observe(this, binding.progressBar::setVisibility);
        loadData();

        adapter.setOnItemClickListener(new GlobalFeedAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v, String slug) {
                LinearLayout linearLayout = v.findViewById(R.id.favoriteArticle);
                linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        callViewModel(slug);
                    }
                });

                TextView textView = v.findViewById(R.id.titleArticle);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), CommentActivity.class);
                        intent.putExtra("slug", slug);
                        getActivity().startActivity(intent);
                    }
                });
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
            if (articlesList.size() == 0) {
                Toast.makeText(getActivity(), "No articles here... yet", Toast.LENGTH_LONG).show();
            }

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

    private void callViewModel(String slug) {
        viewModel.favoriteArticle(slug).observe(this, singleArticle -> {
            Log.d(TAG, "CAlled ViewModel " + singleArticle.toString());
            onStart();
        });
    }

}
