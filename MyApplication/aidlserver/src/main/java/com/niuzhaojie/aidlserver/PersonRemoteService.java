package com.niuzhaojie.aidlserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by niuzhaojie on 16/11/15.
 */

public class PersonRemoteService extends Service {

    private List<Person> persons;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        persons = new ArrayList<>();
        return mBinder;
    }


    private final IPersonAIDL.Stub mBinder = new IPersonAIDL.Stub() {

        @Override
        public List<Person> add(Person person) throws RemoteException {
            persons.add(person);
            return persons;
        }
    };


}
