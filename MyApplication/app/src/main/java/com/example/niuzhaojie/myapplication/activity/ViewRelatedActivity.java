package com.example.niuzhaojie.myapplication.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.example.niuzhaojie.myapplication.R;

/**
 * Created by niuzhaojie on 16/9/20.
 */
public class ViewRelatedActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView();


//        setContentView1();


//        setContentView2();


        setContentView3();

    }

    private void setContentView3() {

        setContentView(R.layout.activity_counterview);

    }

    private void setContentView2() {

        setContentView(R.layout.activity_myview);

    }

    private void setContentView1() {

        setContentView(R.layout.activity_simplelayout);

    }

    private void setContentView() {
        setContentView(R.layout.activity_viewrelated);

        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.main_layout);

        LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View buttonLayout = layoutInflater.inflate(R.layout.button_layout, null);

        mainLayout.addView(buttonLayout);


        Log.e("tag", mainLayout.getParent() + "");
    }
}
