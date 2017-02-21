package com.example.niuzhaojie.myapplication.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.example.niuzhaojie.myapplication.R;
import com.example.niuzhaojie.myapplication.utils.ToastUtils;

/**
 * Created by niuzhaojie on 17/2/21.
 */
public class PermissionsRelatedActivity extends Activity {

    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_related);
    }


    @Override
    protected void onStart() {
        super.onStart();


//        Intent intent = new Intent(Settings.ACTION_SECURITY_SETTINGS);
//        startActivity(intent);


//        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS);
//        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
//            Log.e("111", "权限获取成功");
//        } else if (permissionCheck == PackageManager.PERMISSION_DENIED) {
//            Log.e("111", "权限获取失败========>");
//        }


        checkPermission(Manifest.permission.READ_CONTACTS);

    }


    private void checkPermission(String permission) {
// Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                permission)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    permission)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                ToastUtils.showToast(this, "请在权限设置中手动赋予该应用权限");


                Intent intent = getAppDetailSettingIntent();
                startActivity(intent);

            } else {

                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{permission},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.

            }
        } else {
            ToastUtils.showToast(this, "权限ok");
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        switch (requestCode) {

            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                    ToastUtils.showToast(this, "成功get到权限");

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.

                    ToastUtils.showToast(this, "权限get失败,请手动设置权限");
                    Intent intent = getAppDetailSettingIntent();
                    startActivity(intent);
                }
                return;
            }


        }

    }

    /**
     * 获取应用详情页面intent
     *
     * @return
     */
    private Intent getAppDetailSettingIntent() {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", getPackageName());
        }
        return localIntent;
    }

}
