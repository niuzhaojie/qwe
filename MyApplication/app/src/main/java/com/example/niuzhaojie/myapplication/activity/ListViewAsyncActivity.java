package com.example.niuzhaojie.myapplication.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.niuzhaojie.myapplication.R;
import com.example.niuzhaojie.myapplication.adapter.ImageAdapter2;
import com.example.niuzhaojie.myapplication.model.Images;
import com.example.niuzhaojie.myapplication.utils.ToastUtils;
import com.example.niuzhaojie.myapplication.view.CustomTitleView;

/**
 * Created by niuzhaojie on 16/9/19.
 */
public class ListViewAsyncActivity extends Activity {

    private CustomTitleView titleBarView;


    private ListView listView;
    private ImageAdapter2 adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listviewasync);

        titleBarView = (CustomTitleView) findViewById(R.id.titleBar);
        listView = (ListView) findViewById(R.id.listview);


        adapter = new ImageAdapter2(this, 0, Images.imageUrls);

    }


    @Override
    protected void onResume() {
        super.onResume();
        listView.setAdapter(adapter);

        titleBarView.setTitleText("这是title");

        titleBarView.SetStatusBarImmersive(ListViewAsyncActivity.this);

        titleBarView.setTitleViewBGColor(Color.parseColor("#ff00ddff"));

        listView.setOnScrollListener(titleBarView.getScrollChangeListener());


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtils.showToast(ListViewAsyncActivity.this, "你丫sb啊");
            }
        });

    }
}
