package com.thssh.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Created by zhang on 2017/5/3.
 */

public class PercentRelativeLayout extends RelativeLayout{
    private static final String TAG = "PercentRelativeLayout";

    public PercentRelativeLayout(Context context) {
        super(context);
    }

    public PercentRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PercentRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 父容器的宽高
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        int childCount = getChildCount();
        for(int i=0; i<childCount; i++){
            View child = getChildAt(i);
            ViewGroup.LayoutParams layoutParams = child.getLayoutParams();
            if(layoutParams instanceof LayoutParams){
                float widthPercent = ((LayoutParams) layoutParams).getWidthPercent();
                float heightPercent = ((LayoutParams) layoutParams).getHeightPercent();

                if(widthPercent != 0){
                    layoutParams.width = (int) (width * widthPercent);
                }
                if(heightPercent != 0){
                    layoutParams.height = (int) (height * heightPercent);
                }
            }
        }


        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    public static class LayoutParams extends RelativeLayout.LayoutParams{

        private final float DEF_WIDTH_PERCENT = 1;
        private final float DEF_HEIGHT_PERCENT = 1;

        private float mWidthPercent;
        private float mHeightPercent;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);

            TypedArray array = c.obtainStyledAttributes(attrs, R.styleable.PercentRelativeLayout);
            mWidthPercent = array.getFloat(R.styleable.PercentRelativeLayout_layout_widthPercent, DEF_WIDTH_PERCENT);
            mHeightPercent = array.getFloat(R.styleable.PercentRelativeLayout_layout_heightPercent, DEF_HEIGHT_PERCENT);
        }

        public float getHeightPercent() {
            return mHeightPercent;
        }

        public float getWidthPercent() {
            return mWidthPercent;
        }
    }
}
