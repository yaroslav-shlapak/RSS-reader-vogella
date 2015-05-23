package com.example.android.rssreader;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class RssfeedActivity extends Activity implements MyListFragment.OnItemSelectedListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rssfeed);
        if (!getResources().getBoolean(R.bool.dual_pane)) {
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.listcontainer, new ListFragment());
            ft.commit();
        }
    }

    @Override
    public void onRssItemSelected(String link) {
        FragmentManager fm = getFragmentManager();

        if (getResources().getBoolean(R.bool.dual_pane)) {
            DetailFragment fragment = (DetailFragment) fm.findFragmentById(R.id.detailFragment);
            fragment.setText(link);
        } else {
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.detailscontainer, new ListFragment());
            ft.remove(fm.findFragmentById(R.id.listcontainer));
            ft.addToBackStack(null);
            ft.commit();
        }
    }

} 
