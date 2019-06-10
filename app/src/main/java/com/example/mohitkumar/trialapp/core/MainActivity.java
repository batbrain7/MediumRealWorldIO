package com.example.mohitkumar.trialapp.core;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.mohitkumar.trialapp.R;
import com.example.mohitkumar.trialapp.MainApplication;
import com.example.mohitkumar.trialapp.core.Feed.APIClient;
import com.example.mohitkumar.trialapp.core.Feed.GlobalfeedFragment;
import com.example.mohitkumar.trialapp.core.Login.ILoginPresenter;
import com.example.mohitkumar.trialapp.core.Login.LoginActivity;
import com.example.mohitkumar.trialapp.core.Login.LoginPresenter;
import com.example.mohitkumar.trialapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ILoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        presenter = new LoginPresenter();

        APIClient apiClient = new APIClient();
        apiClient.getGlobalFeed(20, 0);
    }

    private void loadFragments() {
        MainFragmentAdapter leagueFragmentAdapter;
        leagueFragmentAdapter = new MainFragmentAdapter(getSupportFragmentManager());

        leagueFragmentAdapter.addFragments(new GlobalfeedFragment(), this.getResources().getString(R.string.global_Feed));
       // leagueFragmentAdapter.addFragments(new StandingsFragment(), this.getResources().getString(R.string.standings_fragment));
        binding.viewPager.setAdapter(leagueFragmentAdapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);



    }

    public void login(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }
}
