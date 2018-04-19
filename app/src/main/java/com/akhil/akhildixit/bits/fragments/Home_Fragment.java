package com.akhil.akhildixit.bits.fragments;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        recyclerView=view.findViewById(R.id.buySellList);

        mLayoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setVisibility(View.GONE);

        return view;
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
               adapter=new CustomBuySell(arrayList);
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
