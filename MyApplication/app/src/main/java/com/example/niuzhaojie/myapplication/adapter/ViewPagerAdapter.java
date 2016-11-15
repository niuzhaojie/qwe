package com.example.niuzhaojie.myapplication.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by niuzhaojie on 16/10/11.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    List<Fragment> list;

    List<String> titles;


    public ViewPagerAdapter(FragmentManager fm, List<Fragment> list, List<String> titles) {
        super(fm);
        this.list = list;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        if (list != null && list.size() > 0) {
            return list.get(position);
        } else {
            return null;
        }
    }

    @Override
    public int getCount() {
        if (list != null && list.size() > 0) {
            return list.size();
        } else {
            return 0;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (titles != null && titles.size() > 0) {
            return titles.get(position % titles.size());
        } else {
            return "";
        }
    }
}
