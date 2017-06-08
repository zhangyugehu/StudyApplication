package com.thssh.example.widget;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.thssh.example.R;
import com.thssh.example.view.AppWattingDialog;

import java.util.Random;

public class WaveProgressViewActivity extends AppCompatActivity {

    private AppWattingDialog wattingDialog;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(wattingDialog != null){ wattingDialog.dismiss(); }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wave_progress_view);
        wattingDialog = AppWattingDialog.newBuilder(this).create();
    }

    public void wattingClick(View view) {
        wattingDialog.show();
        handler.sendEmptyMessageDelayed(0, new Random().nextInt(10) * 1000);
    }
}
