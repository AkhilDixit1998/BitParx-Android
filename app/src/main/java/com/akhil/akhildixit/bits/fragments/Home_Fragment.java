package com.akhil.akhildixit.bits.fragments;

import android.annotation.SuppressLint;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.akhil.akhildixit.bits.R;
import com.akhil.akhildixit.bits.SettersGetters.BuySell;
import com.akhil.akhildixit.bits.adapters.CustomBuySell;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import static android.widget.GridLayout.HORIZONTAL;


public class Home_Fragment extends Fragment {

    Button btcMarket,ethMarket;
    ImageView arrowBtc,arrowEth;
    FrameLayout popup;
    Button buyBtn,sellBtn;
    Button close;
    RelativeLayout sell;
    ScrollView buy;
    View viewlayout;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager mLayoutManager;
    ArrayList<BuySell> arrayList=new ArrayList<>(10);
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fetchInitialData();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_home, container, false);
        viewlayout=view;
        recyclerView=view.findViewById(R.id.buySellList);
        btcMarket=view.findViewById(R.id.home_btcMarket);
        ethMarket=view.findViewById(R.id.home_ethMarket);

        arrowBtc=view.findViewById(R.id.arrowBtc);
        arrowEth=view.findViewById(R.id.arrowEth);
        mLayoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setVisibility(View.GONE);
        buyBtn=view.findViewById(R.id.popUp_buyButton);
        sellBtn=view.findViewById(R.id.popUp_sellButton);

        close=view.findViewById(R.id.popUpClose);
        popup=view.findViewById(R.id.popUp);
        sell=view.findViewById(R.id.popUp_sell);
        buy=view.findViewById(R.id.popUp_buy);
        initialiseListeners(view);
        return view;
    }

    public void initialiseListeners(View view)
    {
        btcMarket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction transaction=fragmentManager.beginTransaction();

            }
        });
        buyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buy.setVisibility(View.VISIBLE);
                sell.setVisibility(View.GONE);
            }
        });

        sellBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buy.setVisibility(View.GONE);
                sell.setVisibility(View.VISIBLE);
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popup.setVisibility(View.GONE);
                }
        });


    }
    @SuppressLint("StaticFieldLeak")
    public void fetchInitialData()
    {

       new AsyncTask<Object,Void,String>()
       {

           @Override
           protected void onPostExecute(String s) {
               super.onPostExecute(s);

               Log.e("Data","D "+s);
               try {
                   JSONArray jsonArray=new JSONArray(s);
                   for (int i=0;i<jsonArray.length();i++)
                   {
                       BuySell buySell=new BuySell();
                       JSONObject jsonObject= jsonArray.getJSONObject(i);
                       String id=jsonObject.getString("id");
                       String price=jsonObject.getString("price_usd");
                       buySell.id=id;
                       buySell.price=price;
                       arrayList.add(buySell);
                       Log.e("The names are as ","data "+id+" "+price+" ");
                   }
               } catch (JSONException e) {
                   e.printStackTrace();
               }
               Log.e("AR","size "+arrayList.size());
               adapter=new CustomBuySell(arrayList,viewlayout);
                   recyclerView.setAdapter(adapter);
                   adapter.notifyDataSetChanged();
               recyclerView.setVisibility(View.VISIBLE);




           }

           @Override
           protected String doInBackground(Object... voids) {

               try {
                   String json;

                   URL url=new URL("https://api.coinmarketcap.com/v1/ticker/?limit=5");
                   HttpsURLConnection urlConnection=(HttpsURLConnection)url.openConnection();
                   BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                   StringBuilder stringBuilder=new StringBuilder();
                   String line;
                   while ((line=bufferedReader.readLine())!=null)
                   {
                       stringBuilder.append(line).append("\n");
                   }
                   bufferedReader.close();
                   json=stringBuilder.toString();
                   return stringBuilder.toString();
               }  catch (Exception e) {
                   e.printStackTrace();
               }
               return null;
           }


       }.execute();

    }

}
