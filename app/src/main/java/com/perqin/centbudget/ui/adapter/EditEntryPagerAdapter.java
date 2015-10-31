package com.perqin.centbudget.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.perqin.centbudget.ui.fragment.EditEntryDetailsFragment;
import com.perqin.centbudget.ui.fragment.EditEntryNumPadFragment;

public class EditEntryPagerAdapter extends FragmentPagerAdapter {
    public EditEntryPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return EditEntryNumPadFragment.newInstance();
        } else if (position == 1) {
            return EditEntryDetailsFragment.newInstance("", "");
        } else {
            return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "TITLE";
    }
}
