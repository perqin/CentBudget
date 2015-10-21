package com.perqin.centbudget.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Cent Budget database change log
 *
 * These comments are used for upgrading database.
 *
 * -----------------------------------------------------------------------------
 * Version  : 1
 * Date     : 2015/10/21
 * Log      :
 *      Add     table   accounts
 *
 */

public class CentBudgetDbHelper extends SQLiteOpenHelper {
    public CentBudgetDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO
    }
}
