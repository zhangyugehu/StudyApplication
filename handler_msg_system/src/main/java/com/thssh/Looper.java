package com.thssh;

/**
 * 为什么MessageQueue放在Looper中：
 * 每个子线程都有自己的looper对象，通过sThreadLocal管理
 * 实现线程优化
 *
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/05/19
 */

public class Looper {

    MessageQueue mQueue;
    static final ThreadLocal<Looper> sThreadLocal = new ThreadLocal<Looper>();
    static Looper sMainLooper;

    public Looper() {
        mQueue = new MessageQueue();
    }

    /**
     * 实例化Looper对象并放入sThreadLocal中管理
     */
    public static void prepare(){
        if(sThreadLocal.get() != null){
            throw new RuntimeException("Only one Looper may be created per thread");
        }
        sThreadLocal.set(new Looper());
    }

    public static Looper myLooper() {
        return sThreadLocal.get();
    }

    public static void loop(){

        Looper me = myLooper();
        MessageQueue queue = me.mQueue;

        for(;;){
            Message msg = queue.next();
            if(msg == null){ continue; }
            // 转发
            msg.target.dispatchMessage(msg);
        }

    }
}
