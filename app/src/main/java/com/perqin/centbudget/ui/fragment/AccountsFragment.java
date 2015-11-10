package com.perqin.centbudget.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.perqin.centbudget.R;
import com.perqin.centbudget.db.Account;
import com.perqin.centbudget.db.DbFactory;
import com.perqin.centbudget.ui.activity.EditAccountActivity;
import com.perqin.centbudget.ui.activity.EditEntryActivity;
import com.perqin.centbudget.ui.adapter.AccountsPagerAdapter;
import com.perqin.centbudget.utils.AppUtils;

public class AccountsFragment extends Fragment
        implements
        Toolbar.OnMenuItemClickListener {
//        AccountsPagerAdapter.OnDataSetChangedListener {
    private Toolbar mToolbar;
    private TabLayout mTabBar;
    private ViewPager mViewPager;
    private AccountsPagerAdapter mPagerAdapter;
    private FloatingActionButton mAddEntryFAB;

    private OnFragmentInteractionListener mListener;

    public static AccountsFragment newInstance(OnFragmentInteractionListener l) {
        return new AccountsFragment();
    }

    public AccountsFragment() {}

    public void refreshViewPager() {
        refreshViewPager(0);
    }

    public void refreshViewPager(int current) {
        mViewPager.setAdapter(null);
        mPagerAdapter.updateDataSet();
        mViewPager.setAdapter(mPagerAdapter);
        mTabBar.setupWithViewPager(mViewPager);
        mViewPager.setCurrentItem(current == AccountsPagerAdapter.INDEX_LAST ? mPagerAdapter.getCount() - 1 : current);
    }

    public void addAccount(Account account) {
        mPagerAdapter.addAccount(getActivity(), account);
        refreshViewPager(AccountsPagerAdapter.INDEX_LAST);
    }

    public void updateAccount(Account account) {
        mPagerAdapter.updateAccount(getActivity(), account);
        refreshViewPager(mViewPager.getCurrentItem());
    }

    private void deleteAccount() {
        // TODO : Confirm delete
        int newCurrent = mPagerAdapter.deleteAccount(getActivity(), mViewPager.getCurrentItem());
        refreshViewPager(newCurrent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_accounts, container, false);

        mToolbar = (Toolbar)view.findViewById(R.id.accounts_title_toolbar);
        mTabBar = (TabLayout)view.findViewById(R.id.accounts_tab_bar);
        mViewPager = (ViewPager)view.findViewById(R.id.accounts_view_pager);
        mPagerAdapter = new AccountsPagerAdapter(getActivity(), getChildFragmentManager());
        mAddEntryFAB = (FloatingActionButton)view.findViewById(R.id.add_entry_fab);

        mToolbar.setNavigationIcon(R.drawable.ic_menu_white);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onNavigationIconClicked();
                }
            }
        });
        mToolbar.setTitle(R.string.accounts);
        mToolbar.inflateMenu(R.menu.menu_accounts);
        mToolbar.setOnMenuItemClickListener(this);

        mTabBar.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabBar.setSelectedTabIndicatorColor(ContextCompat.getColor(getActivity(), R.color.white));

        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mToolbar.getMenu().findItem(R.id.action_edit_account).setVisible(position != 0);
                mToolbar.getMenu().findItem(R.id.action_delete_account).setVisible(position != 0);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        mPagerAdapter.updateDataSet();
        mPagerAdapter.notifyDataSetChanged();
        mViewPager.setCurrentItem(0);
        mTabBar.setupWithViewPager(mViewPager);

        mAddEntryFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO
                Intent intent = new Intent(getActivity(), EditEntryActivity.class);
                intent.putExtra(AppUtils.EXTRA_REQUEST_CODE, AppUtils.REQUEST_ADD_ENTRY);
                getActivity().startActivityForResult(intent, AppUtils.REQUEST_ADD_ENTRY);
            }
        });

        return view;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_account:
                if (mListener != null) {
                    mListener.onAddAccountActionClicked();
                }
                return true;
            case R.id.action_edit_account:
                if (mListener != null) {
                    mListener.onEditAccountActionClicked(mPagerAdapter.getAccount(mViewPager.getCurrentItem()));
                }
                return true;
            case R.id.action_delete_account:
                deleteAccount();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public interface OnFragmentInteractionListener {
        void onNavigationIconClicked();
        void onAddAccountActionClicked();
        void onEditAccountActionClicked(Account account);
        void onAddEntryFABClicked();
    }
}
