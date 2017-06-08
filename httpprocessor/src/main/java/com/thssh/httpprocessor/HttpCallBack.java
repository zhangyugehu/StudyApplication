package com.thssh.httpprocessor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/05/25
 */

public abstract class HttpCallBack<T> implements ICallBack {

    @Override
    public void onSuccess(String response) {
        try {
            Class<?> clazz = analysisClazzInfo(this);
            T object = (T) JSON.parseObject(response, clazz);
            onSuccess(object);
        }catch (Exception e){
            onFailure(e.toString());
        }

    }

    protected abstract void onSuccess(T response);

    private Class<?> analysisClazzInfo(Object obj){
        Type type = obj.getClass().getGenericSuperclass();
        Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
        return (Class<?>) actualTypeArguments[0];
    }
}
