package com.perqin.centbudget.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.perqin.centbudget.R;
import com.perqin.centbudget.db.IetEntry;
import com.perqin.centbudget.ui.activity.EditEntryActivity;
import com.perqin.centbudget.utils.AppUtils;

import java.util.ArrayList;

public class AccountsRecyclerAdapter extends RecyclerView.Adapter<AccountsRecyclerAdapter.ViewHolder> {
    public String mString;
    private Context mActivityContext;
    private ArrayList<DataItem> mDataSet = new ArrayList<>();

    public AccountsRecyclerAdapter(Context activity) {
        mActivityContext = activity;
    }

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
                    if (v.getContext() instanceof Activity) {
                        Intent intent = new Intent(v.getContext(), EditEntryActivity.class);
                        intent.putExtra(AppUtils.EXTRA_REQUEST_CODE, AppUtils.REQUEST_EDIT_ENTRY);
                        // TODO : get list
                        IetEntry ietEntry = new IetEntry();
                        ietEntry._id = 123;
                        ietEntry.category_id = 6666;
                        ietEntry.date = "2015-11-22 12:34";
                        ietEntry.detail = "Waimai hahaha";
                        ietEntry.transfer_info = "{}";
                        ietEntry.type = IetEntry.EXPENSE;
                        ietEntry.value = 12;
                        intent.putExtras(IetEntry.toBundle(ietEntry));
                        ((Activity)v.getContext()).startActivityForResult(intent, AppUtils.REQUEST_EDIT_ENTRY);
                    }
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
