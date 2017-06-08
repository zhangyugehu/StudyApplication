package com.thssh;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/05/19
 */

public class Message {

    public int what;
    public Object obj;
    /** 目标Handler */
    Handler target;

    @Override
    public String toString() {
        return obj.toString();
    }
}
