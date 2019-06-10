package com.example.mohitkumar.trialapp.core.Feed;


import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mohitkumar.trialapp.R;
import com.example.mohitkumar.trialapp.databinding.FragmentGlobalfeedBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class GlobalfeedFragment extends Fragment {


    public GlobalfeedFragment() {
        // Required empty public constructor
    }

    FragmentGlobalfeedBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_globalfeed, container, false);
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void loadData() {

    }

}
