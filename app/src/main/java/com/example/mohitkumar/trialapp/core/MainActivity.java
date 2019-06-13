package com.example.mohitkumar.trialapp.core;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.mohitkumar.trialapp.R;
import com.example.mohitkumar.trialapp.core.feed.YourFeedFragment;
import com.example.mohitkumar.trialapp.core.profile.ProfileActivity;
import com.example.mohitkumar.trialapp.core.tags.TagsActivity;
import com.example.mohitkumar.trialapp.util.Constants;
import com.example.mohitkumar.trialapp.util.PrefManager;
import com.example.mohitkumar.trialapp.util.Utils;
import com.example.mohitkumar.trialapp.core.feed.GlobalFeedFragment;
import com.example.mohitkumar.trialapp.core.login.ILoginPresenter;
import com.example.mohitkumar.trialapp.core.login.LoginActivity;
import com.example.mohitkumar.trialapp.core.login.LoginPresenter;
import com.example.mohitkumar.trialapp.core.signup.SignUpActivity;
import com.example.mohitkumar.trialapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ILoginPresenter presenter;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        presenter = new LoginPresenter();

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, binding.activityMain, R.string.Open, R.string.Close);

        binding.activityMain.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Log.d("TrialApp", PrefManager.getBoolean(Constants.LOG_IN) + " ");

        if (Utils.isLoggedIn()) {
            binding.nv.getMenu().clear();
            binding.nv.inflateMenu(R.menu.navigation);
        } else {
            binding.nv.getMenu().clear();
            binding.nv.inflateMenu(R.menu.nav_logout_menu);
        }
        setNavBar();
        loadFragments();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.tags) {
            startActivity(new Intent(this, TagsActivity.class));
        }
        if (actionBarDrawerToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    private void setNavBar() {
        binding.nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (Utils.isLoggedIn()) {
                    int id = menuItem.getItemId();
                    switch (id) {
                        case R.id.newArticle:
                            openClassWrite("Write");
                            break;
                        case R.id.settings:
                            openClassWrite("Settings");
                            break;
                        case R.id.myprofile:
                            openClassProfile();
                            break;
                        default:
                            return true;
                    }
                } else {
                    int id = menuItem.getItemId();
                    switch (id) {
                        case R.id.signIn:
                            login();
                            break;
                        case R.id.signUp:
                            signUp();
                            break;
                        default:
                            return true;
                    }
                }
                return true;
            }
        });
    }

    private void loadFragments() {
        MainFragmentAdapter leagueFragmentAdapter;
        leagueFragmentAdapter = new MainFragmentAdapter(getSupportFragmentManager());
        if (Utils.isLoggedIn()) {
            leagueFragmentAdapter.addFragments(new GlobalFeedFragment(), this.getResources().getString(R.string.global_Feed));
            leagueFragmentAdapter.addFragments(new YourFeedFragment(), this.getResources().getString(R.string.your_feed));
        } else {
            leagueFragmentAdapter.addFragments(new GlobalFeedFragment(), this.getResources().getString(R.string.global_Feed));
        }
        binding.viewPager.setAdapter(leagueFragmentAdapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
    }

    public void signUp() {
        startActivity(new Intent(this, SignUpActivity.class));
    }

    public void login() {
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void openClassWrite(String string) {
        Intent intent = new Intent(this, PersonalActivity.class);
        intent.putExtra("frag", string);
        startActivity(intent);
    }

    public void openClassProfile() {
        startActivity(new Intent(this, ProfileActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.tag_menu, menu);
        return true;
    }

}
