package com.example.niuzhaojie.myapplication.view.calendar;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.niuzhaojie.myapplication.R;
import com.example.niuzhaojie.myapplication.model.DayModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by niuzhaojie on 16/11/11.
 */

public class MyCalendar extends LinearLayout {


    private Context mContext;
    private View mContentView;
    private RecyclerView calendarRecycleView;

    private GridLayoutManager mGridLayoutManager;


    List<DayModel> list = new ArrayList<>();

    private int mParentWidth;


    public MyCalendar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;

        DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
        mParentWidth = metrics.widthPixels;

        initData();
        initView();
    }

    private void initView() {

        mGridLayoutManager = new GridLayoutManager(mContext, 7);
        mGridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {


                if (list.get(position).getMonth() != null) {
                    return 7;
                } else {
                    return 1;
                }

            }
        });

        mContentView = LayoutInflater.from(mContext).inflate(R.layout.view_calendar, this);
        calendarRecycleView = (RecyclerView) mContentView.findViewById(R.id.calendar_recycleView);
        calendarRecycleView.setLayoutManager(mGridLayoutManager);
//        calendarRecycleView.addItemDecoration(new RecyclerView.ItemDecoration() {
//
//            @Override
//            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//                super.getItemOffsets(outRect, view, parent, state);
//
//                GridLayoutManager.LayoutParams layoutParams = (GridLayoutManager.LayoutParams) view.getLayoutParams();
//                int spanSize = layoutParams.getSpanSize();
//                int spanIndex = layoutParams.getSpanIndex();
//                outRect.top = 20;
//                if (spanSize != mGridLayoutManager.getSpanCount()) {
//                    if (spanIndex == 0) {
//                        outRect.right = 10;
//                    } else if (spanIndex == 6) {
//                        outRect.left = 10;
//                    } else {
//                        outRect.right = 10;
//                        outRect.left = 10;
//
//                    }
//                }
//
//            }
//        });

        initAdapter();

    }

    private void initAdapter() {
        calendarRecycleView.setAdapter(new MyAdapter());
    }

    private void initData() {

        list.clear();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);


        DayModel day;
        for (int i = 0; i < 13; i++) {
            calendar.add(Calendar.MONTH, i == 0 ? 0 : 1);

            day = new DayModel();
            day.setMonth(new SimpleDateFormat("yyyy年 MM月").format(calendar.getTime()));
            day.setDate(calendar.getTime());

            list.add(day);

            checkDataInfo(i);
            getAllDayInMonth(i);
        }


        for (int i = 0; i < list.size(); i++) {
        }
    }


    /**
     * 补全每月开始时第一天不是星期天的数据
     */
    private void checkDataInfo(int monthDiff) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, monthDiff);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        if (dayOfWeek > 1) {
            for (int i = 1; i < dayOfWeek; i++) {
                list.add(new DayModel());
            }
        }
    }


    /**
     * 添加完该月所有的数据
     */
    private void getAllDayInMonth(int monthDiff) {

        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.MONTH, monthDiff);

        calendar.set(Calendar.DAY_OF_MONTH, 1);

        int maxDayInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);


        DayModel model;
        for (int i = 0; i < maxDayInMonth; i++) {
            calendar.set(Calendar.DAY_OF_MONTH, i + 1);

            model = new DayModel();
            model.setDate(calendar.getTime());

            list.add(model);
        }

    }


    class MyAdapter extends RecyclerView.Adapter<ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.gridview_calendar, parent, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            DayModel day = list.get(position);

            if (day != null) {
                if (day.getMonth() != null) {
                    holder.dayTxt.setText(day.getMonth());

                    holder.dayTxt.setWidth(mParentWidth);
                    holder.dayTxt.setHeight(mParentWidth / 7);
                    holder.dayTxt.setTextSize(16);
                    holder.dayTxt.setTextColor(Color.parseColor("#000000"));
                    holder.dayTxt.setEnabled(false);
                } else {
                    if (day.getDate() != null) {
                        Calendar calendar = Calendar.getInstance();
                        if (day.getDate().before(calendar.getTime())) {
                            holder.dayTxt.setWidth(mParentWidth / 7);
                            holder.dayTxt.setHeight(mParentWidth / 7);

                            holder.dayTxt.setText(new SimpleDateFormat("dd日").format(day.getDate()));
                            holder.dayTxt.setTextSize(14);
                            holder.dayTxt.setTextColor(Color.parseColor("#aaaaaa"));

                            holder.dayTxt.setSelected(false);

                        } else {
                            holder.dayTxt.setWidth(mParentWidth / 7);
                            holder.dayTxt.setHeight(mParentWidth / 7);

                            holder.dayTxt.setText(new SimpleDateFormat("dd日").format(day.getDate()));
                            holder.dayTxt.setTextSize(14);
                            holder.dayTxt.setTextColor(Color.parseColor("#000000"));

                            holder.dayTxt.setSelected(day.isSelected());
                            holder.dayTxt.setTag(position);
                            holder.dayTxt.setOnClickListener(mOnclickListener);
                        }


                    } else {
                        holder.dayTxt.setText("");
                        holder.dayTxt.setSelected(false);

                    }
                }
            } else {
                holder.dayTxt.setText("无");
                holder.dayTxt.setSelected(false);
            }
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView dayTxt;

        public ViewHolder(View itemView) {
            super(itemView);
            dayTxt = (TextView) itemView.findViewById(R.id.day_txt);
        }
    }


    private OnClickListener mOnclickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            int index = (int) v.getTag();

            DayModel model = list.get(index);


            if (model.isSelected()) {
                model.setSelected(false);
            } else {
                model.setSelected(true);
            }

            list.set(index, model);
            calendarRecycleView.getAdapter().notifyDataSetChanged();
        }
    };


}
