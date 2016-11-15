package com.example.niuzhaojie.myapplication.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import com.example.niuzhaojie.myapplication.R;
import com.example.niuzhaojie.myapplication.databinding.ActivityDatastorageRelatedBinding;
import com.example.niuzhaojie.myapplication.utils.ToastUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Created by niuzhaojie on 16/11/10.
 */
public class DataStorageRelatedActivity extends Activity {
    public static final String PREFS_NAME = "MyPrefsFile";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDatastorageRelatedBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_datastorage_related);

        binding.saveDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveDataBySharedPreference();
            }
        });

        binding.getDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataFromSharedPreference();
            }
        });

        binding.clearDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSharedPreference();

            }
        });

        binding.checkExternalStorageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isExternalStorageReadable()) {
                    Log.e("tag", "外部存储设备可读可写");
                }


                if (isExternalStorageWritable()) {
                    Log.e("tag", "外部存储设备，至少可读");
                }
            }
        });

    }

    /*sharedPreferences存储方式*/
    private void saveDataBySharedPreference() {
        SharedPreferences setting = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = setting.edit();
        editor.putString("sb", "你丫的真的是傻逼么");
        editor.commit();
    }

    private void getDataFromSharedPreference() {
        SharedPreferences setting = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String str = setting.getString("sb", "");
        ToastUtils.showToast(DataStorageRelatedActivity.this, str);
    }

    private void clearSharedPreference() {
        SharedPreferences setting = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = setting.edit();
        editor.clear();
        editor.commit();
        editor = null;
        setting = null;
        ToastUtils.showToast(DataStorageRelatedActivity.this, "数据已清空。。。。");
    }


    /*内部存储器*/
    private void saveDataByInternalStorage(String content) {
        try {
            FileOutputStream fos = openFileOutput(PREFS_NAME, MODE_PRIVATE);
            fos.write(content.getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }

    private void getDataFromInternalSorage() {
        try {
            FileInputStream fis = openFileInput(PREFS_NAME);
            //读取中文是乱码，使用bufferedReader读取
            BufferedReader br = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                builder.append(line);
            }
            ToastUtils.showToast(DataStorageRelatedActivity.this, builder.toString());
            br.close();
            fis.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }


    /*使用缓存文件*/
    private void saveDataByCache(String content) {

        File file;

        file = getCacheDir();


        try {
            FileOutputStream fos = new FileOutputStream(file);

            fos.write(content.getBytes());

            fos.close();


            ToastUtils.showToast(DataStorageRelatedActivity.this, "save success");

        } catch (FileNotFoundException e) {
            Log.e("tag", "file not found");
        } catch (IOException e) {
            Log.e("tag", "IOException");
        }

    }

    private void getDataFromCache() {

        File file = getFilesDir();


        try {
            FileInputStream fis = new FileInputStream(file);

            BufferedReader br = new BufferedReader(new InputStreamReader(fis, "UTF-8"));


            String line;
            StringBuilder builder = new StringBuilder();
            if ((line = br.readLine()) != null) {
                builder.append(line);
            }

            br.close();
            fis.close();


            ToastUtils.showToast(DataStorageRelatedActivity.this, builder.toString());


        } catch (FileNotFoundException e) {
        } catch (UnsupportedEncodingException e) {
        } catch (IOException e) {
        }


    }


    /*使用外部存储器*/
    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }
}
