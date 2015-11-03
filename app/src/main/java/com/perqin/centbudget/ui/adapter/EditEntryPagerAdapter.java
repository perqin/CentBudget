package com.perqin.centbudget.ui.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.perqin.centbudget.R;
import com.perqin.centbudget.ui.fragment.EditEntryDetailsFragment;
import com.perqin.centbudget.ui.fragment.EditEntryNumPadFragment;

public class EditEntryPagerAdapter extends FragmentPagerAdapter {
    private Context mApplicationContext;

    public EditEntryPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mApplicationContext = context.getApplicationContext();
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
}
