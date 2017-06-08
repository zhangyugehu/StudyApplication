package com.thssh.httpprocessor.impl;

import android.os.Handler;
import android.os.Looper;

import com.thssh.httpprocessor.ICallBack;
import com.thssh.httpprocessor.IHttpProcessor;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/05/25
 */

public class OkHttpProcessor implements IHttpProcessor {

    private OkHttpClient okHttpClient;
    private Handler handler;

    public OkHttpProcessor() {
        okHttpClient = new OkHttpClient();
        handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void get(String url, Map<String, String> params, final ICallBack callBack) {

        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        enqueue(request, callBack);
    }

    @Override
    public void post(String url, Map<String, String> params, final ICallBack callBack) {
        FormBody.Builder builder = new FormBody.Builder();
        if(params != null &&  params.size() > 0){
            for(Map.Entry<String, String> entry : params.entrySet()){
                builder.add(entry.getKey(), entry.getValue());
            }
        }
        Request request = new Request.Builder()
                .post(builder.build())
                .url(url)
                .build();
        enqueue(request, callBack);
    }

    private void enqueue(Request request, final ICallBack callBack) {
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                publishFailure(callBack, e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    publishSuccess(callBack, response.body().string());
                }else{
                    publishFailure(callBack, response.message().toString());
                }
            }
        });
    }

    private void publishSuccess(final ICallBack callBack, final String response){
        handler.post(new Runnable() {
            @Override
            public void run() {
                callBack.onSuccess(response);
            }
        });
    }

    private void publishFailure(final ICallBack callBack, final String error){
        handler.post(new Runnable() {
            @Override
            public void run() {
                callBack.onFailure(error);
            }
        });
    }
}
