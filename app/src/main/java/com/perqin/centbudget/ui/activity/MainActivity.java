package com.perqin.centbudget.ui.activity;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.perqin.centbudget.R;
import com.perqin.centbudget.ui.fragment.AccountsFragment;

public class MainActivity extends AppCompatActivity implements
        AccountsFragment.OnFragmentInteractionListener,
        NavigationView.OnNavigationItemSelectedListener {
    // Static variables for Navigation Menu
    public static final int NAV_ACCOUNTS = 0;
    public static final int NAV_CHARTS = 1;
    public static final int NAV_SETTINGS = 2;
    public static final boolean[] NAV_CHECKABLE = {true, true, false};

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavDrawer;

    private void navigateTo(int navPage) {
        switch (navPage) {
            case NAV_ACCOUNTS:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_frame, AccountsFragment.newInstance(this))
                        .commit();
                break;
            case NAV_CHARTS:
                // TODO
//                fragment = AccountsFragment.newInstance();
//                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, fragment).commit();
                break;
            case NAV_SETTINGS:
                // TODO
//                fragment = AccountsFragment.newInstance();
                break;
            default:
                break;
        }
        if (NAV_CHECKABLE[navPage]) {
            mNavDrawer.getMenu().getItem(navPage).setChecked(true);
        }
        mDrawerLayout.closeDrawer(mNavDrawer);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout)findViewById(R.id.main_drawer_layout);
        mNavDrawer = (NavigationView)findViewById(R.id.nav_drawer);

        mNavDrawer.setNavigationItemSelectedListener(this);

        navigateTo(NAV_ACCOUNTS);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_accounts:
                navigateTo(NAV_ACCOUNTS);
                return true;
            case R.id.nav_charts:
                navigateTo(NAV_CHARTS);
                return true;
            case R.id.nav_settings:
                navigateTo(NAV_SETTINGS);
                return true;
            default:
                return false;
        }
    }

    // AccountsFragment.OnFragmentInteractionListener
    @Override
    public void onNavigationIconClicked() {
        mDrawerLayout.openDrawer(mNavDrawer);
    }
}