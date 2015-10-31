package com.perqin.centbudget.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class EditEntryPagerAdapter extends FragmentPagerAdapter {
    public EditEntryPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "TITLE";
    }
}
