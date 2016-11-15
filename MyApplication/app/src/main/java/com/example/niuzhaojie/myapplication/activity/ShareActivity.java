package com.example.niuzhaojie.myapplication.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.niuzhaojie.myapplication.R;
import com.example.niuzhaojie.myapplication.databinding.ActivityShareBinding;

public class ShareActivity extends AppCompatActivity {
    public String extraStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        extraStr = getIntent().getStringExtra(Intent.EXTRA_TEXT);

        ActivityShareBinding binding = DataBindingUtil.setContentView(ShareActivity.this, R.layout.activity_share);
        binding.setExtraStr(extraStr);
    }
}
