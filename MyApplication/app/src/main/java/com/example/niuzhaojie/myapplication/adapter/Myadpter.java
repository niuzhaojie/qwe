package com.example.niuzhaojie.myapplication.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.niuzhaojie.myapplication.R;

import java.util.List;

/**
 * Created by niuzhaojie on 16/9/14.
 */
public class Myadpter extends BaseAdapter {
    private Activity activity;
    private List<int[]> list;
    private String TAG = this.getClass().getSimpleName();

    public Myadpter(List<int[]> list, Activity activity) {
        this.list = list;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = activity.getLayoutInflater().inflate(R.layout.item_listview, null);
            holder.test_txt = (TextView) view.findViewById(R.id.tv_txt);
            holder.btn_add = (Button) view.findViewById(R.id.btn_add);
            holder.btn_minus = (Button) view.findViewById(R.id.btn_minus);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }


        holder.test_txt.setText("index: " + list.get(i)[0] + "; value: " + list.get(i)[1]);

        holder.btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value = list.get(i)[1];
                value++;
                list.get(i)[1] = value;
                notifyDataSetChanged();
            }
        });
        holder.btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value = list.get(i)[1];
                value--;
                list.get(i)[1] = value;
                notifyDataSetChanged();
            }
        });


//        holder.test_txt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.e(TAG, "onClick: " + list.get(i)[0]);
//            }
//        });

        return view;
    }

    class ViewHolder {
        private TextView test_txt;
        private Button btn_add, btn_minus;

    }

}
