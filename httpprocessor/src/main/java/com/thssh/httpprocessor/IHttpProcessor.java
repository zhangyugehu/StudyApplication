package com.thssh.httpprocessor;

import java.util.Map;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/05/25
 */

public interface IHttpProcessor {

    void get(String url, Map<String, String> params, ICallBack callBack);

    void post(String url, Map<String, String> params, ICallBack callBack);
}
