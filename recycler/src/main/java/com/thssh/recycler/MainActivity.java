package com.thssh.recycler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private RecyclerView mRecycleView;
    private List<MainBean> mDatas;
    private MainRecycleAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        generateMockData();
        initRecycleView();
        initAdapter();
        setAdapter();

    }

    private void generateMockData() {
        if(mDatas == null) { mDatas = new ArrayList<>(); }
        for(int i = 0; i< 10; i++) {
            Random r = new Random();
            int nextInt = r.nextInt(1000);
            mDatas.add(new MainBean("title" + nextInt, "des" + nextInt));
        }
    }

    private void initRecycleView() {
        mRecycleView = (RecyclerView) findViewById(R.id.recycle_view);
        mRecycleView.setLayoutManager(new GridLayoutManager(this, 3));
        mRecycleView.setItemAnimator(new DefaultItemAnimator());
        mRecycleView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecycleView.addOnItemTouchListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(RecyclerView view, int position) {
                toast("点击  " + position);
                if(position < 0 || position > mDatas.size()){ return; }
                mDatas.remove(position);
                mAdapter.notifyItemRemoved(position);
            }

            @Override
            public void onItemLongClick(RecyclerView view, int position) {
                toast("长按 " + position);
                if(position < 0 || position > mDatas.size()){ return; }
                mDatas.add(position, new MainBean("add", "i'm new added!" + position));
                mAdapter.notifyItemInserted(position);
            }
        });
    }

    private void initAdapter() {
        mAdapter = new MainRecycleAdapter(this, mDatas);
    }

    private void setAdapter() {
        mRecycleView.setAdapter(mAdapter);
    }

    private void toast(String text){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
