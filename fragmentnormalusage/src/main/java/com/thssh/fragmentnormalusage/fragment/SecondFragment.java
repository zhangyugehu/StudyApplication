package com.thssh.fragmentnormalusage.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.thssh.fragmentnormalusage.R;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/06/22
 */

public class SecondFragment extends BaseFragment {
    public static final String TAG = "SecondFragment";


    private static final String PARAM_TEXT = "param_text";

    private TextView mContentTv;
    private String mText;

    public static SecondFragment newInstance(String text) {
        SecondFragment fragment = new SecondFragment();
        fragment.addParam(text);
        return fragment;
    }

    public void addParam(String text){
        Bundle args = new Bundle();
        args.putString(PARAM_TEXT, text);
        this.setArguments(args);
    }

    @Override
    protected void afterViewInited() {
        mContentTv.setText(mText);
    }

    @Override
    protected void initView(View view) {
        mContentTv = findViewById(R.id.content_tv);
    }

    @Override
    protected void initArgs(Bundle args) {
        if(args == null){ return; }
        mText = args.getString(PARAM_TEXT);
    }

    @Override
    protected int setLayoutResouces() {
        return R.layout.fragment_second;
    }
}
