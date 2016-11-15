package com.example.niuzhaojie.myapplication.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.example.niuzhaojie.myapplication.R;
import com.example.niuzhaojie.myapplication.utils.ToastUtils;

/**
 * Created by niuzhaojie on 16/11/8.
 */
public class IntentRelatedActivity extends Activity {
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1001;
    private final static String phoneNum = "13621647344";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intentrelated);

//        implicitIntent();

//        explicitIntent();

//        callSystemSMS();

//        createAlarm("这是一个大傻逼设的闹钟", 14, 55);

//        showAlarm();

//        capturePhoto();


//        Uri uri = Uri.parse("geo:47.6,-122.3");
//        showMap(uri);

        dialPhoneNumber(phoneNum);

//        openWifiSettings();
    }



    //显式intent
    private void explicitIntent() {
        ToastUtils.showToast(IntentRelatedActivity.this, "显式Intent");
        Intent intent = new Intent(IntentRelatedActivity.this, FrescoActivity.class);
        startActivity(intent);

    }

    //隐式intent
    private void implicitIntent() {
        ToastUtils.showToast(IntentRelatedActivity.this, "隐式Intent");
        Intent intent = new Intent();
        intent.setAction("nzj.......hhh");
        if (intent.resolveActivity(getPackageManager()) != null) {//隐式需要处理，不让容易报空指针异常
            startActivity(intent);
        }


    }

    //用intent调用系统浏览器
    private void callSystemBrowser() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.parse("http://www.hotelgg.com");
        intent.setData(uri);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    //intent调用系统相册
    private void callSystemPhotoAlbum() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    //intent调用系统短信
    private void callSystemSMS() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "你是大傻逼么");

        Intent chosser = Intent.createChooser(intent, "斯不斯傻逼啊！！！");//创建一个选择器

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(chosser);
        }
    }

    //intent调用系统电话
    private void callSystemCall() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.parse("tel:110");
        intent.setData(uri);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    //创建闹铃
    private void createAlarm(String message, int hour, int minutes) {
        Intent intent = new Intent();
        intent.setAction(AlarmClock.ACTION_SET_ALARM);
        intent.putExtra(AlarmClock.EXTRA_MESSAGE, message);
        intent.putExtra(AlarmClock.EXTRA_HOUR, hour);
        intent.putExtra(AlarmClock.EXTRA_MINUTES, minutes);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    //显式所有闹钟设置
    private void showAlarm() {
        ToastUtils.showToast(IntentRelatedActivity.this, "显式所有闹钟设置哦");
        Intent intent = new Intent(AlarmClock.ACTION_SHOW_ALARMS);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


    //以静态图像模式打开相机
    public void capturePhoto() {
        Intent intent = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 0);
        }
    }

    //在地图上显式
    public void showMap(Uri geoLocation) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            ToastUtils.showToast(IntentRelatedActivity.this, "他喵的，根本就没能调用啊");
        }
    }


    //拨打电话
    public void dialPhoneNumber(String phoneNumber) {

        if (Build.VERSION.SDK_INT < 23) {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + phoneNumber));
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }

        } else {//sdk不小于23时，检查权限

            if (ContextCompat.checkSelfPermission(IntentRelatedActivity.this, Manifest.permission.CALL_PHONE)
                    == PackageManager.PERMISSION_GRANTED) {//权限通过
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + phoneNumber));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }


            } else {//权限不通过

                //用户未给予权限时，弹出的提醒
                if (ActivityCompat.shouldShowRequestPermissionRationale(IntentRelatedActivity.this,
                        Manifest.permission.CALL_PHONE)) {

                    ToastUtils.showToast(IntentRelatedActivity.this, "操你大爷的，能给点权限么");

                } else {//系统弹出对话框请求用户是否给予权限

                    ActivityCompat.requestPermissions(IntentRelatedActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE},
                            MY_PERMISSIONS_REQUEST_CALL_PHONE);

                }


            }


        }


    }


    //打开设置界面
    public void openWifiSettings() {
        Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:" + phoneNum));
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    }

                } else {

                    Log.e("hhh", "onRequestPermissionsResult: 哎   还是没有赋予权限哦");

                }
                break;
        }
    }
}
