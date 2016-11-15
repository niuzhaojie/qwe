package com.example.niuzhaojie.myapplication.activity;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.niuzhaojie.myapplication.R;
import com.example.niuzhaojie.myapplication.databinding.ActivityLevellistdrawableBinding;

/**
 * Created by niuzhaojie on 16/11/7.
 */
public class LevelListDtawableActivity extends Activity {

    private ActivityLevellistdrawableBinding binding;
    private static int level = 0;


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            binding.img.setImageLevel(level % 20);
            level++;
            mHandler.sendEmptyMessageDelayed(0, 10);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_levellistdrawable);
        mHandler.sendEmptyMessage(0);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }
}
