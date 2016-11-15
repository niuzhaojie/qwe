package com.example.niuzhaojie.myapplication.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.niuzhaojie.myapplication.R;
import com.example.niuzhaojie.myapplication.utils.ScreenUtils;

/**
 * Created by niuzhaojie on 16/9/20.
 */

public class CustomTitleView extends FrameLayout implements View.OnClickListener {


    private AbsListView.OnScrollListener mOnScrollListener;

    private ImageButton imageLeft;

    private TextView titleText;

    private View titleViewLayout;

    private int titleViewBGColor;

    private Activity mActivity;

    public CustomTitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.titleview_layout, this);
        titleText = (TextView) findViewById(R.id.title_text);
        imageLeft = (ImageButton) findViewById(R.id.image_left);
        titleViewLayout = findViewById(R.id.titleView_layout);
        setOnClickListener();
    }


    public void setTitleText(String text) {
        if (text != null) {
            titleText.setText(text);
        }
    }


    public void setTitleViewBGColor(int color) {
        this.titleViewBGColor = color;
        titleViewLayout.setBackgroundColor(titleViewBGColor);
    }

    public void SetStatusBarImmersive(@NonNull Activity mActivity) {
        this.mActivity = mActivity;
        setTitleBarViewAlpha(0f);
        if (Build.VERSION.SDK_INT > 20) {

            View decorView = mActivity.getWindow().getDecorView();

            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE;
            decorView.setSystemUiVisibility(option);

            mActivity.getWindow().setStatusBarColor(Color.TRANSPARENT);

            setTitleViewMarginTop();

        }
    }

    public AbsListView.OnScrollListener getScrollChangeListener() {
        if (mOnScrollListener == null) {
            setOnScrollChangeLintener();
        }
        return mOnScrollListener;
    }

    private void setOnClickListener() {
        imageLeft.setOnClickListener(this);
    }

    private void setTitleBarViewAlpha(float alpha) {

        titleViewLayout.setAlpha(alpha);
        titleText.setAlpha(alpha);
        setStatusBarViewColor(alpha);

    }


    private void setStatusBarViewColor(@FloatRange(from = 0.0, to = 1.0) float alpha) {
        if (mActivity != null && Build.VERSION.SDK_INT > 20 && titleViewBGColor != 0) {
            String alphaString = Integer.toHexString((int) (alpha * 255) > 255 ? 255 : (int) (alpha * 255));
            if (alphaString.length() < 2) {
                alphaString = "0" + alphaString;
            }
            String colorString = Integer.toHexString(titleViewBGColor).substring(2);
            mActivity.getWindow().setStatusBarColor(Color.parseColor("#" + alphaString + colorString));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_left:
                ((Activity) getContext()).finish();
                break;
        }

    }


    private void setTitleViewMarginTop() {
        this.setPadding(0, ScreenUtils.getStatusHeight(mActivity), 0, 0);
    }


    private void setOnScrollChangeLintener() {

        mOnScrollListener = new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                setTitleBarViewAlpha(getScrollY(view) / 1000.0f);

            }

        };
    }

    private int getScrollY(AbsListView mListView) {
        View c = mListView.getChildAt(0);
        if (c == null) {
            return 0;
        }
        int firstVisiblePosition = mListView.getFirstVisiblePosition();
        int top = c.getTop();
        return -top + firstVisiblePosition * c.getHeight();
    }


}
