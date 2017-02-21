package com.example.niuzhaojie.myapplication.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;

import com.example.niuzhaojie.myapplication.Interface.IUser;
import com.example.niuzhaojie.myapplication.R;
import com.example.niuzhaojie.myapplication.utils.ToastUtils;
import com.example.niuzhaojie.myapplication.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IUser {


    private RecyclerView recyclerView;

    private List<MyModel> listData = new ArrayList<MyModel>();
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new MyAdapter(myOnItemClickListener);
        recyclerView.setAdapter(adapter);

        initData();
        start();

    }

    private void initData() {
        listData.clear();

        listData.add(new MyModel(FrescoActivity.class, "fresco图片加载框架"));
        listData.add(new MyModel(StatusBarActivity.class, "状态栏相关"));
        listData.add(new MyModel(SystemPluginActivity.class, "系统小工具"));
        listData.add(new MyModel(ScreenActivity.class, "屏幕相关"));
        listData.add(new MyModel(TestListViewActivity.class, "测试listview"));
        listData.add(new MyModel(EventDistributeActivity.class, "事件分发"));
        listData.add(new MyModel(ScrollTestActivity.class, "ScrollerLayout"));
        listData.add(new MyModel(AnimatorActivity.class, "属性动画"));
        listData.add(new MyModel(ListViewAsyncActivity.class, "异步加载不混乱"));
        listData.add(new MyModel(VolleyTestActivity.class, "volley框架"));
        listData.add(new MyModel(ViewRelatedActivity.class, "view相关"));
        listData.add(new MyModel(LoggerActivity.class, "LoggerUtils相关"));
        listData.add(new MyModel(RoundImageViewActivity.class, "圆角view"));
        listData.add(new MyModel(ViewPagerActivity.class, "ViewPager"));
        listData.add(new MyModel(RecycleViewActivity.class, "recycleView"));
        listData.add(new MyModel(TakePhotoActivity.class, "拍照相关"));
        listData.add(new MyModel(SoftKeyBoardActivity.class, "软键盘相关"));
        listData.add(new MyModel(BottomSheetActivity.class, "BottonSheet相关"));
        listData.add(new MyModel(DataBindingActivity.class, "DataBinding（数据绑定）"));
        listData.add(new MyModel(LevelListDtawableActivity.class, "LevelListDtawable"));
        listData.add(new MyModel(IntentRelatedActivity.class, "Intent相关"));
        listData.add(new MyModel(DataStorageRelatedActivity.class, "数据存储相关"));
        listData.add(new MyModel(MyCalendarActivity.class, "自定义一个Calendar"));
        listData.add(new MyModel(AIDLTestActivity.class, "AIDL测试"));
        listData.add(new MyModel(PermissionsRelatedActivity.class, "权限相关内容"));

        adapter.notifyDataSetChanged();

    }

    private MyOnItemClickListener myOnItemClickListener = new MyOnItemClickListener() {
        @Override
        public void onItemClick(View v, int position) {
            MyModel model = listData.get(position);

            if (model != null) {
                Intent intent = new Intent(MainActivity.this, model.getCls());
                startActivity(intent);
            }

        }
    };

    @Override
    protected void onStart() {
        super.onStart();

//        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("te:19299293"));
//        if (Utils.IsIntentSafe(intent, this)) {
//            startActivity(intent);
//        } else {
//            ToastUtils.showToast(this, "MDZZ-------->>妈的智障");
//        }


//        Intent intent = new Intent(Intent.ACTION_MEDIA_SHARED);
//        Utils.ResolveActivity(intent, this);


    }

    @Override
    public void login() {
        Log.e("111", "2222");
    }

    @Override
    public void userInfo() {
        Log.e("111", "3333");

    }

    @Override
    public void start() {
        Log.e("111", "1111");
    }

    private class MyAdapter extends RecyclerView.Adapter<ViewHolder> {

        private MyOnItemClickListener myOnItemClickListener;

        public MyAdapter(MyOnItemClickListener myOnItemClickListener) {
            this.myOnItemClickListener = myOnItemClickListener;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            int layout_id = -1;
            layout_id = R.layout.item_main;
            return new ViewHolder(LayoutInflater.from(MainActivity.this).inflate(layout_id, parent, false), myOnItemClickListener);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            MyModel model = listData.get(position);

            holder.item_btn.setText(model.getTag());

        }

        @Override
        public int getItemCount() {
            return listData.size();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Button item_btn;
        private MyOnItemClickListener myOnItemClickListener;

        public ViewHolder(View itemView, MyOnItemClickListener myOnItemClickListener) {
            super(itemView);
            item_btn = (Button) itemView.findViewById(R.id.item_btn);
            item_btn.setOnClickListener(this);
            this.myOnItemClickListener = myOnItemClickListener;
        }

        @Override
        public void onClick(View v) {
            if (myOnItemClickListener != null) {
                myOnItemClickListener.onItemClick(v, getAdapterPosition());
            }
        }
    }

    class MyModel {
        private Class<?> cls;
        private String tag;

        public MyModel(Class<?> cls, String tag) {
            this.cls = cls;
            this.tag = tag;
        }

        public Class<?> getCls() {
            return cls;
        }

        public void setCls(Class<?> cls) {
            this.cls = cls;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }
    }

    public interface MyOnItemClickListener {
        void onItemClick(View v, int position);
    }

}
