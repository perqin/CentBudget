package com.perqin.centbudget.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
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

import com.perqin.centbudget.R;
import com.perqin.centbudget.db.Account;
import com.perqin.centbudget.db.DbFactory;
import com.perqin.centbudget.ui.adapter.AccountsPagerAdapter;

public class AccountsFragment extends Fragment
        implements
        Toolbar.OnMenuItemClickListener,
        AccountsPagerAdapter.OnDataSetChangedListener {
      // TOxDO: Rename parameter arguments, choose names that match
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";

    // TOxDO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    private Toolbar mToolbar;
    private TabLayout mTabBar;
    private ViewPager mViewPager;
    private AccountsPagerAdapter mPagerAdapter;

    private OnFragmentInteractionListener mListener;

    // TOxDO: Rename and change types and number of parameters
    // public static AccountsFragment newInstance(String param1, String param2);
    public static AccountsFragment newInstance(OnFragmentInteractionListener l) {
        AccountsFragment fragment = new AccountsFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    public AccountsFragment() {}

    public void refreshViewPager() {
        refreshViewPager(0);
    }

    public void refreshViewPager(int current) {
        mPagerAdapter.refreshAccounts(current);
    }

    // TODO
    private void addAccount() {
        Account account = new Account();
        account.display_name = "Acc " + mPagerAdapter.getCount();
        mPagerAdapter.addAccount(account);
    }

    private void deleteAccount() {
        mPagerAdapter.deleteAccount(mViewPager.getCurrentItem());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_accounts, container, false);

        mToolbar = (Toolbar)view.findViewById(R.id.accounts_title_toolbar);
        mTabBar = (TabLayout)view.findViewById(R.id.accounts_tab_bar);
        mViewPager = (ViewPager)view.findViewById(R.id.accounts_view_pager);
        mPagerAdapter = new AccountsPagerAdapter(getActivity(), getChildFragmentManager());

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
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
            @Override
            public void onPageSelected(int position) {
                mToolbar.getMenu().findItem(R.id.action_edit_account).setVisible(position != 0);
                mToolbar.getMenu().findItem(R.id.action_delete_account).setVisible(position != 0);
            }
            @Override
            public void onPageScrollStateChanged(int state) {}
        });

        mPagerAdapter.setOnDataSetChangedListener(this);
        mPagerAdapter.refreshAccounts(0);

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
//                addAccount();
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
//                if (mListener != null) {
//                    mListener.onDeleteAccountActionClicked(mPagerAdapter.getAccount(mViewPager.getCurrentItem()));
//                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onDataSetChanged(int current) {
        // TODO : handle last
        mViewPager.setCurrentItem(0);
        mTabBar.setupWithViewPager(mViewPager);
        if (current == AccountsPagerAdapter.INDEX_LAST) {
            mViewPager.setCurrentItem(mPagerAdapter.getCount() - 1);
        } else {
            mViewPager.setCurrentItem(current);
        }
    }

    public interface OnFragmentInteractionListener {
        void onNavigationIconClicked();
        void onAddAccountActionClicked();
        void onEditAccountActionClicked(Account account);
        void onDeleteAccountActionClicked(Account account);
    }
}
