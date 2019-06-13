package com.example.mohitkumar.trialapp.core.profile;

import com.example.mohitkumar.trialapp.network.AuthService;
import com.example.mohitkumar.trialapp.data.settings.ProfileResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileModel implements IProfileModel {

    private Call<ProfileResponse> call;

    @Override
    public void getProfile(String username, IProfileModel.OnFetchProfileListener listener) {
        call = AuthService.getApi().getProfile(username);

        call.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                listener.onProfileFetchSuccess(response);
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                listener.onProfileFetchError(t.toString());
            }
        });
    }
}
