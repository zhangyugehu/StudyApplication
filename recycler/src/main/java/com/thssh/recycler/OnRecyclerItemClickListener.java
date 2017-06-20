package com.thssh.recycler;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/06/20
 */

public abstract class OnRecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
    private static final String TAG = "OnRecyclerItemClickList";

    private static final long DELAY_LONG_CLICK = 3 * 500;
    private static final int WHAT_LONG_CLICK = 0;

    private boolean preformClick;
    private float startY = -1;
    private RecyclerView mRecyclerView;
    private View mChildUnderView;
    private int mPosition;

    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case WHAT_LONG_CLICK:
                    preformClick = false;
                    onItemLongClick(mRecyclerView, mPosition);
                    break;
            }
        }
    };

    public abstract void onItemClick(RecyclerView view, int position);
    public void onItemLongClick(RecyclerView view, int position){}

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View childViewUnder = rv.findChildViewUnder(e.getX(), e.getY());
        int position = rv.getChildAdapterPosition(childViewUnder);
        switch (e.getAction()){
            case MotionEvent.ACTION_DOWN:
                startY = e.getY();
                preformClick = true;
                mRecyclerView = rv;
                mChildUnderView = childViewUnder;
                mPosition = position;
                handler.sendEmptyMessageDelayed(WHAT_LONG_CLICK, DELAY_LONG_CLICK);
                break;
            case MotionEvent.ACTION_MOVE:
                if(!preformClick){ break; }
                float dy = Math.abs(e.getY() - startY);
                if(dy > 20){
                    preformClick = false;
                    handler.removeMessages(WHAT_LONG_CLICK);
                }
                break;
            case MotionEvent.ACTION_UP:
                if(!preformClick){ break; }
                preformClick = false;
                handler.removeMessages(WHAT_LONG_CLICK);
                onItemClick(rv, position);
                break;
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
