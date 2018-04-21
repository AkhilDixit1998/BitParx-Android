package com.akhil.akhildixit.bits.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

    @NonNull
    @Override
    public CustomBuySell.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.buy_sell_custom,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

      /*  holder.name.setText("Name "+position);
        holder.price.setText("Price "+position);*/
        holder.name.setText(arrayList.get(position).id);
        holder.price.setText(arrayList.get(position).price);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
         TextView name;
        TextView price;
        public myViewHolder(View v)
        {
       super(v);
         name=v.findViewById(R.id.buySellName);
         price=v.findViewById(R.id.buySellPrice);
        }
    }
    public CustomBuySell(ArrayList<BuySell> arrayList)  {
        this.arrayList=arrayList;

    }
    public CustomBuySell()
    {

    }

}
