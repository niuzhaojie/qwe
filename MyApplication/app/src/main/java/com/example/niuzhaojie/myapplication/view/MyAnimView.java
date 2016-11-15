package com.example.niuzhaojie.myapplication.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.niuzhaojie.myapplication.R;
import com.example.niuzhaojie.myapplication.control.DecelerateAccelerateInterpolator;
import com.example.niuzhaojie.myapplication.control.PointEvaluator;
import com.example.niuzhaojie.myapplication.model.Point;

/**
 * Created by niuzhaojie on 16/9/18.
 */
public class MyAnimView extends View {
    public static final float RADIUS = 50f;

    private Point currentPoint;

    private Paint mPaint;

    public MyAnimView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
    }

    public MyAnimView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CircleIndicator, defStyleAttr, 0);


        int size = array.getIndexCount();
        for (int i = 0; i < size; i++) {
            int attr = array.getIndex(i);
            switch (attr) {
                case R.styleable.CircleIndicator_ci_animator:
                    array.getDimensionPixelSize(attr,50);
                    break;

                default:
                    break;
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (currentPoint == null) {
            currentPoint = new Point(getWidth() / 2, RADIUS);
            drawCircle(canvas);
            startAnimation();
        } else {
            drawCircle(canvas);
        }
    }

    private void drawCircle(Canvas canvas) {
        float x = currentPoint.getX();
        float y = currentPoint.getY();
        canvas.drawCircle(x, y, RADIUS, mPaint);
    }

    private void startAnimation() {
        Point startPoint = new Point(getWidth() / 2, RADIUS);
        Point endPoint = new Point(getWidth() / 2, getHeight() - RADIUS);
        ValueAnimator anim = ValueAnimator.ofObject(new PointEvaluator(), startPoint, endPoint);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentPoint = (Point) animation.getAnimatedValue();
                invalidate();
            }
        });
//        anim.setInterpolator(new BounceInterpolator());
        anim.setInterpolator(new DecelerateAccelerateInterpolator());
        anim.setDuration(2500);
        anim.start();
    }
}
