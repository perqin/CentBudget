package com.perqin.centbudget.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.perqin.centbudget.R;

public class EditEntryNumPadGridAdapter extends BaseAdapter {
    public static final int DEL_BUTTON_POSITION = 11;
    private static int[] mButtonTextRes = {
            R.string.num_pad_1,
            R.string.num_pad_2,
            R.string.num_pad_3,
            R.string.num_pad_4,
            R.string.num_pad_5,
            R.string.num_pad_6,
            R.string.num_pad_7,
            R.string.num_pad_8,
            R.string.num_pad_9,
            R.string.num_pad_dot,
            R.string.num_pad_0,
            R.string.num_pad_del
    };

    private Context mContext;

    public EditEntryNumPadGridAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return 12;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v;
        if (convertView == null) {
            v = ((LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.grid_item_edit_entry_num_pad, parent, false);
        } else {
            v = convertView;
        }
        TextView button = (TextView)v.findViewById(R.id.edit_entry_num_pad_button_text_view);
        button.setText(mButtonTextRes[position]);
        return v;
    }
}
