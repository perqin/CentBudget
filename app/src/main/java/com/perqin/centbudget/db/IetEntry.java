package com.perqin.centbudget.db;

import android.os.Bundle;

public class IetEntry {
    public static final int INCOME = 0;
    public static final int EXPENSE = 1;
    public static final int TRANSFER = 2;

    public static final String EXTRA_ID = "_ID";
    public static final String EXTRA_DATE = "DATE";
    public static final String EXTRA_TYPE = "TYPE";
    public static final String EXTRA_CATEGORY_ID = "CATEGORY_ID";
    public static final String EXTRA_DETAIL = "DETAIL";
    public static final String EXTRA_VALUE = "VALUE";
    public static final String EXTRA_TRANSFER_INFO = "TRANSFER_INFO";

    public int _id;
    public String date = "1970-01-01";
    public int type = INCOME;
    public int category_id;
    public String detail = "";
    public double value;
    public String transfer_info = "{}";

    public static Bundle toBundle(IetEntry entry) {
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_ID, entry._id);
        bundle.putString(EXTRA_DATE, entry.date);
        bundle.putInt(EXTRA_TYPE, entry.type);
        bundle.putInt(EXTRA_CATEGORY_ID, entry.category_id);
        bundle.putString(EXTRA_DETAIL, entry.detail);
        bundle.putDouble(EXTRA_VALUE, entry.value);
        bundle.putString(EXTRA_TRANSFER_INFO, entry.transfer_info);
        return bundle;
    }

    public static IetEntry fromBundle(Bundle bundle) {
        IetEntry ietEntry = new IetEntry();
        ietEntry._id = bundle.getInt(EXTRA_ID, 0);
        ietEntry.date = bundle.getString(EXTRA_DATE, "1970-01-01 00:00");
        ietEntry.type = bundle.getInt(EXTRA_TYPE, INCOME);
        ietEntry.category_id = bundle.getInt(EXTRA_CATEGORY_ID, 0);
        ietEntry.detail = bundle.getString(EXTRA_DETAIL, "");
        ietEntry.value = bundle.getDouble(EXTRA_VALUE, 0.0);
        ietEntry.transfer_info = bundle.getString(EXTRA_TRANSFER_INFO, "{}");
        return ietEntry;
    }
}
