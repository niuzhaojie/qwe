package com.example.niuzhaojie.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.niuzhaojie.myapplication.R;
import com.example.niuzhaojie.myapplication.view.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by niuzhaojie on 16/10/13.
 */
public class RecycleViewActivity extends Activity {

    //    private LoadMoreRecyclerView recyclerView;
    private RecyclerView recyclerView;


    private List<String> mDatas;
    private SwipeRefreshLayout swipeRefreshLayout;


    private static final int LEFT_LAYOUT = 101;
    private static final int CENTER_LAYOUT = 201;
    private static final int RIGHT_LAYOUT = 301;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview);

        findViewById(R.id.swipe_container).setEnabled(false);

        initData();

        recyclerView = (RecyclerView) findViewById(R.id.id_recycleView);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));


//        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.HORIZONTAL));
//        recyclerView.addItemDecoration(new DividerGridItemDecoration(this));


        recyclerView.setAdapter(new MyAdapter());

//        recyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
//
//            @Override
//            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                Log.e("tag", "onScrollChange: " + scrollX + "; " + scrollY + "; " + oldScrollX + "; " + oldScrollY);
//
//            }
//        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                Log.e("tag", "onScrollStateChanged: " + newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.e("tag", "onScrolled: " + dx + "; " + dy);


                if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
                    int index = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();

                    Log.e("tag", index + "");
                }


            }
        });


//        initRefresh();
//
//        initLoadMore();

    }

//    int i = 0;
//
//    private void initLoadMore() {
//
//        recyclerView.setAutoLoadMoreEnable(true);
//
//        recyclerView.setLoadMoreListener(new LoadMoreRecyclerView.LoadMoreListener() {
//            @Override
//            public void onLoadMore() {
//                swipeRefreshLayout.setRefreshing(false);
//                swipeRefreshLayout.setEnabled(false);
//
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        swipeRefreshLayout.setEnabled(true);
//                        i++;
//                        getMoreData();
//                        if (5 == i) {
//                            recyclerView.notifyMoreFinish(false);
//                        } else {
//                            recyclerView.notifyMoreFinish(true);
//                        }
//
//                    }
//                }, 3000);
//            }
//        });
//    }
//
//    private void initRefresh() {
//        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
//
//
//        //设置刷新时动画的颜色，可以设置4个
//        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
//
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//
//
//            @Override
//            public void onRefresh() {
//                recyclerView.setAutoLoadMoreEnable(false);
//
//                new Handler().postDelayed(new Runnable() {
//
//                    @Override
//                    public void run() {
//                        swipeRefreshLayout.setRefreshing(false);
//
//                        getLastedData();
//                        recyclerView.getAdapter().notifyDataSetChanged();
//                        recyclerView.setAutoLoadMoreEnable(true);
//
//
//                    }
//                }, 2000);
//            }
//        });
//    }


    private void initData() {
        mDatas = new ArrayList<String>();
        for (int i = 'A', j = 0; i <= 'H'; i++, j++) {
            mDatas.add("" + (char) i + "; item:" + j);
        }
    }


    private void getMoreData() {
        for (int i = 0; i < 5; i++) {
            mDatas.add("more data: " + (i + 1));
        }
    }

    private void getLastedData() {
        mDatas.clear();
        for (int i = 0; i < 6; i++) {
            mDatas.add("new data: " + (i + 1));
        }
    }

    class MyAdapter extends RecyclerView.Adapter<ViewHolder> {


        @Override
        public int getItemViewType(int position) {

            switch (position % 3) {
                case 0:
                    return LEFT_LAYOUT;
                case 1:
                    return CENTER_LAYOUT;
                case 2:
                    return RIGHT_LAYOUT;
                default:
                    return -1;
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            int layout_id = -1;

            switch (viewType) {
                case LEFT_LAYOUT:
                    layout_id = R.layout.left_item;
                    break;
                case CENTER_LAYOUT:
                    layout_id = R.layout.center_item;
                    break;
                case RIGHT_LAYOUT:
                    layout_id = R.layout.right_item;
                    break;
                default:
                    layout_id = R.layout.item_home;
                    break;
            }
            ViewHolder holder = new ViewHolder(LayoutInflater.from(RecycleViewActivity.this).inflate(layout_id, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.tv.setText(mDatas.get(position));
        }

        @Override
        public int getItemCount() {
            if (mDatas != null && mDatas.size() > 0) {
                return mDatas.size();
            } else {
                return 0;
            }
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.id_num);
        }
    }


}
