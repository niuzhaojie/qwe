package com.example.niuzhaojie.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.niuzhaojie.myapplication.R;

/**
 * Created by niuzhaojie on 16/10/11.
 */

public class TestFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Bundle bundle = getArguments();

        String note = bundle.getString("sb");

        View view = inflater.inflate(R.layout.fragment_test, container, false);

        TextView textView = (TextView) view.findViewById(R.id.textview);

        textView.setText(note == null ? "" : note);

        return view;
    }
}
