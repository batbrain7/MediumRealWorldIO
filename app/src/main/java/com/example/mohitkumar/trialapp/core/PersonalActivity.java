package com.example.mohitkumar.trialapp.core;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mohitkumar.trialapp.R;
import com.example.mohitkumar.trialapp.core.Settings.SettingsFragment;
import com.example.mohitkumar.trialapp.core.writeArticle.WriteArticleFragment;

public class PersonalActivity extends AppCompatActivity {

    String extra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_write_article);
        extra = getIntent().getStringExtra("frag");
        loadFragment();
    }

    void loadFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        switch (extra) {
            case "Write" :
                fragmentTransaction.replace(R.id.frameLayout, new WriteArticleFragment());
                break;
            case "Settings":
                fragmentTransaction.replace(R.id.frameLayout, new SettingsFragment());
                break;
            case "Profile":
                break;
        }
        fragmentTransaction.commit();
    }
}
