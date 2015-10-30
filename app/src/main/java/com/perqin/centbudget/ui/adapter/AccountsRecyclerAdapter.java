package com.perqin.centbudget.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.perqin.centbudget.R;
import com.perqin.centbudget.ui.activity.EditEntryActivity;

import java.util.ArrayList;

public class AccountsRecyclerAdapter extends RecyclerView.Adapter<AccountsRecyclerAdapter.ViewHolder> {
    public String mString;
//    private Context mContext;
    private ArrayList<DataItem> mDataSet = new ArrayList<>();

//    public AccountsRecyclerAdapter(Context context) {
//        mContext = context;
//    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_iet_entry, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        // TODO
        viewHolder.mEntryCategoryTextView.setText(mString);
    }

    @Override
    public int getItemCount() {
        // TODO
        return 10;
//        return mDataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mEntryIconImageView;
        public TextView mEntryCategoryTextView;
        public TextView mEntryDetailTextView;
        public TextView mEntryValueTextView;
        public TextView mEntryDateTextView;
        public ViewHolder(View v) {
            super(v);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.getContext().startActivity(new Intent(v.getContext(), EditEntryActivity.class));
                }
            });
            mEntryIconImageView = (ImageView)v.findViewById(R.id.entry_icon_image_view);
            mEntryCategoryTextView = (TextView)v.findViewById(R.id.entry_category_text_view);
            mEntryDetailTextView = (TextView)v.findViewById(R.id.entry_detail_text_view);
            mEntryValueTextView = (TextView)v.findViewById(R.id.entry_value_text_view);
            mEntryDateTextView = (TextView)v.findViewById(R.id.entry_date_text_view);
        }
    }

    public static class DataItem {
        // TODO
    }
}
