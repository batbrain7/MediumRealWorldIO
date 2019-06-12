package com.example.mohitkumar.trialapp.core.Settings;

import android.util.Log;

import com.example.mohitkumar.trialapp.Util.Constants;
import com.example.mohitkumar.trialapp.Util.PrefManager;
import com.example.mohitkumar.trialapp.core.writeArticle.IWriteArticleModel;
import com.example.mohitkumar.trialapp.core.writeArticle.IWriteArticlePresenter;
import com.example.mohitkumar.trialapp.core.writeArticle.IWriteArticleView;
import com.example.mohitkumar.trialapp.data.LoginSignUp.User;
import com.example.mohitkumar.trialapp.data.Settings.ProfileResponse;
import com.example.mohitkumar.trialapp.data.comment.SingleArticle;
import com.example.mohitkumar.trialapp.data.writearticle.WriteArticle;

import org.json.JSONObject;

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
    public void updateSettings(String username, String object) {
        model.updateSettings(username, object, (ISettingsModel.OnSettingsUpdateListener) this);
    }

    @Override
    public void getProfile(String username) {
        model.getProfile(username);
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
            PrefManager.putString(Constants.USERNAME, user.user.username);
            PrefManager.putString(Constants.USERNAME, user.user.username);
            PrefManager.putString(Constants.USERNAME, user.user.username);
            view.onSettingsUpdateSuccess(user.toString());
        }
    }

    @Override
    public void onProfileFetchError(String error) {

    }

    @Override
    public void onProfileFetchSuccess(Response<ProfileResponse> response) {

    }
}
