package com.example.mohitkumar.trialapp.core.feed;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mohitkumar.trialapp.R;
import com.example.mohitkumar.trialapp.databinding.MyFeedBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyFeed extends Fragment {


    public MyFeed() {
        // Required empty public constructor
    }



    MyFeedBinding binding;
    GlobalViewModel viewModel;
    LinearLayoutManager linearLayoutManager;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int currentPage = 0;
    private static final int TOTAL_PAGES = 500;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_feed, container, false);
        return binding.getRoot();
    }

}
