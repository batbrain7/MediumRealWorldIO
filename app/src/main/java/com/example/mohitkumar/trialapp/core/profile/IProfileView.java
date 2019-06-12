package com.example.mohitkumar.trialapp.core.profile;

import com.example.mohitkumar.trialapp.data.settings.ProfileResponse;

public interface IProfileView {

    void onProfileFetchSuccess(ProfileResponse response);

    void onProfileFetchError(String error);

    void displayProgress();
}
