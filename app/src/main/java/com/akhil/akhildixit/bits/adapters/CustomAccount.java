package com.akhil.akhildixit.bits.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.akhil.akhildixit.bits.R;
import com.akhil.akhildixit.bits.SettersGetters.BuySell;

import java.util.ArrayList;

public class CustomAccount  extends RecyclerView.Adapter<CustomAccount.myViewHolder >{

    ArrayList arrayList;
    View v;

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.account_custom,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
v.findViewById(R.id.accountPopUp).setVisibility(View.VISIBLE);
            }
        });



    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public static class myViewHolder extends RecyclerView.ViewHolder {

        LinearLayout layout;
        public myViewHolder(View v)
        {
            super(v);

            layout=v.findViewById(R.id.account_layout);


        }
    }
    public CustomAccount(ArrayList<BuySell> arrayList, View view)  {
        this.arrayList=arrayList;



    }
    public CustomAccount(View view)
    {
this.v=view;
    }



}
