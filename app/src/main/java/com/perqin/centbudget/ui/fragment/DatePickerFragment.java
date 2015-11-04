package com.perqin.centbudget.ui.fragment;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    private OnDateSetListener mListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH);
        int day = now.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int literalYear, int monthOfYear, int dayOfMonth) {
        if (mListener != null) {
            mListener.onDateSet(view, literalYear - 1900, monthOfYear, dayOfMonth);
        }
    }

    public interface OnDateSetListener {
        void onDateSet(DatePicker view, int yearSince1900, int monthOfYear, int dayOfMonth);
    }

    public void setOnDateSetListener(OnDateSetListener l) {
        mListener = l;
    }
}
