package com.example.mohitkumar.trialapp.core.settings;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.PrecomputedText;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mohitkumar.trialapp.R;
import com.example.mohitkumar.trialapp.data.settings.ProfileResponse;
import com.example.mohitkumar.trialapp.data.settings.UpdateUserPOJO;
import com.example.mohitkumar.trialapp.data.settings.UserPOJO;
import com.example.mohitkumar.trialapp.util.Constants;
import com.example.mohitkumar.trialapp.util.PrefManager;
import com.example.mohitkumar.trialapp.util.Utils;
import com.example.mohitkumar.trialapp.core.MainActivity;
import com.example.mohitkumar.trialapp.databinding.SettingsBinding;

import static com.example.mohitkumar.trialapp.MainApplication.TAG;


import org.json.JSONException;
import org.json.JSONObject;

public class SettingsFragment extends Fragment implements ISettingsView {

    public SettingsFragment() {
    }

    ISettingsPresenter presenter;
    SettingsBinding binding;
    UpdateUserPOJO pojo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false);
        presenter = new SettingsPresenter();
        presenter.onAttach(this);
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        pojo = new UpdateUserPOJO();
        binding.updateSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateProfile();
            }
        });

        binding.logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOut();
            }
        });
        presenter.getProfile(PrefManager.getString(Constants.USERNAME, ""));
    }

    private void logOut() {
        Utils.clearToken();
        Utils.clearPrefs();
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        getActivity().startActivity(intent);
        getActivity().finish();
    }

    private void updateProfile() {
        if (!TextUtils.isEmpty(binding.newPassword.getText())) {
            pojo.password = binding.newPassword.getText().toString();
        }
        if (!TextUtils.isEmpty(binding.email.getText()) &&
                !binding.email.getText().toString().equals(PrefManager.getString(Constants.EMAIL, ""))) {
            pojo.email = binding.email.getText().toString();
        }
        if (!TextUtils.isEmpty(binding.bio.getText())) {
            pojo.bio = binding.bio.getText().toString();
        }
        if (!TextUtils.isEmpty(binding.userName.getText())) {
            pojo.username = binding.userName.getText().toString();
        }

        UserPOJO user = new UserPOJO();
        user.userPOJO = pojo;
        Log.d(TAG, user.toString());

        presenter.updateSettings(user);
    }

    @Override
    public void onSettingsUpdateSuccess(String result) {
        Toast.makeText(getActivity(), "Settings Updated", Toast.LENGTH_LONG).show();
        Log.d(TAG, result);
        binding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onSettingsUpdateError(String message) {
        Toast.makeText(getActivity(), "Unable to update your settings", Toast.LENGTH_LONG).show();
        binding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onProfileFetchSuccess(ProfileResponse response) {
        binding.bio.setText(response.profile.bio);
        binding.email.setText(PrefManager.getString(Constants.EMAIL, ""));
        binding.userName.setText(response.profile.username);
        binding.progressBar.setVisibility(View.GONE);
        if (response.profile != null)
            pojo.bio = response.profile.bio;
        pojo.email = PrefManager.getString(Constants.EMAIL, "");
        pojo.username = response.profile.username;
    }

    @Override
    public void onProfileFetchError(String error) {
        Toast.makeText(getActivity(), "ERROR", Toast.LENGTH_LONG).show();
        Log.d(TAG, error);
        binding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void displayProgress() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }
}
