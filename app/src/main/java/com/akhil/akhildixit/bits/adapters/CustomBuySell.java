package com.akhil.akhildixit.bits.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.akhil.akhildixit.bits.R;
import com.akhil.akhildixit.bits.SettersGetters.BuySell;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CustomBuySell extends RecyclerView.Adapter<CustomBuySell.myViewHolder >{
    String json;
    ArrayList<BuySell> arrayList;
   View viewLayout;


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.buy_sell_custom,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {


        holder.name.setText(arrayList.get(position).id);
        holder.price.setText(arrayList.get(position).price);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewLayout.findViewById(R.id.popUp).setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder {

         TextView name;
        TextView price;
        LinearLayout layout;
        public myViewHolder(View v)
        {
       super(v);
         name=v.findViewById(R.id.buySellName);
         price=v.findViewById(R.id.buySellPrice);
         layout=v.findViewById(R.id.buySell);


        }
    }
    public CustomBuySell(ArrayList<BuySell> arrayList, View view)  {
        this.arrayList=arrayList;
        this.viewLayout=view;


    }
    public CustomBuySell()
    {

    }



}
