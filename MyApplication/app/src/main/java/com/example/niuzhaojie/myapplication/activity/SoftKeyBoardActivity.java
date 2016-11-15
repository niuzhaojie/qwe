package com.example.niuzhaojie.myapplication.activity;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;

import com.example.niuzhaojie.myapplication.R;
import com.example.niuzhaojie.myapplication.view.RelativeLayoutSubClass;

/**
 * Created by niuzhaojie on 16/10/17.
 */
public class SoftKeyBoardActivity extends Activity {

    private RelativeLayoutSubClass mRootLayout;
    private int screenHeight;
    private int screenWidth;
    private int threshold;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_softkeyboard);
        init();
    }

    private void init() {
        screenHeight = getResources().getDisplayMetrics().heightPixels;
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        threshold = screenHeight / 3;
        mRootLayout = (RelativeLayoutSubClass) findViewById(R.id.rootLayout);
        mRootLayout.setSoftKeyboardListener(new RelativeLayoutSubClass.OnSoftKeyboardListener() {
            @Override
            public void onSoftKeyboardChange(int w, int h, int oldw, int oldh) {
                if (oldh - h > threshold) {
                    System.out.println("----> 软键盘弹起");
                } else if (h - oldh > threshold) {
                    System.out.println("----> 软键盘收起");
                }
            }

            @Override
            public void onSoftKeyboardChange() {
                boolean isShow = isSoftKeyboardShow(mRootLayout);
                System.out.println("----> isShow=" + isShow);

            }
        });
    }

    public boolean isSoftKeyboardShow(View rootView) {
        screenHeight = getResources().getDisplayMetrics().heightPixels;
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        threshold = screenHeight / 3;
        int rootViewBottom = rootView.getBottom();
        Rect rect = new Rect();
        rootView.getWindowVisibleDisplayFrame(rect);
        int visibleBottom = rect.bottom;
        int heightDiff = rootViewBottom - visibleBottom;
        System.out.println("----> rootViewBottom=" + rootViewBottom + ",visibleBottom=" + visibleBottom);
        System.out.println("----> heightDiff=" + heightDiff + ",threshold=" + threshold);
        return heightDiff > threshold;
    }
}
