package com.akhil.akhildixit.bits.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.akhil.akhildixit.bits.R;
import com.akhil.akhildixit.bits.adapters.CustomAccount;


public class Account_Fragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager mLayoutManager;
    Button close;

    public Account_Fragment() {

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_account, container, false);
        recyclerView=view.findViewById(R.id.accountList);
        mLayoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        close=view.findViewById(R.id.account_popUp_close);
        adapter=new CustomAccount(view);
        recyclerView.setAdapter(adapter);

        setListeners(view);
        return view;
    }

    public void setListeners(final View v)
    {
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                v.findViewById(R.id.accountPopUp).setVisibility(View.GONE);
            }
        });
    }

}
