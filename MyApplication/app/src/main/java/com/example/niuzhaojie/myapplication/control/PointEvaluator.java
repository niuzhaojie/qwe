package com.example.niuzhaojie.myapplication.control;

import android.animation.TypeEvaluator;

import com.example.niuzhaojie.myapplication.model.Point;

/**
 * Created by niuzhaojie on 16/9/18.
 */
public class PointEvaluator implements TypeEvaluator {


    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {

        Point startPoint = (Point) startValue;
        Point endPoint = (Point) endValue;

        float x = startPoint.getX() + fraction * (endPoint.getX() - startPoint.getX());
        float y = startPoint.getY() + fraction * (endPoint.getY() - startPoint.getY());

        return new Point(x, y);
    }
}
