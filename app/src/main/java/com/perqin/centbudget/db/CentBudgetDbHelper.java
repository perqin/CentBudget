package com.perqin.centbudget.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Cent Budget database change log
 *
 * These comments are used for upgrading database.
 *
 * -----------------------------------------------------------------------------
 * Version  : 1
 * Date     : 2015/10/26
 * Log      :
 *      Add     database    cent_budget_db
 *      Add     table       cent_budget_db:accounts
 *      Add     column      cent_budget_db:accounts:_id
 *      Add     column      cent_budget_db:accounts:display_name
 *      Add     column      cent_budget_db:accounts:_aid
 *      Add     table       cent_budget_db:account_all
 *      Add     column      cent_budget_db:account_all:_id
 *      Add     column      cent_budget_db:account_all:date
 *      Add     column      cent_budget_db:account_all:type
 *      Add     column      cent_budget_db:account_all:category_id
 *      Add     column      cent_budget_db:account_all:detail
 *      Add     column      cent_budget_db:account_all:value
 *      Add     column      cent_budget_db:account_all:transfer_info
 */

public class CentBudgetDbHelper extends SQLiteOpenHelper {
    // Singleton
    private static CentBudgetDbHelper mInstance;

    // Version
    public static final int DB_VERSION = 1;

    // Database name
    public static final String DB_NAME = "cent_budget_db";

    // Tables
    public static final String TABLE_ACCOUNTS = "accounts";
    public static final String TABLE_ACCOUNT_ALL = "account_all";
    public static final String TABLE_ACCOUNT_PREFIX = "account_";

    // Columns - Common
    public static final String COLUMN_ID = "_id";

    // Columns - accounts
    public static final String COLUMN_ACCOUNTS_DISPLAY_NAME = "display_name";

    // Collumns - account_xxxx
    public static final String COLUMN_ACCOUNT_DATE = "date";
    public static final String COLUMN_ACCOUNT_TYPE = "type";
    public static final String COLUMN_ACCOUNT_CATEGORY_ID = "category_id";
    public static final String COLUMN_ACCOUNT_DETAIL = "detail";
    public static final String COLUMN_ACCOUNT_VALUE = "value";
    public static final String COLUMN_ACCOUNT_TRANSFER_INFO = "transfer_info";

    // Queries
    public static final String Q_CREATE_TABLE_ACCOUNTS = "CREATE TABLE " + TABLE_ACCOUNTS + " ( "
            + COLUMN_ID + " INTEGER PRIMARY KEY "
            + ", " + COLUMN_ACCOUNTS_DISPLAY_NAME + " TEXT "
            + ")";
    public static final String Q_CREATE_TABLE_ACCOUNT_PRE = "CREATE TABLE ";
    public static final String Q_CREATE_TABLE_ACCOUNT_POST = " ( "
            + COLUMN_ID + " INTEGER PRIMARY KEY "
            + ", " + COLUMN_ACCOUNT_DATE + " TEXT "
            + ", " + COLUMN_ACCOUNT_TYPE + " INTEGER "
            + ", " + COLUMN_ACCOUNT_CATEGORY_ID + " INTEGER "
            + ", " + COLUMN_ACCOUNT_DETAIL + " TEXT "
            + ", " + COLUMN_ACCOUNT_VALUE + " INTEGER "
            + ", " + COLUMN_ACCOUNT_TRANSFER_INFO + " TEXT "
            + ")";

    private CentBudgetDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static synchronized CentBudgetDbHelper getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new CentBudgetDbHelper(context.getApplicationContext());
        }
        return mInstance;
    }

    // CRUD - Create Read Update Delete

    public boolean createInAccounts(Account account) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ACCOUNTS_DISPLAY_NAME, account.display_name);
        long status = database.insert(TABLE_ACCOUNTS, null, values);
        return status != -1;
    }

    public ArrayList<Account> readAllInAccounts() {
        ArrayList<Account> list = new ArrayList<Account>();
        String query = "SELECT * FROM " + TABLE_ACCOUNTS;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Account account = new Account();
                account._id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ID)));
                account.display_name = cursor.getString(cursor.getColumnIndex(COLUMN_ACCOUNTS_DISPLAY_NAME));
                list.add(account);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public boolean deleteInAccounts(Account account) {
        SQLiteDatabase database = this.getWritableDatabase();
        String where = COLUMN_ID + " = ?";
        String[] args = {String.valueOf(account._id)};
        int deleted = database.delete(TABLE_ACCOUNTS, where, args);
        return deleted != 0;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create tables
        db.execSQL(Q_CREATE_TABLE_ACCOUNTS);
        db.execSQL(Q_CREATE_TABLE_ACCOUNT_PRE + TABLE_ACCOUNT_ALL + Q_CREATE_TABLE_ACCOUNT_POST);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO
    }
}
