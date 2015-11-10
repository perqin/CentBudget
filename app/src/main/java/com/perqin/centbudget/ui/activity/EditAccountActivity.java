package com.perqin.centbudget.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.perqin.centbudget.R;
import com.perqin.centbudget.db.Account;
import com.perqin.centbudget.db.DbFactory;
import com.perqin.centbudget.utils.AppUtils;

public class EditAccountActivity extends AppCompatActivity {
    public static final int RES_OK = 0;
    public static final int RES_CANCEL = 1;

    private Toolbar mToolbar;
    private EditText mAccountNameEditText;
    private Account mEditingAccount;
    private int mRequestCode;

    private void saveChanges() {
        mEditingAccount.display_name = mAccountNameEditText.getText().toString();
        Intent intent = new Intent();
        intent.putExtras(Account.toBundle(mEditingAccount));
        setResult(RES_OK, intent);
        finish();
    }

    private void confirmDelete() {
        // TODO
        finish();
    }

    private void confirmDiscard() {
        // TODO
        setResult(RES_CANCEL);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);

        Bundle bundle = getIntent().getExtras();
        mRequestCode = bundle.getInt(AppUtils.EXTRA_REQUEST_CODE);
        mToolbar = (Toolbar)findViewById(R.id.edit_account_toolbar);
        mAccountNameEditText = (EditText)findViewById(R.id.account_name_edit_text);

        mToolbar.setNavigationIcon(R.drawable.ic_done_white_24dp);
        if (mRequestCode == AppUtils.REQUEST_ADD_ACCOUNT) {
            mToolbar.setTitle(R.string.add_account);
            mEditingAccount = new Account();
        } else {
            mToolbar.setTitle(R.string.edit_account);
            mEditingAccount = Account.fromBundle(bundle);
            mAccountNameEditText.setText(mEditingAccount.display_name);
        }

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
