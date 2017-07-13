package com.example.niuzhaojie.myapplication.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.niuzhaojie.myapplication.Interface.LoadingMoreListener;
import com.example.niuzhaojie.myapplication.R;

/**
 * Created by niuzhaojie on 17/7/13.
 */

public class MyRecycleView extends RecyclerView implements View.OnTouchListener {

    private boolean isSlidingToLast;
    private boolean isToBottom;
    private MyAdapter mAdapter;
    private LoadingMoreListener listener;

    public MyRecycleView(Context context) {
        super(context);
        initRecycleView();
    }

    public MyRecycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initRecycleView();
    }

    public MyRecycleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initRecycleView();
    }

    private void initRecycleView() {


        this.addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
                    int totalItemCount = manager.getItemCount();
                    if (lastVisibleItem == (totalItemCount - 1) && isSlidingToLast) {
                        isToBottom = true;
                    }
                }
                ;
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                if (dy > 0) {
                    isSlidingToLast = true;
                } else {
                    isSlidingToLast = false;
                }

            }
        });
        this.setOnTouchListener(this);
    }

    public void setListener(LoadingMoreListener listener) {
        this.listener = listener;
    }

    private boolean showFooter;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN && isToBottom && listener != null) {
            showFooter = true;
        } else if (event.getAction() == MotionEvent.ACTION_MOVE && showFooter) {
            mAdapter.updateFooterView(event);
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            showFooter = false;
            mAdapter.initFooter();
            if (mAdapter.isLoadMore()) {
                listener.onLoadMore();
            }
        }
        return false;
    }

    @Override
    public void setAdapter(Adapter adapter) {
        if (adapter != null) {
            mAdapter = new MyAdapter(adapter);
        }
        super.swapAdapter(mAdapter, true);
    }


    private class MyAdapter extends RecyclerView.Adapter<ViewHolder> {

        private RecyclerView.Adapter mInternalAdapter;
        private final int[] ITEM_TYPES = new int[]{0, 1};

        private boolean loadMore;

        public boolean isLoadMore() {
            return loadMore;
        }

        public MyAdapter(Adapter mInternalAdapter) {
            this.mInternalAdapter = mInternalAdapter;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == ITEM_TYPES[1]) {
                return new FooterHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.footer_view, parent, false));
            } else if (viewType == ITEM_TYPES[0]) {
                return mInternalAdapter.onCreateViewHolder(parent, viewType);
            } else {
                return null;
            }
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            if (getItemViewType(position) == ITEM_TYPES[1]) {
                footerItemTask((FooterHolder) holder);
            } else {
                mInternalAdapter.onBindViewHolder(holder, position);
            }
        }

        @Override
        public int getItemCount() {
            return mInternalAdapter.getItemCount() + 1;
        }

        @Override
        public int getItemViewType(int position) {
            if (position == getItemCount() - 1) {
                return ITEM_TYPES[1];//底部footerView
            } else {
                return ITEM_TYPES[0];
            }
        }

        private int mFooterHeight;
        private FooterHolder footerHolder;

        private void footerItemTask(FooterHolder holder) {
            footerHolder = holder;
            initFooter();
        }

        public void initFooter() {
            if (footerHolder != null) {
                int width = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                int height = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                footerHolder.view.measure(width, height);
                height = footerHolder.view.getMeasuredHeight();
                if (mFooterHeight == 0) {
                    mFooterHeight = height;
                }
                if (height != 0) {
                    footerHolder.view.setPadding(0, 0, 0, -height);
                    footerHolder.mTextView.setText("上拉加载更多");
                }
            }
        }

        private float initY = -1;


        public void updateFooterView(MotionEvent event) {


            if (footerHolder != null && event.getAction() == MotionEvent.ACTION_MOVE) {
                if (initY == -1) {
                    initY = event.getY();
                }
                loadMore = false;
                if (event.getY() < initY) {
                    float distence = initY - event.getY();
                    distence *= 0.51;
                    if (distence <= 300) {
                        footerHolder.mTextView.setText("上拉加载更多");
                    } else {
                        footerHolder.mTextView.setText("松开加载更多");
                        loadMore = true;
                    }
                    footerHolder.view.setPadding(0, 0, 0, (int) (distence - mFooterHeight));
                } else {
                    footerHolder.mTextView.setText("上拉加载更多");
                }
            }
        }
    }


    private class FooterHolder extends RecyclerView.ViewHolder {
        View view;
        TextView mTextView;

        public FooterHolder(View itemView) {
            super(itemView);
            view = itemView;
            mTextView = (TextView) itemView.findViewById(R.id.hint_txt);
        }
    }
}

