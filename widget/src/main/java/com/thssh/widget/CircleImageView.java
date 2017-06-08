package com.thssh.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * 圆角头像
 *
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/06/08
 */

public class CircleImageView extends android.support.v7.widget.AppCompatImageView {

    private static final int DEF_RING_WIDTH = 20;
    private static final int DEF_RING_COLOR = Color.RED;

    private Bitmap mBitmap;
    private Bitmap mDstBitmap;
    private Bitmap mSRCBitmap;
    private Bitmap mRingBitmap;

    private Paint mDstPaint;
    private Paint mSrcPaint;

    private int mRingWidth;
    private int mRingColor;
    private boolean hasRing;

    public CircleImageView(Context context) {
        this(context, null);
    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        mDstPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mSrcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mRingWidth = DEF_RING_WIDTH;
        mRingColor = DEF_RING_COLOR;
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);


        if(mBitmap == null) {
            BitmapDrawable drawable = (BitmapDrawable) getDrawable();
            if (drawable == null) {
                return;
            }

            mDstBitmap = drawable.getBitmap();
        }else{
            mDstBitmap = mBitmap;
        }

        int dstWidth = mDstBitmap.getWidth();
        int dstHeight = mDstBitmap.getHeight();
        int radius = Math.min(dstHeight, dstWidth) / 2;


        drawSrcBitmap(dstWidth, dstHeight, radius);

        Matrix matrix = getImageMatrix();

//        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        canvas.drawBitmap(mDstBitmap, matrix, mDstPaint);
        mDstPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(mSRCBitmap, matrix, mDstPaint);

        mDstPaint.setXfermode(null);

        if(hasRing) {
            drawRingBitmap(dstWidth, dstHeight, radius);
            canvas.drawBitmap(mRingBitmap, matrix, mDstPaint);
        }

//        canvas.restoreToCount(layerId);
    }

    private void drawSrcBitmap(int dstWidth, int dstHeight, int radius) {
        if(mSRCBitmap == null){
            mSRCBitmap = Bitmap.createBitmap(dstWidth, dstHeight, Bitmap.Config.ARGB_8888);
            Canvas srcCanvas = new Canvas(mSRCBitmap);
            mSrcPaint.setColor(Color.RED);
            srcCanvas.drawCircle(dstWidth/2, dstHeight/2, radius - mRingWidth, mSrcPaint);
            srcCanvas = null;
        }
    }

    private void drawRingBitmap(int dstWidth, int dstHeight, int radius) {
        if(mRingBitmap == null) {
            mRingBitmap = Bitmap.createBitmap(dstWidth, dstHeight, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(mRingBitmap);
            mSrcPaint.setColor(mRingColor);
            mSrcPaint.setStyle(Paint.Style.STROKE);
            mSrcPaint.setStrokeWidth(mRingWidth);
            canvas.drawCircle(dstWidth/2, dstHeight/2, radius - mRingWidth, mSrcPaint);
            canvas = null;
        }
    }

    @Override
    public void setImageBitmap(Bitmap bitmap){
        mBitmap = bitmap;
    }

    @Override
    public void setImageDrawable(@Nullable Drawable drawable) {
        mBitmap = ((BitmapDrawable)drawable).getBitmap();
    }

    @Override
    public void setImageResource(@DrawableRes int resId) {
        mBitmap = BitmapFactory.decodeResource(getResources(), resId);
    }

    public void setRingWidth(int width){
        this.mRingWidth = width;
    }

    public void setRingColor(int color){
        this.mRingColor = color;
    }

    public void setHasRing(boolean hasRing) {
        this.hasRing = hasRing;
    }
}
