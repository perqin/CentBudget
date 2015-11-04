package com.perqin.centbudget.ui.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.perqin.centbudget.R;

public class EditEntryDetailDialogFragment extends DialogFragment {
    private String mEditingEntryDetail;
    private EditText mEditEntryDetailEditText;

    private OnDetailChangedListener mListener;

    public void setDefaultDetailText(String defaultDetailText) {
        mEditingEntryDetail = defaultDetailText;
    }

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_entry_detail, null);
        mEditEntryDetailEditText = (EditText)v.findViewById(R.id.edit_entry_detail_edit_text);
        mEditEntryDetailEditText.setText(mEditingEntryDetail);
        mEditEntryDetailEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    EditEntryDetailDialogFragment.this.getDialog().getWindow()
                            .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                }
            }
        });
        builder.setView(v)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        if (mListener != null) {
                            mListener.onDetailChanged(mEditEntryDetailEditText.getText().toString());
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        EditEntryDetailDialogFragment.this.getDialog().cancel();
                    }
                })
                .setTitle(R.string.edit_detail);
        return builder.create();
    }

    public interface OnDetailChangedListener {
        void onDetailChanged(String detail);
    }

    public void setOnDetailChangedListener(OnDetailChangedListener l) {
        mListener = l;
    }
}
