package com.thssh;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/05/19
 */

public class MessageQueue {

    Message[] items;
    int putIndex;
    int takeIndex;
    int count;

    private Lock lock;
    private Condition notEmpty;
    private Condition notFull;

    public MessageQueue() {
        this.items = new Message[50];
        lock = new ReentrantLock();
        notEmpty = lock.newCondition();
        notFull = lock.newCondition();
    }

    /**
     * 添加消息到消息队列（生产者）
     * @param msg
     */
    public void enqueueMessage(Message msg){
        try{
            lock.lock();
            // 消息队列已满，停止生产（子线程阻塞）
            if(count == items.length){
                // 等待新的消息加入消息队列，就继续执行
                try {
                    Log.log("enqueueMessage Full");
                    notFull.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            items[putIndex] = msg;
            putIndex = (++ putIndex == items.length) ? 0 : putIndex;
            count ++;
            notEmpty.signal();
        }finally {
            lock.unlock();
        }
    }

    /**
     * 获取队列中的消息（消费者）
     * @return
     */
    public Message next(){
        Message msg = null;
        try {
            lock.lock();
            // 消息队列被掏空，停止消费（主线程阻塞）
            if (count == 0) {
                try {
                    Log.log("next empty");
                    notEmpty.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            msg = items[takeIndex];
            items[takeIndex] = null;
            takeIndex = (++takeIndex == items.length) ? 0 : takeIndex;
            count--;
            notFull.signal();
        }finally {
            lock.unlock();
        }
        return msg;
    }
}
