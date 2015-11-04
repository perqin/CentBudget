package com.perqin.centbudget.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.perqin.centbudget.R;
import com.perqin.centbudget.db.IetEntry;
import com.perqin.centbudget.ui.adapter.EditEntryNumPadGridAdapter;
import com.perqin.centbudget.ui.adapter.EditEntryPagerAdapter;
import com.perqin.centbudget.ui.fragment.EditEntryDetailsFragment;
import com.perqin.centbudget.ui.fragment.EditEntryNumPadFragment;
import com.perqin.centbudget.utils.AppUtils;
import com.perqin.centbudget.utils.DebugUtils;

import java.util.ArrayList;

public class EditEntryActivity extends AppCompatActivity implements
        EditEntryNumPadFragment.OnFragmentInteractionListener,
        EditEntryDetailsFragment.OnFragmentInteractionListener {
    private Toolbar mToolbar;
    private TabLayout mTabBar;
    private ViewPager mViewPager;
    private EditEntryPagerAdapter mPagerAdapter;
    private TextView mAmountTextView;

    private double mAmount = 0;
    private String mAmountString = "";
//    private Bundle mExtrasFromIntent;
    private IetEntry mEditingEntry;

    private void updateAmount() {
        // TODO : set currency
        mAmount = AppUtils.digitsStringToDouble(mAmountString);
        mAmountTextView.setText(mAmountString + "$");
        DebugUtils.makeToast(this, String.valueOf(mAmount));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_entry);
//        mExtrasFromIntent = getIntent().getExtras();
        mEditingEntry = IetEntry.fromBundle(getIntent().getExtras());

        mToolbar = (Toolbar)findViewById(R.id.edit_entry_toolbar);
        mTabBar = (TabLayout)findViewById(R.id.edit_entry_tab_bar);
        mViewPager = (ViewPager)findViewById(R.id.edit_entry_view_pager);
        mPagerAdapter = new EditEntryPagerAdapter(getSupportFragmentManager(), mEditingEntry);
        mAmountTextView = (TextView)findViewById(R.id.edit_entry_amount_text_view);

        mToolbar.setNavigationIcon(R.drawable.ic_done_white_24dp);

        mViewPager.setAdapter(mPagerAdapter);

        mTabBar.setupWithViewPager(mViewPager);
        //noinspection ConstantConditions
        mTabBar.getTabAt(0).setIcon(R.drawable.ic_dialpad_white_24dp);
        //noinspection ConstantConditions
        mTabBar.getTabAt(1).setIcon(R.drawable.ic_list_white_24dp);

        mAmountString = "0";
        updateAmount();

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

    @Override
    public void onPadButtonClicked(int position, int type, int value) {
        if (type == EditEntryNumPadFragment.BUTTON_DIGIT) {
            if (mAmountString.equals("0")) {
                mAmountString = String.valueOf(value);
            } else {
                mAmountString = mAmountString + String.valueOf(value);
            }
        } else if (type == EditEntryNumPadFragment.BUTTON_DOT && !mAmountString.contains(".")) {
            mAmountString = mAmountString + ".";
        } else if (type == EditEntryNumPadFragment.BUTTON_DEL && !mAmountString.equals("0")) {
            if (mAmountString.length() == 1) {
                mAmountString = "0";
            } else {
                mAmountString = mAmountString.substring(0, mAmountString.length() - 1);
            }
        }
        updateAmount();
    }

    @Override
    public void onDelButtonLongClicked() {
        mAmountString = "0";
        updateAmount();
    }
}
