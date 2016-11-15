package com.example.niuzhaojie.myapplication.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by niuzhaojie on 16/9/19.
 */
public class ToastUtils {

    private ToastUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    private static Toast toast;

    public static void showToast(Context context, String content) {
        if (toast == null) {
            toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }

}
