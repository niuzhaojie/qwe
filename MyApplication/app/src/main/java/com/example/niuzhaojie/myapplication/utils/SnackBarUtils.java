package com.example.niuzhaojie.myapplication.utils;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by niuzhaojie on 16/10/13.
 */

public class SnackBarUtils {
    private SnackBarUtils() {
        throw new UnsupportedOperationException("这个它喵的不可实例，不会用不要用");
    }

    private static Snackbar mSnackbar;


    public static void showSnackbar(View view, Context context, String content) {

        mSnackbar = Snackbar.make(view, content, Snackbar.LENGTH_SHORT);
        mSnackbar.show();
    }

}
