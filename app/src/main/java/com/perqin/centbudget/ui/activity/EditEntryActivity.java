package com.perqin.centbudget.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.perqin.centbudget.R;
import com.perqin.centbudget.ui.adapter.EditEntryPagerAdapter;

public class EditEntryActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private TabLayout mTabBar;
    private ViewPager mViewPager;
    private EditEntryPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_entry);

        mToolbar = (Toolbar)findViewById(R.id.edit_entry_toolbar);
        mTabBar = (TabLayout)findViewById(R.id.edit_entry_tab_bar);
        mViewPager = (ViewPager)findViewById(R.id.edit_entry_view_pager);
        mPagerAdapter = new EditEntryPagerAdapter(getSupportFragmentManager());

        mToolbar.setNavigationIcon(R.drawable.ic_done_white_24dp);

        mViewPager.setAdapter(mPagerAdapter);

        mTabBar.setupWithViewPager(mViewPager);

        setSupportActionBar(mToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit_entry, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
