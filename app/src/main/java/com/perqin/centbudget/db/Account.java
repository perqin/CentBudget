package com.perqin.centbudget.db;

import android.os.Bundle;

public class Account {
    public static final String EXTRA_ID = "ID";
    public static final String EXTRA_DISPLAY_NAME = "DISPLAY_NAME";

    public int _id = 0;
    public String display_name = "";

    public static Account fromBundle(Bundle bundle) {
        Account account = new Account();
        account._id = bundle.getInt(EXTRA_ID);
        account.display_name = bundle.getString(EXTRA_DISPLAY_NAME);
        return account;
    }

    public static Bundle toBundle(Account account) {
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_ID, account._id);
        bundle.putString(EXTRA_DISPLAY_NAME, account.display_name);
        return bundle;
    }
}
