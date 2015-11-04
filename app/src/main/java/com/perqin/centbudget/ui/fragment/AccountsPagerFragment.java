package com.perqin.centbudget.ui.fragment;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.perqin.centbudget.R;
import com.perqin.centbudget.ui.adapter.AccountsRecyclerAdapter;

public class AccountsPagerFragment extends Fragment {
//    private Context mActivityContext = null;
    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";

    // TOxDO: Rename and change types of parameters
    private String mParam1;
//    private String mParam2;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private AccountsRecyclerAdapter mAdapter;

    public static AccountsPagerFragment newInstance(Context activity, String param1) {
        AccountsPagerFragment fragment = new AccountsPagerFragment();
//        fragment.setActivityContext(activity);
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public AccountsPagerFragment() {}

//    public void setActivityContext(Context activity) {
//        mActivityContext = activity;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_accounts_pager, container, false);

        mRecyclerView = (RecyclerView)view.findViewById(R.id.accounts_pager_recycler_view);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new AccountsRecyclerAdapter(getActivity());

        if (getArguments() != null) {
            mAdapter.mString = getArguments().getString(ARG_PARAM1);
        }

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }
}
