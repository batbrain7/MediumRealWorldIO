package com.example.mohitkumar.trialapp.core.profile;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.mohitkumar.trialapp.R;
import com.example.mohitkumar.trialapp.core.MainFragmentAdapter;
import com.example.mohitkumar.trialapp.core.feed.YourFeedFragment;
import com.example.mohitkumar.trialapp.core.profile.myfeed.MyFeedFragment;
import com.example.mohitkumar.trialapp.data.settings.ProfileResponse;
import com.example.mohitkumar.trialapp.databinding.ProfileBinding;
import com.example.mohitkumar.trialapp.util.Constants;
import com.example.mohitkumar.trialapp.util.PrefManager;

import static com.example.mohitkumar.trialapp.MainApplication.TAG;

public class ProfileActivity extends AppCompatActivity implements IProfileView{

    ProfileBinding binding;
    IProfilePresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        presenter = new ProfilePresenter();
        presenter.onAttach(this);
        loadFragments();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.getProfile(PrefManager.getString(Constants.USERNAME, ""));
    }

    private void loadFragments() {
        MainFragmentAdapter leagueFragmentAdapter;
        leagueFragmentAdapter = new MainFragmentAdapter(getSupportFragmentManager());
        leagueFragmentAdapter.addFragments(new MyFeedFragment(), this.getResources().getString(R.string.my_feed));
        leagueFragmentAdapter.addFragments(new FavoriteFragment(), this.getResources().getString(R.string.favorited_articles));
        binding.viewPager.setAdapter(leagueFragmentAdapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
    }

    @Override
    public void onProfileFetchSuccess(ProfileResponse response) {
        binding.userName.setText(response.profile.username);
        Glide.with(this)
                .asBitmap()
                .load(response.profile.image)
                .listener(new RequestListener<Bitmap>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                        //Toast.makeText(this, "ERROR in image laoding", Toast.LENGTH_LONG).show();
                        Log.d(TAG, "ERROR in image laoding");
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource,
                                                   boolean isFirstResource) {
                        binding.imageUser.setImageBitmap(resource);
                        return true;
                    }
                }).submit();
        binding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onProfileFetchError(String error) {
        Toast.makeText(this, "Error in loading profile", Toast.LENGTH_LONG).show();
        Log.d(TAG, error);
        binding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void displayProgress() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }
}
