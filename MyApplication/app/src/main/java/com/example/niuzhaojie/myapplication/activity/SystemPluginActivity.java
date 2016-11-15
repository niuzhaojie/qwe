package com.example.niuzhaojie.myapplication.activity;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;

import com.example.niuzhaojie.myapplication.R;
import com.example.niuzhaojie.myapplication.utils.StatusBarUtils;
import com.example.niuzhaojie.myapplication.utils.ToastUtils;

/**
 * Created by niuzhaojie on 16/9/19.
 */
public class SystemPluginActivity extends Activity {

    private final String TAG = this.getClass().getName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plugin);


        findViewById(R.id.showDialog_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        findViewById(R.id.showToast_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.showToast(SystemPluginActivity.this, "Things happend");

            }
        });
        findViewById(R.id.showSnackBar_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSnackBar(view, "kkkkkk");
            }
        });
        findViewById(R.id.showNofication_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNotification("title is here!!!!!", "content is here");
            }
        });
    }

    private void showNotification(String title, String content) {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        Notification notification = builder.setContentTitle(title)
                .setContentText(content)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setColor(Color.parseColor("#FF0000"))
                .build();
        manager.notify(1, notification);


    }

    private void showSnackBar(View view, String content) {
        Snackbar.make(view, content, Snackbar.LENGTH_SHORT).setAction("undo", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "onClick: " + 111111);
            }
        }).show();
    }


    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Title").setMessage("Message").setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).show();
    }


    @Override
    protected void onResume() {
        super.onResume();

        StatusBarUtils.setWindowStatusBarColor(this, Color.RED);


    }
}
