package com.perqin.centbudget.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.perqin.centbudget.R;

public class EditAccountActivity extends AppCompatActivity {
    private Toolbar mToolbar;

    private void saveChanges() {
        // TODO
        finish();
    }

    private void confirmDelete() {
        // TODO
        finish();
    }

    private void confirmDiscard() {
        // TODO
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);

        mToolbar = (Toolbar)findViewById(R.id.edit_account_toolbar);

        mToolbar.setNavigationIcon(R.drawable.ic_done_white_24dp);
        // TODO
        mToolbar.setTitle(R.string.title_activity_edit_account);

        setSupportActionBar(mToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit_account, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                saveChanges();
                return true;
            case R.id.action_delete_editing_account:
                confirmDelete();
                return true;
            case R.id.action_discard_changes:
                confirmDiscard();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
