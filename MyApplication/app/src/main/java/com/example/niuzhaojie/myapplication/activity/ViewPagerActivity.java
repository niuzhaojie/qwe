package com.example.niuzhaojie.myapplication.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.example.niuzhaojie.myapplication.R;
import com.example.niuzhaojie.myapplication.adapter.ViewPagerAdapter;
import com.example.niuzhaojie.myapplication.fragment.TestFragment;
import com.example.niuzhaojie.myapplication.model.Images;
import com.example.niuzhaojie.myapplication.view.WheelBannerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by niuzhaojie on 16/10/11.
 */
public class ViewPagerActivity extends FragmentActivity {

    private List<Fragment> fragments = new ArrayList<Fragment>();
    private List<String> titles = new ArrayList<String>();


    WheelBannerView wheelBannerView;
    TabLayout idTablayout;
    ViewPager idViewpager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);


        wheelBannerView = (WheelBannerView) findViewById(R.id.wheelView);
        idTablayout = (TabLayout) findViewById(R.id.id_tablayout);
        idViewpager = (ViewPager) findViewById(R.id.id_viewpager);

        initView();


    }

    private void initView() {
        List<String> imgs = new ArrayList<String>();

        Collections.addAll(imgs, Images.imageUrls2);
        wheelBannerView.startPlayWheelBanner(this, imgs, true, 3 * 1000);
//        wheelBannerView.startPlayWheelBanner(this, imgs, false);

        ViewPager viewPager = wheelBannerView.getViewPager();

        for (int i = 0; i < 5; i++) {
            TestFragment fragment = new TestFragment();
            Bundle bundle = new Bundle();
            bundle.putString("sb", "this is fragment: " + i);
            fragment.setArguments(bundle);

            fragments.add(fragment);
            titles.add("fragment:" + i);
        }


        idViewpager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), fragments, titles));

        idTablayout.setupWithViewPager(idViewpager);

//        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.circleIndicator);
//        indicator.setViewPager(idViewpager);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        wheelBannerView.onDestroy();
    }
}
