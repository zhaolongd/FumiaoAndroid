package com.fumiao.pay.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @author XieBoss
 * @version 1.0
 * @date 2019/8/18 16:27
 */
public class ViewPageAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments;

    public ViewPageAdapter(FragmentManager fm) {
        super(fm);
    }
    public ViewPageAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.mFragments = fragments;
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public Fragment getItem(int i) {
        return mFragments.get(i);
    }

}
