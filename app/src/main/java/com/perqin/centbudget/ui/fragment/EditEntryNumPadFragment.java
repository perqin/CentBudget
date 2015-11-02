package com.perqin.centbudget.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.perqin.centbudget.R;
import com.perqin.centbudget.ui.adapter.EditEntryNumPadGridAdapter;
import com.perqin.centbudget.utils.DebugUtils;

public class EditEntryNumPadFragment extends Fragment {
    private OnFragmentInteractionListener mListener;
    private GridView mGridView;
    private EditEntryNumPadGridAdapter mAdapter;

    public static EditEntryNumPadFragment newInstance() {
        return new EditEntryNumPadFragment();
    }

    public EditEntryNumPadFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_edit_entry_num_pad, container, false);

        mGridView = (GridView)v.findViewById(R.id.edit_entry_num_pad_grid_view);
        mAdapter = new EditEntryNumPadGridAdapter(getContext());

        mGridView.setAdapter(mAdapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mListener != null) {
                    mListener.onPadButtonClicked(position);
                }
            }
        });
        mGridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (mListener != null && position == EditEntryNumPadGridAdapter.DEL_BUTTON_POSITION) {
                    mListener.onDelButtonLongClicked();
                    return true;
                }
                return false;
            }
        });

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
        void onPadButtonClicked(int position);
        void onDelButtonLongClicked();
    }
}
