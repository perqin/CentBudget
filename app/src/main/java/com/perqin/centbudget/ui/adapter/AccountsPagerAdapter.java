package com.perqin.centbudget.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.perqin.centbudget.ui.fragment.AccountsPagerFragment;

public class AccountsPagerAdapter extends FragmentStatePagerAdapter {
    public AccountsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        // TODO
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // TODO
        return "TAB " + (position + 1);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new AccountsPagerFragment();
        // TODO : Args
        return fragment;
    }
}
