package com.thssh.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/05/25
 */

public class FlowLayout extends ViewGroup {
    private static final String TAG = "FlowLayout";

    private List<List<View>> linesList;
    private List<Integer> lineHeights;

    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        linesList = new ArrayList<>();
        lineHeights = new ArrayList<>();
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int suggestWidth = MeasureSpec.getSize(widthMeasureSpec);
        int suggestHeight = MeasureSpec.getSize(heightMeasureSpec);

        int measuredWidth = 0;
        int measuredHeight = 0;
        if(widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY){
            measuredWidth = suggestWidth;
            measuredHeight = suggestHeight;
        }else {
            /**
             * 记录每行最大宽度
             */
            int maxLineWidth = 0;
            /**
             * 记录每行最大高度
             */
            int maxLineHeight = 0;

            int childCount = getChildCount();
            List<View> viewList = new ArrayList<>();
            for (int i = 0; i < childCount; i++) {
                View child = getChildAt(i);
//                measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0);
                measureChild(child, widthMeasureSpec, heightMeasureSpec);
                MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();

                int iHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
                int iWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
                if (iWidth + maxLineWidth > suggestWidth) {
                    // 换行
                    // 记录当前行最大宽度，高度累加
                    measuredWidth = Math.max(measuredHeight, maxLineWidth);
                    measuredHeight += maxLineHeight;

                    // 记录信息
                    lineHeights.add(maxLineHeight);
                    linesList.add(viewList);

                    // 重置
                    maxLineWidth = iWidth;
                    maxLineHeight = iHeight;

                    // 新建一行viewlist，添加新一行的view
                    viewList = new ArrayList<>();
                    viewList.add(child);
                } else {
                    // 记录行内信息
                    // 宽度累加，高度比较
                    maxLineWidth += iWidth;
                    maxLineHeight = Math.max(maxLineHeight, iHeight);

                    viewList.add(child);
                }
            }
        }

        Log.d(TAG, "onMeasure: measuredHeight > " + measuredHeight);
        Log.d(TAG, "onMeasure: measuredWidth > " + measuredWidth);
        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int left = 0, top = 0, right = 0, bottom = 0;
        /** 左上角顶点*/
        int curTop = 0;
        int curLeft = 0;
        int lines = linesList.size();
        for(int i = 0; i < lines; i++){
            List<View> lineViews = linesList.get(i);
            int itemCount = lineViews.size();
            for(int j = 0; j < itemCount; j ++){
                View child = lineViews.get(j);
                MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
                left = curLeft + lp.leftMargin;
                top = curTop + lp.topMargin;
                right = left + child.getMeasuredWidth();
                bottom = top + child.getMeasuredHeight();
                child.layout(left, top, right, bottom);
                Log.d(TAG, "onLayout: l "+ left + "<> t " + top + "<> r " + right + "<> b " + bottom);
                curLeft += child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            }
            curTop += lineHeights.get(i);
            curLeft = 0;
        }
        linesList.clear();
        lineHeights.clear();
    }
}
