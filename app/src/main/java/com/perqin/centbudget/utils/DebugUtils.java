package com.perqin.centbudget.utils;

import android.content.Context;
import android.widget.Toast;

public class DebugUtils {
    public static void makeToast(Context context, String text) {
        makeToast(context, text, false);
    }

    public static void makeToast(Context context, String text, boolean isLongToast) {
        Toast.makeText(context, text, isLongToast ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT).show();
    }
}
