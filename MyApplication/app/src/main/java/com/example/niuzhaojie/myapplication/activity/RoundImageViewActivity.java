package com.example.niuzhaojie.myapplication.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.niuzhaojie.myapplication.R;
import com.example.niuzhaojie.myapplication.view.CircleImageDrawable;
import com.example.niuzhaojie.myapplication.view.RoundImageDrawable;

/**
 * Created by niuzhaojie on 16/10/9.
 */
public class RoundImageViewActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roundimageview);


        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.tu);
        ImageView imageView = (ImageView) findViewById(R.id.view_img);
        imageView.setImageDrawable(new RoundImageDrawable(bitmap));


        bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.tu);
        ImageView imageView2 = (ImageView) findViewById(R.id.view_img2);
        imageView2.setImageDrawable(new CircleImageDrawable(bitmap));


    }
}
