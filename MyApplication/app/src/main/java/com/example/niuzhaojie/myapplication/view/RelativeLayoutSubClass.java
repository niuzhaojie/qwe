package com.example.niuzhaojie.myapplication.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by niuzhaojie on 16/10/17.
 */

public class RelativeLayoutSubClass extends RelativeLayout {
    private OnSoftKeyboardListener mSoftKeyboardListener;

    public RelativeLayoutSubClass(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        System.out.println("----> onMeasure");
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mSoftKeyboardListener.onSoftKeyboardChange();
        System.out.println("----> onLayout");
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mSoftKeyboardListener.onSoftKeyboardChange(w, h, oldw, oldh);
        System.out.println("----> onSizeChanged");
    }

    public void setSoftKeyboardListener(OnSoftKeyboardListener listener) {
        mSoftKeyboardListener = listener;
    }

    public interface OnSoftKeyboardListener {
        public void onSoftKeyboardChange(int w, int h, int oldw, int oldh);

        public void onSoftKeyboardChange();
    }
}
