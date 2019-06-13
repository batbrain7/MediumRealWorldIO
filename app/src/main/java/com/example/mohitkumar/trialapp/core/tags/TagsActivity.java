package com.example.mohitkumar.trialapp.core.tags;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.example.mohitkumar.trialapp.R;
import com.example.mohitkumar.trialapp.databinding.ActivityTagsBinding;

import java.util.List;

public class TagsActivity extends AppCompatActivity {

    private ActivityTagsBinding binding;
    private TagsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tags);
        viewModel = ViewModelProviders.of(this).get(TagsViewModel.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        viewModel.getProgress().observe(this, binding.progressBar::setVisibility);
        viewModel.getTags().observe(this, list -> {
            if (list != null)
                setHorizontalRecyclerView(list);
        });
    }

    private void setHorizontalRecyclerView(List<String> list) {
        binding.tagRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        TagsRecyclerAdapter adapter = new TagsRecyclerAdapter(this, list);
        binding.tagRecyclerView.setAdapter(adapter);
    }
}
