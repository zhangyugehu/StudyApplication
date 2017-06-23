package com.thssh.fragmentnormalusage.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.thssh.fragmentnormalusage.R;

import java.util.Random;


/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/06/22
 */

public class FirstFragment extends BaseFragment {

    public interface Listener {

        void openSecondFragment(String param);
    }

    public static final String TAG = "FirstFragment";
    private static final String PARAM_TEXT = "params_text";

    private Listener mListener;
    private TextView mContentTv;
    private Button mOpenBtn;
    private String mText;

    public static FirstFragment newInstance(String text) {

        Bundle args = new Bundle();
        args.putString(PARAM_TEXT, text);
        FirstFragment fragment = new FirstFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void setListener(Listener listener) {
        this.mListener = listener;
    }

    @Override
    protected void afterViewInited() {
        mContentTv.setText(mText);
        mOpenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.openSecondFragment("open from first fragment " + new Random().nextInt(100));
            }
        });
    }

    @Override
    protected void initView(View view) {
        mContentTv = findViewById(R.id.content_tv);
        mOpenBtn = findViewById(R.id.open_btn);
    }

    @Override
    protected void initArgs(Bundle args) {
        if(args == null){ return; }
        mText = args.getString(PARAM_TEXT);
    }

    @Override
    protected int setLayoutResouces() {
        return R.layout.fragment_first;
    }
}
