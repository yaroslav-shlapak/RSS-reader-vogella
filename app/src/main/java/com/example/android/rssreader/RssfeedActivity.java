package com.example.android.rssreader;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;

public class RssfeedActivity extends Activity implements MyListFragment.OnItemSelectedListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rssfeed);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        if(getResources().getBoolean(R.bool.dual_pane)) {
            ft.add(R.id.listcontainer_land, new MyListFragment());
            ft.add(R.id.detailscontainer_land, new DetailFragment());
        } else {
            ft.add(R.id.listcontainer, new MyListFragment());
            ft.add(R.id.detailscontainer, new DetailFragment());
        }
        ft.commit();
    }

    @Override
    public void onRssItemSelected(String link) {
        FragmentManager fm = getFragmentManager();
        int fragmentId;
        if (getResources().getBoolean(R.bool.dual_pane)) {
            fragmentId = R.id.detailscontainer_land;
            Log.d("test", "onRssItemSelected if true");
        } else {
            FragmentTransaction ft = fm.beginTransaction();
            ft.remove(fm.findFragmentById(R.id.listcontainer));

            FrameLayout fl = (FrameLayout) findViewById((R.id.detailscontainer));
            fl.setVisibility(View.VISIBLE);
            ft.addToBackStack(null);
            ft.commit();
            fragmentId = R.id.detailscontainer;
            Log.d("test", "onRssItemSelected if false");

        }
        DetailFragment fragment = (DetailFragment) fm.findFragmentById(fragmentId);
        fragment.setText(link);
    }

} 
