package com.perqin.centbudget.ui.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.perqin.centbudget.R;
import com.perqin.centbudget.db.IetEntry;
import com.perqin.centbudget.ui.fragment.EditEntryDetailsFragment;
import com.perqin.centbudget.ui.fragment.EditEntryNumPadFragment;

import java.util.ArrayList;

public class EditEntryPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> mFragments = new ArrayList<>();

//    private Context mApplicationContext;

    public EditEntryPagerAdapter(FragmentManager fm, IetEntry entryData) {
        super(fm);
//        mApplicationContext = context.getApplicationContext();
        mFragments.add(EditEntryNumPadFragment.newInstance());
        mFragments.add(EditEntryDetailsFragment.newInstance(entryData.date, entryData.detail));
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }
}
