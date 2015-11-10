package com.perqin.centbudget.db;

import android.content.Context;

import java.util.ArrayList;

public class DbFactory {
    public static void createInAccounts(Context context, Account account) {
        CentBudgetDbHelper.getInstance(context).createInAccounts(account);
    }

    public static ArrayList<Account> readAllInAccounts(Context context) {
        return CentBudgetDbHelper.getInstance(context).readAllInAccounts();
    }

    public static void updateInAccounts(Context context, Account account) {
        CentBudgetDbHelper.getInstance(context).updateInAccounts(account);
    }

    public static void deleteInAccount(Context context, Account account) {
        CentBudgetDbHelper.getInstance(context).deleteInAccounts(account);
    }
}
