package com.example.mohitkumar.trialapp.core.profile;

import com.example.mohitkumar.trialapp.data.settings.ProfileResponse;

import retrofit2.Response;

public class ProfilePresenter implements IProfilePresenter, IProfileModel.OnFetchProfileListener {

    private IProfileView view;
    private IProfileModel model;

    ProfilePresenter() {
        this.model = new ProfileModel();
    }

    @Override
    public void getProfile(String username) {
        model.getProfile(username,  this);
        view.displayProgress();
    }

    @Override
    public void onAttach(IProfileView profileView) {
        this.view = profileView;
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
