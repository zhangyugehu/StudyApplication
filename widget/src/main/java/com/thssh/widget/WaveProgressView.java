package com.thssh.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/06/07
 */

public class WaveProgressView extends View {

    private Bitmap mSrcBitmap;
    private Bitmap mDstBitmap;

    private Paint mDstPaint;
    private Paint mWavePaint;
    private Paint mCoverPaint;
    private Path mPath;

    private int mHeight;
    private int mWidth;

    /** 波长*/
    private int mWaveLength;
    /** 波的高度 */
    private int mWaveHeight;
    /** 总高度 */
    private int mOriginY;
    /** 波偏移量 */
    private int dx;
    /** 波浪最高高度 */
    private int mMaxHeight;

    private ValueAnimator mWaveHighAnimator;
    private ValueAnimator mWaveAnimator;

    public WaveProgressView(Context context) {
        this(context, null);
    }

    public WaveProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaveProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mDstBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_docmail);
        mWidth = mDstBitmap.getWidth();
        mHeight = mDstBitmap.getHeight();
        mSrcBitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);

        mPath = new Path();

        mDstPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mWavePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCoverPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mWavePaint.setColor(Color.RED);
        mCoverPaint.setColor(Color.GRAY);

        mWaveHeight = 60;
        mWaveLength = mWidth / 2;
        mMaxHeight = mHeight * 2 / 5;

        Log.d("WaveProgressView", "init mMaxHeight "+mMaxHeight);

        startWaveAnim();
        startHeightAnim();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int suggestWith = MeasureSpec.getSize(widthMeasureSpec);
        int suggestHeight = MeasureSpec.getSize(heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int measuredWidth = suggestWith;
        int measuredHeight = suggestHeight;

        if(widthMode == MeasureSpec.EXACTLY){
            measuredWidth = suggestWith;
        }else if(widthMode == MeasureSpec.AT_MOST){
            int width = getPaddingLeft() + getPaddingRight() + mDstBitmap.getWidth();
            measuredWidth = Math.min(width, suggestWith);
        }

        if(heightMode == MeasureSpec.EXACTLY){
            measuredHeight = suggestHeight;
        }else if(widthMode == MeasureSpec.AT_MOST){
            int height = getPaddingTop() + getPaddingBottom() + mDstBitmap.getHeight();
            measuredHeight = Math.min(height, suggestHeight);
        }

        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Canvas srcCanvas = new Canvas(mSrcBitmap);
        srcCanvas.drawColor(Color.GRAY, PorterDuff.Mode.CLEAR);
        setPathData();
        srcCanvas.drawPath(mPath, mWavePaint);

        Matrix matrix = getViewMatrix();
        canvas.drawBitmap(mDstBitmap, matrix, mDstPaint);
        mDstPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));
        canvas.drawBitmap(mSrcBitmap, matrix, mDstPaint);

        mDstPaint.setXfermode(null);
    }

    private Matrix getViewMatrix() {
        int measuredHeight = getMeasuredHeight();
        int measuredWidth = getMeasuredWidth();
        float xScale = measuredWidth * 1.0f / mDstBitmap.getWidth();
        float yScale = measuredHeight * 1.0f / mDstBitmap.getHeight();
        float scale = Math.min(xScale, yScale);
        Matrix matrix = new Matrix();
        matrix.setScale(scale, scale);
        return matrix;
    }

    private void setPathData(){
        mPath.reset();
        int halfWaveLength = mWaveLength / 2;
        mPath.moveTo(-mWaveLength + dx, mOriginY);
        for(int i = -mWaveLength; i< mWidth + mWaveLength; i += mWaveLength){
            mPath.rQuadTo(halfWaveLength/2, -mWaveHeight, halfWaveLength, 0);
            mPath.rQuadTo(halfWaveLength/2, mWaveHeight, halfWaveLength, 0);
        }
        mPath.lineTo(mWidth, mHeight);
        mPath.lineTo(0, mHeight);
        mPath.close();
    }

    private void startWaveAnim() {
        mWaveAnimator = ValueAnimator.ofFloat(0, 1);
        mWaveAnimator.setDuration(1000);
        mWaveAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mWaveAnimator.setInterpolator(new LinearInterpolator());
        mWaveAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                dx = (int) (mWaveLength * value);
                postInvalidate();
            }
        });
        mWaveAnimator.start();
    }

    private void startHeightAnim() {
        mWaveHighAnimator = ValueAnimator.ofFloat(1, 0);
        mWaveHighAnimator.setDuration(12000);
        mWaveHighAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mWaveHighAnimator.setInterpolator(new LinearInterpolator());
        mWaveHighAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                mOriginY = (int) (mHeight * value);
                if(mOriginY < mMaxHeight){
                    mOriginY = mMaxHeight;
                    animation.cancel();
                }
                postInvalidate();
            }
        });
        mWaveHighAnimator.start();
    }

    public void reset(){
        mWaveAnimator.cancel();
        mWaveHighAnimator.cancel();
        mWaveAnimator.start();
        mWaveHighAnimator.start();
    }
}
