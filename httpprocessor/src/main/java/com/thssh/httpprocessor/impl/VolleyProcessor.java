package com.thssh.httpprocessor.impl;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.thssh.httpprocessor.ICallBack;
import com.thssh.httpprocessor.IHttpProcessor;

import java.util.Map;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/05/25
 */

public class VolleyProcessor implements IHttpProcessor {
    private static final String TAG = "VolleyProcessor";

    private static RequestQueue mQueue;

    public VolleyProcessor(Context context) {
        mQueue = Volley.newRequestQueue(context);
    }

    @Override
    public void get(String url, Map<String, String> params, final ICallBack callBack) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        callBack.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callBack.onFailure(error.toString());
                    }
                });
        mQueue.add(stringRequest);
    }

    @Override
    public void post(String url, Map<String, String> params, final ICallBack callBack) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        callBack.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callBack.onFailure(error.toString());
                    }
                });
        mQueue.add(stringRequest);
    }
}
