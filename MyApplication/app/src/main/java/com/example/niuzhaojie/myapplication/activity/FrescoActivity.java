package com.example.niuzhaojie.myapplication.activity;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

import com.example.niuzhaojie.myapplication.R;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by niuzhaojie on 16/9/19.
 */
public class FrescoActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco);

        Uri uri = Uri.parse("http://img.pconline.com.cn/images/upload/upc/tx/wallpaper/1402/12/c1/31189058_1392186616852.jpg");
        SimpleDraweeView draweeView = (SimpleDraweeView) findViewById(R.id.my_image_view);
        draweeView.setImageURI(uri);
    }
}
