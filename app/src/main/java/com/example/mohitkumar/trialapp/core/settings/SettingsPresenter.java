package com.example.mohitkumar.trialapp.core.settings;

import android.util.Log;

import com.example.mohitkumar.trialapp.data.settings.UserPOJO;
import com.example.mohitkumar.trialapp.util.Constants;
import com.example.mohitkumar.trialapp.util.PrefManager;
import com.example.mohitkumar.trialapp.data.loginsignup.User;
import com.example.mohitkumar.trialapp.data.settings.ProfileResponse;

import retrofit2.Response;

import static com.example.mohitkumar.trialapp.MainApplication.TAG;

public class SettingsPresenter implements ISettingsPresenter, ISettingsModel.OnSettingsUpdateListener, ISettingsModel.OnFetchProfileListener {

    ISettingsModel model;
    ISettingsView view;

    public SettingsPresenter() {
        this.model = new SettingsModel() ;
    }

    @Override
    public void onAttach(ISettingsView settingsView) {
        this.view = settingsView;
    }

    @Override
    public void updateSettings(UserPOJO object) {
        model.updateSettings(object, (ISettingsModel.OnSettingsUpdateListener) this);
        view.displayProgress();
    }

    @Override
    public void getProfile(String username) {
        model.getProfile(username, this);
        view.displayProgress();
    }


    @Override
    public void onSettingsUpdateError(String error) {
        view.onSettingsUpdateError(error);
    }

    @Override
    public void onSettingsUpdateSuccess(Response<User> response) {
        User user = response.body();

        if (response.code() == 400) {
            onSettingsUpdateError(response.errorBody().toString());
        }
        if (user == null) {
            Log.d(TAG, "user is null");
        } else {
            PrefManager.putString(Constants.USER_NAME, user.user.username);
            PrefManager.putString(Constants.ACCESS_TOKEN, user.user.token);
            PrefManager.putString(Constants.EMAIL, user.user.email);
            view.onSettingsUpdateSuccess(user.toString());
        }
    }

    @Override
    public void onProfileFetchError(String error) {
        view.onProfileFetchError(error);
    }

    @Override
    public void onProfileFetchSuccess(Response<ProfileResponse> response) {
        view.onProfileFetchSuccess(response.body());
    }
}
