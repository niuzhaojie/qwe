package com.example.niuzhaojie.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.niuzhaojie.myapplication.R;
import com.example.niuzhaojie.myapplication.adapter.Myadpter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by niuzhaojie on 16/9/14.
 */
public class TestListViewActivity extends Activity {

    ListView listView;
    private Myadpter myAdapter;
    private List<int[]> list = new ArrayList<int[]>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testlistview);


        list.clear();
        for (int i = 0; i < 100; i++) {
            int[] tmp = new int[2];
            tmp[0] = i;
            tmp[1] = 0;
            list.add(tmp);
        }


        myAdapter = new Myadpter(list, this);

        listView = (ListView) findViewById(R.id.main_listview);
        listView.setAdapter(myAdapter);

    }
}



