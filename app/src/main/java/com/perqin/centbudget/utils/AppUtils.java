package com.perqin.centbudget.utils;

import android.os.Bundle;

import com.perqin.centbudget.db.Account;

public class AppUtils {
    // Request code for EditAccountActivity
    public static final int REQUEST_ADD_ACCOUNT = 0;
    public static final int REQUEST_EDIT_ACCOUNT = 1;
    // Request code for EditEntryActivity
    public static final int REQUEST_ADD_ENTRY = 2;
    public static final int REQUEST_EDIT_ENTRY = 3;

    // Extra keys
    public static final String EXTRA_REQUEST_CODE = "REQUEST_CODE";

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
