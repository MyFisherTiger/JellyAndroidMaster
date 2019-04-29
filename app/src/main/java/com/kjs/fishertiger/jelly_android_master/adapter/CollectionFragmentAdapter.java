package com.kjs.fishertiger.jelly_android_master.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;

import java.util.List;


public class CollectionFragmentAdapter extends FragmentPagerAdapter {

    private Context context;
    private List<Fragment> fragmentList;

    public CollectionFragmentAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return (fragmentList == null || fragmentList.size() == 0) ? null : fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList == null ? 0 : fragmentList.size();
    }

    @Override
    public int getItemPosition(Object object) {
        //return PagerAdapter.POSITION_NONE;
        return PagerAdapter.POSITION_NONE;
    }
}
