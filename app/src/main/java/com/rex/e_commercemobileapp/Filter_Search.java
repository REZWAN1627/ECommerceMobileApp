package com.rex.e_commercemobileapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;

import com.google.android.material.tabs.TabLayout;
import com.rex.e_commercemobileapp.Adapter.PagerAdamperFilter;

public class Filter_Search extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private String SearchQuery;
    private static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter__search);
        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.Tablayout);
        SearchQuery = getIntent().getStringExtra("Order");

        Log.d(TAG, "onCreate: "+SearchQuery);


        viewPager.setAdapter(new PagerAdamperFilter(getSupportFragmentManager(),this,SearchQuery));
        tabLayout.setupWithViewPager(viewPager,true);
    }
}