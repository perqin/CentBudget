package com.perqin.centbudget.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.perqin.centbudget.R;

import java.util.ArrayList;

public class AccountsRecyclerAdapter extends RecyclerView.Adapter<AccountsRecyclerAdapter.ViewHolder> {
    private ArrayList<DataItem> mDataSet = new ArrayList<>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_iet_entry, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        // TODO
    }

    @Override
    public int getItemCount() {
        // TODO
        return 10;
//        return mDataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // TODO
        public ViewHolder(View v) {
            super(v);
        }
    }

    public static class DataItem {
        // TODO
    }
}
