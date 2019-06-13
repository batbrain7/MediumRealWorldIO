package com.example.mohitkumar.trialapp.core.tags;

import android.annotation.SuppressLint;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.mohitkumar.trialapp.data.mainpage.FeedResponse;
import com.example.mohitkumar.trialapp.network.APIClient;
import com.example.mohitkumar.trialapp.network.APIService;

import java.util.List;

public class TagsViewModel extends ViewModel {

    private APIService service;
    private final MutableLiveData<List<String>> tags = new MutableLiveData<>();
    private final MutableLiveData<Integer> progress = new MutableLiveData<>();
    private final MutableLiveData<FeedResponse> tagFeedLiveData = new MutableLiveData<>();

    public TagsViewModel() {
        service = new APIClient();
    }

    @SuppressLint("CheckResult")
    public MutableLiveData<List<String>> getTags() {
        service.getTags().
                doOnSubscribe(disposable -> { progress.setValue(0);})
                .doFinally(() -> progress.setValue(8))
                .subscribe(status -> {
                    tags.setValue(status.tags);
                }, error -> {
                    Log.i("TagsViewModel.class", "onStart: " + error);

                });
        return tags;
    }

    public MutableLiveData<Integer> getProgress() {
        return progress;
    }

    @SuppressLint("CheckResult")
    public MutableLiveData<FeedResponse> getTagFeed(long offset, String tag) {
        service.getTagFeed(20, offset, tag)
                .doOnSubscribe(disposable -> progress.setValue(0))
                .doFinally(() -> progress.setValue(8))
                .subscribe(status -> {
                    tagFeedLiveData.setValue(status);
                }, error -> {
                    Log.i("GlobalViewModel.class", "onStart: " + error);
                });
        return tagFeedLiveData;
    }


}
