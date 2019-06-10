package com.example.mohitkumar.trialapp.core;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class MainFragmentAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> fragments = new ArrayList<Fragment>();
    ArrayList<String> tabTitles = new ArrayList<String>();

    public MainFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragments(Fragment fragments,String titles)
    {
        this.fragments.add(fragments);
        this.tabTitles.add(titles);
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles.get(position);
    }
}
