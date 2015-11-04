package com.perqin.centbudget.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.perqin.centbudget.R;

import android.text.format.DateFormat;
import android.widget.TimePicker;

import java.util.Date;

public class EditEntryDetailsFragment extends Fragment {
    private static final String ARG_DATE = "DATE";
    private static final String ARG_TIME = "TIME";
    private static final String ARG_DETAIL = "DETAIL";

    private TextView mDateTextView;
    private TextView mTimeTextView;
    private TextView mDetailTextView;

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

    private void updateDate() {
        mDateTextView.setText(mDate);
        mTimeTextView.setText(mTime);
        mListener.onDateStringChanged(mDate + " " + mTime);
    }

    private void updateDetail() {
        mDetailTextView.setText(mDetail);
        mListener.onDetailStringChanged(mDetail);
    }

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

        RelativeLayout mDateItem = (RelativeLayout) v.findViewById(R.id.edit_entry_date_button);
        mDateTextView = (TextView)v.findViewById(R.id.edit_entry_date_text_view);
        RelativeLayout mTimeItem = (RelativeLayout) v.findViewById(R.id.edit_entry_time_button);
        mTimeTextView = (TextView)v.findViewById(R.id.edit_entry_time_text_view);
        RelativeLayout mDetailItem = (RelativeLayout) v.findViewById(R.id.edit_entry_detail_button);
        mDetailTextView = (TextView)v.findViewById(R.id.edit_entry_detail_text_view);

        mDateItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment datePickerFragment = new DatePickerFragment();
                datePickerFragment.setOnDateSetListener(new DatePickerFragment.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int yearSince1900, int monthOfYear, int dayOfMonth) {
                        //noinspection deprecation
                        Date date = new Date(yearSince1900, monthOfYear, dayOfMonth);
                        mDate = DateFormat.format("yyyy-MM-dd", date).toString();
                        updateDate();
                    }
                });
                datePickerFragment.show(getFragmentManager(), "datePickerFragment@EditEntryDetailsFragment");
            }
        });

        mDateTextView.setText(mDate);

        mTimeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerFragment timePickerFragment = new TimePickerFragment();
                timePickerFragment.setOnTimeSetListener(new TimePickerFragment.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Log.d("TIME", String.valueOf(hourOfDay));
                        //noinspection deprecation
                        Date date = new Date(0, 0, 0, hourOfDay, minute);
                        mTime = DateFormat.format("kk:mm", date).toString();
                        updateDate();
                    }
                });
                timePickerFragment.show(getFragmentManager(), "timePickerFragment@EditEntryDetailsFragment");
            }
        });

        mTimeTextView.setText(mTime);

        mDetailItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditEntryDetailDialogFragment editEntryDetailDialogFragment = new EditEntryDetailDialogFragment();
                editEntryDetailDialogFragment.setDefaultDetailText(mDetail);
                editEntryDetailDialogFragment.setOnDetailChangedListener(new EditEntryDetailDialogFragment.OnDetailChangedListener() {
                    @Override
                    public void onDetailChanged(String detail) {
                        mDetail = detail;
                        updateDetail();
                    }
                });
                editEntryDetailDialogFragment.show(getFragmentManager(), "editEntryDetailDialogFragment@EditEntryDetailsFragment");
            }
        });

        mDetailTextView.setText(mDetail);

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
        void onDateStringChanged(String date);
        void onDetailStringChanged(String detail);
    }
}
