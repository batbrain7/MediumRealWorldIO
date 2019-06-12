package com.example.mohitkumar.trialapp.core.profile;

public interface IProfilePresenter {
    void getProfile(String username);

    void onAttach(IProfileView profileView);
}
