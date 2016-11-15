package com.example.niuzhaojie.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.niuzhaojie.myapplication.R;

/**
 * Created by niuzhaojie on 16/9/19.
 */
public class ImageAdapter2 extends ArrayAdapter<String> {


    public ImageAdapter2(Context context, int resource, String[] objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String imgUrl = getItem(position);
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_asynctest, null);
            holder.imageView = (ImageView) convertView.findViewById(R.id.image);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Glide.with(getContext()).load(imgUrl).placeholder(R.drawable.mdd_banner).error(R.drawable.mdd_beijing).crossFade().into(holder.imageView);

        return convertView;
    }

    private class ViewHolder {
        private ImageView imageView;
    }
}
