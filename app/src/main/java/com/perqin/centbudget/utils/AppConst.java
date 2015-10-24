package com.perqin.centbudget.utils;

import android.os.Bundle;

import com.perqin.centbudget.db.Account;

public class AppConst {
    // Request code for EditAccountActivity
    public static final int REQUEST_ADD_ACCOUNT = 0;
    public static final int REQUEST_EDIT_ACCOUNT = 1;

    // Extra keys
    public static final String EXTRA_ID = "ID";
    public static final String EXTRA_DISPLAY_NAME = "DISPLAY_NAME";

    public static Bundle getExtrasFromAccount(Account account) {
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_ID, account._id);
        bundle.putString(EXTRA_DISPLAY_NAME, account.display_name);
        return bundle;
    }
}
