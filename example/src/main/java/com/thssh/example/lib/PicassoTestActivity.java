package com.thssh.example.lib;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.thssh.example.R;
import com.thssh.widget.CircleImageView;

import java.io.File;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/06/06
 */

public class PicassoTestActivity extends AppCompatActivity{
    private static final String TAG = "PicassoTestActivity";

    private RecyclerView rvList;
    private CircleImageView iv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        CircleImageView2 circleImageView2 = new CircleImageView2(this);
//        circleImageView2.setImageResource(R.mipmap.ic_launcher);
        setContentView(R.layout.activity_picasso_test);

//        rvList = (RecyclerView) findViewById(R.id.rv_list);
        iv = (CircleImageView) findViewById(R.id.iv_image);
        iv.setHasRing(true);
        iv.setRingWidth(10);
        iv.setRingColor(Color.WHITE);
//        iv.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.xyjy));
        iv.setImageResource(R.drawable.xyjy);

        Picasso.with(this)
                .load(new File(Environment.getExternalStorageDirectory(), "123.jpg"))
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher_round)
                .centerCrop()
                .resize(800, 800)
                .into(iv);
    }
}
