package com.thssh.httpprocessor;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/05/25
 */

public interface ICallBack {
    void onSuccess(String response);
    void onFailure(String error);
}
