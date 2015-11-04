package com.perqin.centbudget.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.perqin.centbudget.R;

public class EditEntryDetailsFragment extends Fragment {
    private static final String ARG_DATE = "DATE";
    private static final String ARG_TIME = "TIME";
    private static final String ARG_DETAIL = "DETAIL";

    private String mDate;
    private String mTime;
    private String mDetail;

    private OnFragmentInteractionListener mListener;

    public static EditEntryDetailsFragment newInstance(String dateAndTime, String detail) {
        EditEntryDetailsFragment fragment = new EditEntryDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_DATE, dateAndTime.substring(0, 10));
        args.putString(ARG_TIME, dateAndTime.substring(11, 16));
        args.putString(ARG_DETAIL, detail);
        fragment.setArguments(args);
        return fragment;
    }

    public EditEntryDetailsFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mDate = getArguments().getString(ARG_DATE);
            mTime = getArguments().getString(ARG_TIME);
            mDetail = getArguments().getString(ARG_DETAIL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_edit_entry_details, container, false);

        RelativeLayout dateItem = (RelativeLayout)v.findViewById(R.id.edit_entry_date_button);
        TextView dateTextView = (TextView)v.findViewById(R.id.edit_entry_date_text_view);
        RelativeLayout timeItem = (RelativeLayout)v.findViewById(R.id.edit_entry_time_button);
        TextView timeTextView = (TextView)v.findViewById(R.id.edit_entry_time_text_view);
        RelativeLayout detailItem = (RelativeLayout)v.findViewById(R.id.edit_entry_detail_button);
        TextView detailTextView = (TextView)v.findViewById(R.id.edit_entry_detail_text_view);

        dateItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO
            }
        });

        dateTextView.setText(mDate);

        timeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO
            }
        });

        timeTextView.setText(mTime);

        detailItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO
            }
        });

        detailTextView.setText(mDetail);

        return v;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
//        public void onFragmentInteraction(Uri uri);
    }
}
