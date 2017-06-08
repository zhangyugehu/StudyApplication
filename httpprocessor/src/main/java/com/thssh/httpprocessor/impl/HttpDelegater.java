package com.thssh.httpprocessor.impl;

import com.thssh.httpprocessor.ICallBack;
import com.thssh.httpprocessor.IHttpProcessor;

import java.util.Map;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/05/25
 */

public class HttpDelegater implements IHttpProcessor {
    private static IHttpProcessor mProcessor;

    public static void init(IHttpProcessor processor) {
        mProcessor = processor;
    }

    private static HttpDelegater sInstance = new HttpDelegater();
    private HttpDelegater(){}
    public static HttpDelegater get(){
        return sInstance;
    }

    @Override
    public void get(String url, Map<String, String> params, ICallBack callBack) {
        mProcessor.get(url, params, callBack);
    }

    @Override
    public void post(String url, Map<String, String> params, ICallBack callBack) {
        mProcessor.post(url, params, callBack);
    }
}
