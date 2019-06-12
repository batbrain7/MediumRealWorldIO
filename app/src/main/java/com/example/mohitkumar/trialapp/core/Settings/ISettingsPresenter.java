package com.example.mohitkumar.trialapp.core.Settings;

import com.example.mohitkumar.trialapp.core.writeArticle.IWriteArticleView;
import com.example.mohitkumar.trialapp.data.writearticle.WriteArticle;

import org.json.JSONObject;

public interface ISettingsPresenter {
    void onAttach(ISettingsView settingsView);

    void updateSettings(String username, String string);

    void getProfile(String username);
}
