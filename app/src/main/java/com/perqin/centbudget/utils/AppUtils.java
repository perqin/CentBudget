package com.perqin.centbudget.utils;

import android.os.Bundle;

import com.perqin.centbudget.db.Account;

public class AppUtils {
    // Request code for EditAccountActivity
    public static final int REQUEST_ADD_ACCOUNT = 0;
    public static final int REQUEST_EDIT_ACCOUNT = 1;

    // Extra keys
    public static final String EXTRA_REQUEST_CODE = "REQUEST_CODE";
    public static final String EXTRA_ID = "ID";
    public static final String EXTRA_DISPLAY_NAME = "DISPLAY_NAME";
    public static final String EXTRA_CURRENT = "CURRENT";

    public static Bundle getExtrasFromAccount(Account account) {
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_ID, account._id);
        bundle.putString(EXTRA_DISPLAY_NAME, account.display_name);
        return bundle;
    }

    public static double digitsStringToDouble(String s) {
        double d;
        if (s.endsWith(".")) {
            d = Double.parseDouble(s.substring(0, s.length() - 1));
        } else {
            d = Double.parseDouble(s);
        }
        return d;
    }
}
