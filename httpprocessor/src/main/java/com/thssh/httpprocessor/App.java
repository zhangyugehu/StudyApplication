package com.thssh.httpprocessor;

import android.app.Application;

import com.thssh.httpprocessor.impl.HttpDelegater;
import com.thssh.httpprocessor.impl.OkHttpProcessor;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/05/25
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        HttpDelegater.init(new VolleyProcessor(this));
        HttpDelegater.init(new OkHttpProcessor());
    }
}
