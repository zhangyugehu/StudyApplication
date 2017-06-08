package com.thssh;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/05/19
 */

public class Handler {

    private MessageQueue mQueue;
    private Looper mLooper;

    public Handler() {
        mLooper = Looper.myLooper();
        mQueue = mLooper.mQueue;
    }

    public void sendMessage(Message msg){
        // 指定目的地，用于msg回到handleMessage
        msg.target = this;
        mQueue.enqueueMessage(msg);
    }

    public void handleMessage(Message msg){

    }

    public void dispatchMessage(Message msg){
        handleMessage(msg);
    }
}
