package com.example.niuzhaojie.myapplication.view.calendar;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.niuzhaojie.myapplication.R;
import com.example.niuzhaojie.myapplication.view.DividerItemDecoration;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by niuzhaojie on 16/11/11.
 */

public class MyCalendar extends LinearLayout {

    private Context mContext;

    private View mContentView;
    private RecyclerView calendarRecycleView;

    private Calendar mCalendar;

    private List<CalendarMode> list;


    public MyCalendar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView(context);
    }


    private void initView(Context context) {
        mContentView = LayoutInflater.from(context).inflate(R.layout.view_calendar, this);
        calendarRecycleView = (RecyclerView) mContentView.findViewById(R.id.calendar_recycleView);
        calendarRecycleView.setLayoutManager(new LinearLayoutManager(context));
        calendarRecycleView.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));
        initAdapter();
        initData();
    }


    private void initAdapter() {

        MyAdapter adapter = new MyAdapter();
        calendarRecycleView.setAdapter(adapter);


    }


    private void initData() {
        list = new ArrayList<>();
        getAllMonth();


//        for (int i = 0; i < list.size(); i++) {
//
//            for (int i1 = 0; i1 < list.get(i).getDays().size(); i1++) {
//
//                Log.e("tag", list.get(i).getDays().get(i1) + "");
//
//            }
//
//        }
    }


    /**
     * 获取所有的月份
     */
    private void getAllMonth() {

        CalendarMode mode;

        for (int i = 0; i < 13; i++) {
            mCalendar = Calendar.getInstance();
            mCalendar.add(Calendar.MONTH, i);
            mode = new CalendarMode();
            mode.setYearAndMonth(mCalendar.get(Calendar.YEAR) + "年" + (mCalendar.get(Calendar.MONTH) + 1) + "月");

            getAllDays(mCalendar, mode);
        }
    }

    /**
     * 获取该月份下所有的天
     *
     * @param calendar
     * @param mode
     */
    private void getAllDays(Calendar calendar, CalendarMode mode) {

        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int maxDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);


        mode.getDays().clear();
        for (int i = 0; i < day; i++) {

            calendar.set(Calendar.DAY_OF_MONTH, i + 1);

            mode.getDays().add(calendar.getTime());
        }

        for (int i = day; i < maxDays; i++) {
            calendar.set(Calendar.DAY_OF_MONTH, i + 1);

            mode.getDays().add(calendar.getTime());
        }

        checkDataInfo(mode);

        list.add(mode);
    }

    /**
     * 检查补全数据
     */
    private void checkDataInfo(CalendarMode mode) {


        Calendar tmp = Calendar.getInstance();
        tmp.setTime(mode.getDays().get(0));


        int index = tmp.get(Calendar.DAY_OF_WEEK);


//        Log.e("tag", tmp.getTime() + "; " + index);
        if (index > 1) {
            for (int i = 1; i < index; i++) {
                mode.getDays().add(0, null);
            }
        }


    }


    class MyAdapter extends RecyclerView.Adapter<ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.gridview_calendar, parent, false));
        }


        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            setDayTxt(holder, list.get(position));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title_txt;
        private LinearLayout weekLL1;
        private LinearLayout weekLL2;
        private LinearLayout weekLL3;
        private LinearLayout weekLL4;
        private LinearLayout weekLL5;


        private List<TextView> textViews = new ArrayList<>();


        public ViewHolder(View itemView) {
            super(itemView);
            title_txt = (TextView) itemView.findViewById(R.id.title_txt);
            weekLL1 = (LinearLayout) itemView.findViewById(R.id.week_ll1);
            weekLL2 = (LinearLayout) itemView.findViewById(R.id.week_ll2);
            weekLL3 = (LinearLayout) itemView.findViewById(R.id.week_ll3);
            weekLL4 = (LinearLayout) itemView.findViewById(R.id.week_ll4);
            weekLL5 = (LinearLayout) itemView.findViewById(R.id.week_ll5);

            textViews.add((TextView) itemView.findViewById(R.id.day_txt11));
            textViews.add((TextView) itemView.findViewById(R.id.day_txt12));
            textViews.add((TextView) itemView.findViewById(R.id.day_txt13));
            textViews.add((TextView) itemView.findViewById(R.id.day_txt14));
            textViews.add((TextView) itemView.findViewById(R.id.day_txt15));
            textViews.add((TextView) itemView.findViewById(R.id.day_txt16));
            textViews.add((TextView) itemView.findViewById(R.id.day_txt17));

            textViews.add((TextView) itemView.findViewById(R.id.day_txt21));
            textViews.add((TextView) itemView.findViewById(R.id.day_txt22));
            textViews.add((TextView) itemView.findViewById(R.id.day_txt23));
            textViews.add((TextView) itemView.findViewById(R.id.day_txt24));
            textViews.add((TextView) itemView.findViewById(R.id.day_txt25));
            textViews.add((TextView) itemView.findViewById(R.id.day_txt26));
            textViews.add((TextView) itemView.findViewById(R.id.day_txt27));

            textViews.add((TextView) itemView.findViewById(R.id.day_txt31));
            textViews.add((TextView) itemView.findViewById(R.id.day_txt32));
            textViews.add((TextView) itemView.findViewById(R.id.day_txt33));
            textViews.add((TextView) itemView.findViewById(R.id.day_txt34));
            textViews.add((TextView) itemView.findViewById(R.id.day_txt35));
            textViews.add((TextView) itemView.findViewById(R.id.day_txt36));
            textViews.add((TextView) itemView.findViewById(R.id.day_txt37));

            textViews.add((TextView) itemView.findViewById(R.id.day_txt41));
            textViews.add((TextView) itemView.findViewById(R.id.day_txt42));
            textViews.add((TextView) itemView.findViewById(R.id.day_txt43));
            textViews.add((TextView) itemView.findViewById(R.id.day_txt44));
            textViews.add((TextView) itemView.findViewById(R.id.day_txt45));
            textViews.add((TextView) itemView.findViewById(R.id.day_txt46));
            textViews.add((TextView) itemView.findViewById(R.id.day_txt47));

            textViews.add((TextView) itemView.findViewById(R.id.day_txt51));
            textViews.add((TextView) itemView.findViewById(R.id.day_txt52));
            textViews.add((TextView) itemView.findViewById(R.id.day_txt53));
            textViews.add((TextView) itemView.findViewById(R.id.day_txt54));
            textViews.add((TextView) itemView.findViewById(R.id.day_txt55));
            textViews.add((TextView) itemView.findViewById(R.id.day_txt56));
            textViews.add((TextView) itemView.findViewById(R.id.day_txt57));

            textViews.add((TextView) itemView.findViewById(R.id.day_txt61));
            textViews.add((TextView) itemView.findViewById(R.id.day_txt62));
            textViews.add((TextView) itemView.findViewById(R.id.day_txt63));
            textViews.add((TextView) itemView.findViewById(R.id.day_txt64));
            textViews.add((TextView) itemView.findViewById(R.id.day_txt65));
            textViews.add((TextView) itemView.findViewById(R.id.day_txt66));
            textViews.add((TextView) itemView.findViewById(R.id.day_txt67));


        }

    }


    private void setDayTxt(ViewHolder holder, CalendarMode mode) {

        holder.title_txt.setText(mode.getYearAndMonth());
        holder.title_txt.setVisibility(View.VISIBLE);


        for (int i = 0; i < mode.getDays().size(); i++) {

            TextView textView = holder.textViews.get(i);

            Date tmp = mode.getDays().get(i);


            if (tmp != null) {
                String str = new SimpleDateFormat("yyyy年MM月dd日").format(tmp);

                Log.e("tag", str + "<<<<<<<<<<<<<<<<<<<<<<<<<");
            }


            if (tmp != null) {
                textView.setText(new SimpleDateFormat("dd日").format(tmp));
            } else {
                textView.setText("");
            }
            textView.setVisibility(View.VISIBLE);
        }


    }

}
