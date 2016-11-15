package com.example.niuzhaojie.myapplication.activity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.TextView;

import com.example.niuzhaojie.myapplication.R;
import com.example.niuzhaojie.myapplication.control.PointEvaluator;
import com.example.niuzhaojie.myapplication.model.Point;
import com.example.niuzhaojie.myapplication.utils.ScreenUtils;

/**
 * Created by niuzhaojie on 16/9/18.
 */
public class AnimatorActivity extends Activity {

    private String Tag = this.getClass().getSimpleName();


    private TextView textview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valueanimator);

        textview = (TextView) findViewById(R.id.textview);

    }

    public void startAnimatator(View view) {

//        testValueAnimator();

//        testObjectAnimator();

//        textAnimatorSet();

//        testAnimatorFromXML();

//        testPointAnimator();

        testViewPropertyAnimator();
    }

    private void testViewPropertyAnimator() {
//        textview.animate().alpha(0f).setDuration(5 * 1000);
        textview.animate().translationY(ScreenUtils.getScreenHeight(this)).setDuration(2 * 1000).setInterpolator(new BounceInterpolator());
    }


    private void testPointAnimator() {

        Point point1 = new Point(0, 0);
        Point point2 = new Point(300, 300);

        ValueAnimator anim = ValueAnimator.ofObject(new PointEvaluator(), point1, point2);
        anim.setDuration(5 * 1000);
        anim.start();

    }

    private void testAnimatorFromXML() {
        Animator anim = AnimatorInflater.loadAnimator(this, R.animator.animator);
        anim.setTarget(textview);

        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        anim.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationRepeat(Animator animation) {
                super.onAnimationRepeat(animation);
            }
        });

        anim.start();
    }

    private void textAnimatorSet() {
        ObjectAnimator moveIn = ObjectAnimator.ofFloat(textview, "translationX", -5000f, 0f);
        ObjectAnimator rotate = ObjectAnimator.ofFloat(textview, "rotation", 0f, 360f);
        ObjectAnimator fadeInOut = ObjectAnimator.ofFloat(textview, "alpha", 1f, 0f, 1f);
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(rotate).with(fadeInOut).after(moveIn);
        animSet.setDuration(5000);
        animSet.start();
    }

    private void testObjectAnimator() {
//        ObjectAnimator anim = ObjectAnimator.ofFloat(textview, "alpha", 1f, 0f, 1f);

//        ObjectAnimator anim = ObjectAnimator.ofFloat(textview, "rotation", 0f, 360f);

        float curTranslationX = textview.getTranslationX();
        ObjectAnimator anim = ObjectAnimator.ofFloat(textview, "translationX", curTranslationX, -500f, curTranslationX);

        anim.setDuration(5 * 1000);
        anim.start();
    }

    private void testValueAnimator() {
        ValueAnimator anim = ValueAnimator.ofInt(0, 10);
        anim.setDuration(10 * 1000);

        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int currentValue = (int) animation.getAnimatedValue();

                Log.e(Tag, "current: " + currentValue);
            }
        });

        anim.start();
    }


}
