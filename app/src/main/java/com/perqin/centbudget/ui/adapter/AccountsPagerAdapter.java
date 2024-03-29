package com.perqin.centbudget.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.perqin.centbudget.R;
import com.perqin.centbudget.db.Account;
import com.perqin.centbudget.db.DbFactory;
import com.perqin.centbudget.ui.fragment.AccountsPagerFragment;

import java.util.ArrayList;

public class AccountsPagerAdapter extends FragmentStatePagerAdapter {
    public static final int INDEX_LAST = -1;

    private Context mActivityContext;
    private ArrayList<Account> mDataSet = new ArrayList<>();

    private OnDataSetChangedListener mListener = null;

    public AccountsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mActivityContext = context;
    }

    public void addAccount(Account account) {
        DbFactory.createInAccounts(mActivityContext, account);
        refreshAccounts(getCount() - 1);
    }

    public void deleteAccount(int position) {
        DbFactory.deleteInAccount(mActivityContext, mDataSet.get(position));
        int current = ((position == getCount() - 1) ? (position - 1) : position);
        refreshAccounts(current);
    }

    public void refreshAccounts(int position) {
        updateDataSet();
        notifyDataSetChangedWithCurrent(position);
    }

    public void updateDataSet() {
        mDataSet.clear();
        addAccountAll();
        ArrayList<Account> list = DbFactory.readAllInAccounts(mActivityContext);
        for (int i = 0; i < list.size(); ++i) {
            mDataSet.add(list.get(i));
        }
    }

    public void addAccountAll() {
        // TODO
        Account all = new Account();
        all._id = 2333;
        all.display_name = mActivityContext.getString(R.string.all);
        mDataSet.add(all);
    }

    public Account getAccount(int i) {
        return mDataSet.get(i);
    }

    @Override
    public int getCount() {
        return mDataSet.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mDataSet.get(position).display_name;
    }

    // TODO : Args
    @Override
    public Fragment getItem(int position) {
        return AccountsPagerFragment.newInstance(mActivityContext, "233 : " + mDataSet.get(position).display_name);
    }

    public void notifyDataSetChangedWithCurrent(int current) {
        super.notifyDataSetChanged();
        if (mListener != null) {
            mListener.onDataSetChanged(current);
        }
    }

    @Override
    public void notifyDataSetChanged() {
        notifyDataSetChangedWithCurrent(0);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public interface OnDataSetChangedListener {
        void onDataSetChanged(int current);
    }

    public void setOnDataSetChangedListener(OnDataSetChangedListener l) {
        mListener = l;
    }
}
