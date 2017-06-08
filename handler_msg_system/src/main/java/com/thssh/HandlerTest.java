package com.thssh;

import java.util.UUID;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/05/19
 */

public class HandlerTest {
    private static int count = 0;

    public static void main(String[] args) {


        Looper.prepare();

        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Log.log(Thread.currentThread().getName() + " >>> " + msg);
            }
        };
        new Thread() {
            @Override
            public void run() {

                int what = 0;
                int time = 100;
                while (--time > 0) {
                    new Thread() {
                        @Override
                        public void run() {
                            Message msg = new Message();
                            msg.what = 0;
                            msg.obj = "count" + count++;
                            Log.log(Thread.currentThread().getName() + " >>> " + msg);
                            handler.sendMessage(msg);
                        }
                    }.start();
                    what++;

//                    try {
//                        long sleeptime = 0;
//                        if(time < 30){ sleeptime = 1000; }
//                        Thread.sleep(sleeptime);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                }
                try {
                    Thread.sleep(20 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message msg = new Message();
                msg.what = 0;
                msg.obj = "last message";
                Log.log(Thread.currentThread().getName() + " >>> " + msg);
                handler.sendMessage(msg);
            }
        }.start();
        Looper.loop();

    }
}
