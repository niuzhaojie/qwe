package com.example.niuzhaojie.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.niuzhaojie.myapplication.R;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.fesco_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, FrescoActivity.class));

            }
        });


        findViewById(R.id.statusBar_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, StatusBarActivity.class));
            }
        });


        findViewById(R.id.dialog_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SystemPluginActivity.class));
            }
        });


        findViewById(R.id.screenActivity_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ScreenActivity.class));

            }
        });


        findViewById(R.id.listview_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TestListViewActivity.class));
            }
        });


        findViewById(R.id.btn33).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, EventDistributeActivity.class));
            }
        });


        findViewById(R.id.btn34).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, ScrollTestActivity.class));

            }
        });


        findViewById(R.id.btn41).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AnimatorActivity.class));
            }
        });


        findViewById(R.id.btn42).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ListViewAsyncActivity.class));
            }
        });


        findViewById(R.id.btn43).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, VolleyTestActivity.class));
            }
        });


        findViewById(R.id.view_related).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ViewRelatedActivity.class));
            }
        });

        findViewById(R.id.loggerUtils).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoggerActivity.class));
            }
        });

        findViewById(R.id.roundImageView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RoundImageViewActivity.class));
            }
        });

        findViewById(R.id.viewPagerView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ViewPagerActivity.class));
            }
        });

        findViewById(R.id.recycleView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RecycleViewActivity.class));
            }
        });

        findViewById(R.id.takePhotoView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TakePhotoActivity.class));
            }
        });

        findViewById(R.id.softKeyBoard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SoftKeyBoardActivity.class));
            }
        });

        findViewById(R.id.bottomSheetView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BottomSheetActivity.class));
            }
        });

        findViewById(R.id.dataBinding).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DataBindingActivity.class));
            }
        });

        findViewById(R.id.levelListDrawable).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LevelListDtawableActivity.class));
            }
        });

        findViewById(R.id.intentRelaed_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, IntentRelatedActivity.class));
            }
        });

        findViewById(R.id.DataStorageRelated_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DataStorageRelatedActivity.class));
            }
        });

        findViewById(R.id.coustomCalendar_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MyCalendarActivity.class));
            }
        });

        findViewById(R.id.aidl_test_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AIDLTestActivity.class));
            }
        });

    }


}
