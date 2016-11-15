package com.example.niuzhaojie.myapplication;

import android.app.Application;

import com.example.niuzhaojie.myapplication.utils.L;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by niuzhaojie on 16/8/25.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(getApplicationContext());

        L.init(true, "hhh");
    }
}
