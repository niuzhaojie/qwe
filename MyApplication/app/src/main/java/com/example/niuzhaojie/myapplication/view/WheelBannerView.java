package com.example.niuzhaojie.myapplication.view;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.niuzhaojie.myapplication.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by niuzhaojie on 16/10/11.
 */

public class WheelBannerView extends LinearLayout {

    private ViewPager mViewPager;

    /**
     * 底部文字指示器
     */
    private TextView mIndicatorTxt;

    /**
     * 小圆点指示器
     */
    private CircleIndicator mCircleIndicator;

    private Activity mActivity;

    /**
     * 要显示的图片资源集合
     */
    private List<String> mImages;

    /**
     * 显示图片的imageview集合
     */
    private List<ImageView> mImageViews;

    /**
     * 是否是循环播放
     */
    private boolean mCycleable;

    /**
     * 是否自动播放
     */
    private boolean mAutoPlay;

    /**
     * 当前显示的位置（循环播放时，不等于真实显示的图片index）
     */
    private int currentPosition;

    /**
     * 自动播放的时间间隔
     */
    private int mDuration;


    public WheelBannerView(Context context) {
        super(context);
    }

    public WheelBannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View wheelView = LayoutInflater.from(context).inflate(R.layout.wheelbannerview_layout, this);

