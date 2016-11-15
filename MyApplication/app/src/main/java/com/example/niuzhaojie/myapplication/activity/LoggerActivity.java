package com.example.niuzhaojie.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.niuzhaojie.myapplication.R;
import com.example.niuzhaojie.myapplication.utils.L;

/**
 * Created by niuzhaojie on 16/10/8.
 */
public class LoggerActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loggerutils);

    }

    public void logger(View view) {
        loggerB();

    }

    private void loggerB() {
        L.e("Hello World");

    }
}
