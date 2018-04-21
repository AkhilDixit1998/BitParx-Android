package com.akhil.akhildixit.bits;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.akhil.akhildixit.bits.fragments.Account_Fragment;
import com.akhil.akhildixit.bits.fragments.Home_Fragment;
import com.akhil.akhildixit.bits.fragments.Order_Fragment;
import com.akhil.akhildixit.bits.fragments.Portfolio_Fragment;
import com.akhil.akhildixit.bits.fragments.Settings_Fragment;

import java.util.ArrayList;

public class MainActivity  extends AppCompatActivity implements Account_Fragment.OnFragmentInteractionListener,Order_Fragment.OnFragmentInteractionListener,Settings_Fragment.OnFragmentInteractionListener,Portfolio_Fragment.OnFragmentInteractionListener,View.OnClickListener{

    Button home,account,settings,portfolio,order;
    ArrayList<Fragment> backStack=new ArrayList<>();
    ArrayList<Fragment> fragmentList=new ArrayList<>();
    Fragment lastTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialisations();
        setFontAwesome();
        setFragment();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        listeners();


    }
    public void listeners()
    {
        home.setOnClickListener(this);
        account.setOnClickListener(this);
        settings.setOnClickListener(this);
        portfolio.setOnClickListener(this);
        order.setOnClickListener(this);

    }

    public void setFragment()
    {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        Home_Fragment hf=new Home_Fragment();
        Order_Fragment of=new Order_Fragment();
        Portfolio_Fragment pf=new Portfolio_Fragment();
        Settings_Fragment sf=new Settings_Fragment();
        Account_Fragment af=new Account_Fragment();

        transaction.add(R.id.h_layout,hf).disallowAddToBackStack();
        backStack.add(hf);
        transaction.commit();
        fragmentList.add(hf);
        fragmentList.add(of);
        fragmentList.add(pf);
        fragmentList.add(sf);
        fragmentList.add(af);

        lastTab=hf;
        home.setTextColor(Color.parseColor("#262626"));

    }
    public void initialisations()
    {
        home=findViewById(R.id.h_home);
        account=findViewById(R.id.h_accounts);
        settings=findViewById(R.id.h_settings);
        portfolio=findViewById(R.id.h_portfolio);
        order=findViewById(R.id.h_order);
    }

    public void setFontAwesome()
    {
        Typeface fontAwesomeFont = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");

        home.setTypeface(fontAwesomeFont);
        account.setTypeface(fontAwesomeFont);
        settings.setTypeface(fontAwesomeFont);
        portfolio.setTypeface(fontAwesomeFont);
        order.setTypeface(fontAwesomeFont);
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onClick(View view) {

        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        switch (view.getId())
        {
            case R.id.h_home:

                if(lastTab.equals(fragmentList.get(0)))
                {

                }
                else { transaction.hide(lastTab);
                    lastTab=fragmentList.get(0);

                    transaction.show(fragmentList.get(0));
                    transaction.commit();


                home.setTextColor(Color.parseColor("#262626"));
                account.setTextColor(Color.parseColor("#c7c7c7"));
                settings.setTextColor(Color.parseColor("#c7c7c7"));
                portfolio.setTextColor(Color.parseColor("#c7c7c7"));
                order.setTextColor(Color.parseColor("#c7c7c7"));}
            break;

            case R.id.h_accounts:
                if(!backStack.contains(fragmentList.get(4)))
                {
                    transaction.hide(lastTab);
                    lastTab=fragmentList.get(4);
                    backStack.add(fragmentList.get(4));

                    transaction.add(R.id.h_layout,fragmentList.get(4)).show(fragmentList.get(4)).disallowAddToBackStack().commit();
                }
                else {transaction.hide(lastTab);
                    lastTab=fragmentList.get(4);

                    transaction.show(fragmentList.get(4)).disallowAddToBackStack().commit();
                }

                account.setTextColor(Color.parseColor("#262626"));
                settings.setTextColor(Color.parseColor("#c7c7c7"));
                portfolio.setTextColor(Color.parseColor("#c7c7c7"));
                order.setTextColor(Color.parseColor("#c7c7c7"));
                home.setTextColor(Color.parseColor("#c7c7c7"));
                break;

            case R.id.h_settings:  if(!backStack.contains(fragmentList.get(3)))
            {
                transaction.hide(lastTab);
                backStack.add(fragmentList.get(3));
                lastTab=fragmentList.get(3);

                transaction.add(R.id.h_layout,fragmentList.get(3)).show(fragmentList.get(3)).disallowAddToBackStack().commit();
            }
            else {
                transaction.hide(lastTab);
                lastTab=fragmentList.get(3);

                transaction.show(fragmentList.get(3)).disallowAddToBackStack().commit();
            }
                settings.setTextColor(Color.parseColor("#262626"));
                home.setTextColor(Color.parseColor("#c7c7c7"));
                account.setTextColor(Color.parseColor("#c7c7c7"));
                portfolio.setTextColor(Color.parseColor("#c7c7c7"));
                order.setTextColor(Color.parseColor("#c7c7c7"));
                break;

            case R.id.h_portfolio:
                if(!backStack.contains(fragmentList.get(2)))
            {
                backStack.add(fragmentList.get(2));
                transaction.hide(lastTab);
                lastTab=fragmentList.get(2);
                transaction.add(R.id.h_layout,fragmentList.get(2)).show(fragmentList.get(2)).disallowAddToBackStack().commit();
            }
            else {
                transaction.hide(lastTab);
                lastTab=fragmentList.get(2);
                transaction.show(fragmentList.get(2)).disallowAddToBackStack().commit();
            }
                portfolio.setTextColor(Color.parseColor("#262626"));
                home.setTextColor(Color.parseColor("#c7c7c7"));
                account.setTextColor(Color.parseColor("#c7c7c7"));
                settings.setTextColor(Color.parseColor("#c7c7c7"));
                order.setTextColor(Color.parseColor("#c7c7c7"));
                break;

            case R.id.h_order:  if(!backStack.contains(fragmentList.get(1)))
            {
                backStack.add(fragmentList.get(1));
                transaction.hide(lastTab);
                lastTab=fragmentList.get(1);

                transaction.add(R.id.h_layout,fragmentList.get(1)).show(fragmentList.get(1)).disallowAddToBackStack().commit();
            }
            else {
                transaction.hide(lastTab);
                lastTab=fragmentList.get(1);

                transaction.show(fragmentList.get(1)).commit();
            }
                order.setTextColor(Color.parseColor("#262626"));
                home.setTextColor(Color.parseColor("#c7c7c7"));
                account.setTextColor(Color.parseColor("#c7c7c7"));
                settings.setTextColor(Color.parseColor("#c7c7c7"));
                portfolio.setTextColor(Color.parseColor("#c7c7c7"));

        }
    }
}