        mViewPager = (ViewPager) wheelView.findViewById(R.id.wheelView_viewpager);
        mIndicatorTxt = (TextView) wheelView.findViewById(R.id.indactor_txt);
        mCircleIndicator = (CircleIndicator) wheelView.findViewById(R.id.circleIndicator);
    }

    public void startPlayWheelBanner(Activity activity, List<String> urls) {
        mActivity = activity;
        mImages = urls;
        mAutoPlay = true;
        mDuration = 2 * 1000;
        startPlayWheelBanner(mActivity, mImages, mAutoPlay, mDuration);
    }

    /**
     * @param activity
     * @param urls     待显示的图片资源集合
     * @param autoPlay 　是否自动播放
     * @param duration 自动播放时间间隔
     */
    public void startPlayWheelBanner(Activity activity, List<String> urls, boolean autoPlay, int duration) {
        mActivity = activity;
        mImages = urls;
        mAutoPlay = autoPlay;
        mDuration = duration;

        startPlay();
    }

    /**
     * @param activity
     * @param urls      待显示的图片资源集合
     * @param cycleable 是否循环播放
     */
    public void startPlayWheelBanner(Activity activity, List<String> urls, boolean cycleable) {
        mActivity = activity;
        mImages = urls;
        mCycleable = cycleable;

        startPlay();
    }

    private void startPlay() {

        getImaveViews();

        initView();

        if (mAutoPlay || mCycleable) {
            currentPosition = Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % mImages.size();

            if (mAutoPlay) {
                mHandler.removeCallbacksAndMessages(null);
                mHandler.sendEmptyMessageDelayed(0, mDuration);
            }
        } else {
            currentPosition = 0;
        }
        mViewPager.setCurrentItem(currentPosition);
    }

    /**
     * 根据图片集合的大小获取imageview集合，长度一致
     */
    private void getImaveViews() {
        mImageViews = new ArrayList<ImageView>();

        for (int i = 0; i < mImages.size(); i++) {
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 120);
            ImageView imageView = new ImageView(getContext());
            imageView.setLayoutParams(params);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);

            mImageViews.add(imageView);
        }
    }

    /**
     * 设置监听，装载适配器
     */
    private void initView() {
        mViewPager.addOnPageChangeListener(mOnPageChangeListener);
        mViewPager.setOnTouchListener(mOnTouchListener);
        mViewPager.setAdapter(new ImgAdapter());

        mCircleIndicator.setViewPager(mViewPager, mImages.size());

        mViewPager.setPageTransformer(true, new DepthPageTransformer());


        ViewPagerScroller scroller = new ViewPagerScroller(mActivity);
        scroller.setScrollDuration(1000);
        scroller.initViewPagerScroll(mViewPager);
    }


    /**
     * 设置指示器文字
     */
    private void setIndicatorTxt() {
        String index = (currentPosition % mImages.size() + 1) + "/" + mImages.size();

        mIndicatorTxt.setText(index);
    }

    /**
     * 处理viewpager滑动时候的事务
     * 1.预加载前后的图片
     * 2.动态设置指示器
     */
    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int state) {
            currentPosition = state;
            setIndicatorTxt();
            if (state > 1) {
                getImgsFromUrl(mImages.get((currentPosition - 1) % mImages.size()), mImageViews.get((currentPosition - 1) % mImageViews.size()));
            }

            if (state < Integer.MAX_VALUE) {
                getImgsFromUrl(mImages.get((currentPosition + 1) % mImages.size()), mImageViews.get((currentPosition + 1) % mImageViews.size()));
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    /**
     * 自动播放相关
     */
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (!mActivity.isFinishing() && mAutoPlay) {
                mViewPager.setCurrentItem(currentPosition + 1);
                mHandler.sendEmptyMessageDelayed(0, mDuration);
            } else {
                mHandler.removeCallbacksAndMessages(null);
            }
        }
    };

    /**
     * viewpager上有触摸事件时，取消handler消息和回调
     */
    private OnTouchListener mOnTouchListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_UP && mAutoPlay) {
                mHandler.removeCallbacksAndMessages(null);
                mHandler.sendEmptyMessageDelayed(0, mDuration);
            } else {
                mHandler.removeCallbacksAndMessages(null);
            }
            return false;
        }
    };

    /**
     * glide库加载网络图片
     *
     * @param url       图片url
     * @param imageView 显示图片的imageview
     */
    private void getImgsFromUrl(String url, ImageView imageView) {
        Glide.with(getContext()).load(url).
                placeholder(R.drawable.mdd_banner).error(R.drawable.mdd_beijing).
                crossFade().
                into(imageView);
    }

    public ViewPager getViewPager() {
        return mViewPager == null ? new ViewPager(mActivity) : mViewPager;
    }

    /**
     * viewpager适配器
     */
    class ImgAdapter extends PagerAdapter {

        public ImgAdapter() {
        }

        @Override
        public int getCount() {
            if (mAutoPlay || mCycleable) {//自动播放和可循环时，设置最大
                return Integer.MAX_VALUE;
            } else {
                if (mImages != null && mImages.size() > 0) {
                    return mImages.size();
                } else {
                    return 0;
                }
            }

        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mImageViews.get(position % mImageViews.size()));//移除
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            ImageView imageView = mImageViews.get(position % mImageViews.size());
            getImgsFromUrl(mImages.get(position % mImages.size()), imageView);//加载当前index的图片

            container.addView(imageView);
            return imageView;
        }


    }


    /**
     * 控制viewPager自动播放时候切换速度
     */
    class ViewPagerScroller extends Scroller {
        private int mScrollDuration = 2000;             // 滑动速度

        /**
         * 设置速度速度
         *
         * @param duration
         */
        public void setScrollDuration(int duration) {
            this.mScrollDuration = duration;
        }

        public ViewPagerScroller(Context context) {
            super(context);
        }

        public ViewPagerScroller(Context context, Interpolator interpolator) {
            super(context, interpolator);
        }

        public ViewPagerScroller(Context context, Interpolator interpolator, boolean flywheel) {
            super(context, interpolator, flywheel);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            super.startScroll(startX, startY, dx, dy, mScrollDuration);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy) {
            super.startScroll(startX, startY, dx, dy, mScrollDuration);
        }


        public void initViewPagerScroll(ViewPager viewPager) {
            try {
                Field mScroller = ViewPager.class.getDeclaredField("mScroller");
                mScroller.setAccessible(true);
                mScroller.set(viewPager, this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * viewPager切换页面时的动画
     */
    class DepthPageTransformer implements ViewPager.PageTransformer {
        private float MIN_SCALE = 0.75f;

        @Override
        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);
            } else if (position <= 0) { // [-1,0]
                // Use the default slide transition when
                // moving to the left page
                view.setAlpha(1);
                view.setTranslationX(0);
                view.setScaleX(1);
                view.setScaleY(1);
            } else if (position <= 1) { // (0,1]
                // Fade the page out.
                view.setAlpha(1 - position);
                // Counteract the default slide transition
                view.setTranslationX(pageWidth * -position);
                // Scale the page down (between MIN_SCALE and 1)
                float scaleFactor = MIN_SCALE + (1 - MIN_SCALE)
                        * (1 - Math.abs(position));
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);
            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);

            }
        }

    }

    /**
     * viewPager切换页面时的动画
     */
    class ZoomOutPageTransformer implements ViewPager.PageTransformer {

        private float MIN_SCALE = 0.85f;

        private float MIN_ALPHA = 0.5f;

        @Override
        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);
            } else if (position <= 1) { // [-1,1]
                // Modify the default slide transition to
                // shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }
                // Scale the page down (between MIN_SCALE and 1)
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);
                // Fade the page relative to its size.
                view.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE)
                        / (1 - MIN_SCALE) * (1 - MIN_ALPHA));
            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }

}