package com.rex.e_commercemobileapp.Adapter;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.rex.e_commercemobileapp.MainUi.FragmentPager.ClothsFrag;
import com.rex.e_commercemobileapp.MainUi.FragmentPager.ShoesFrag;
import com.rex.e_commercemobileapp.R;

public class PagerAdamperFilter  extends FragmentPagerAdapter {
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_3, R.string.tab_text_4};
    private final Context  mContext;
    private String searchQuery;
    private static final String TAG = "TAg";

    public PagerAdamperFilter(@NonNull FragmentManager fm, Context mContext, String searchQuery) {
        super(fm);
        this.mContext = mContext;
        this.searchQuery = searchQuery;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                Log.d(TAG, "getItem: "+searchQuery);
                fragment = new ShoesFrag().newInstance(searchQuery);
                break;
            case 1:
                fragment = new ClothsFrag().newInstance(searchQuery);
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }
}
