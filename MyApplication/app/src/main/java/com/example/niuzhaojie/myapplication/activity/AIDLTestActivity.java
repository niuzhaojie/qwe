package com.example.niuzhaojie.myapplication.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import com.example.niuzhaojie.myapplication.R;
import com.example.niuzhaojie.myapplication.databinding.ActivityAidlTestBinding;
import com.example.niuzhaojie.myapplication.utils.ToastUtils;
import com.niuzhaojie.aidlserver.IMyAidlInterface;
import com.niuzhaojie.aidlserver.IPersonAIDL;
import com.niuzhaojie.aidlserver.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by niuzhaojie on 16/11/15.
 */
public class AIDLTestActivity extends Activity {

    private List<Person> list = new ArrayList<>();

    private IMyAidlInterface iMyAidlInterface;

    private ServiceConnection conn = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);

            Log.e("tag", "onServiceConnected");

            try {
                ToastUtils.showToast(AIDLTestActivity.this, iMyAidlInterface.getNum(19) + "");
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e("tag", "onServiceDisconnected");

            iMyAidlInterface = null;
        }
    };

    private IPersonAIDL iPersonAIDL;
    private ServiceConnection conn2 = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iPersonAIDL = IPersonAIDL.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            iPersonAIDL = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAidlTestBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_aidl_test);


        bindService();
        bindService2();


        binding.startTestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    ToastUtils.showToast(AIDLTestActivity.this, iMyAidlInterface.getNum(1009) + "");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

            }
        });

        binding.addPersonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    list = iPersonAIDL.add(new Person("小李", 23));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }


                Log.e("tag", list.toString());
            }
        });
    }

    private void bindService() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.niuzhaojie.aidlserver", "com.niuzhaojie.aidlserver.RemoteService"));
        bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }

    private void bindService2() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.niuzhaojie.aidlserver", "com.niuzhaojie.aidlserver.PersonRemoteService"));
        bindService(intent, conn2, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
        unbindService(conn2);
    }
}
