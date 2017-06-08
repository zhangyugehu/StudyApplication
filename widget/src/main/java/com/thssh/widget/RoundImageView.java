package com.thssh.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhangyugehu on 2017/5/18.
 */

public class RoundImageView extends View {

    private Paint mBitmapPaint;
    private Bitmap mBitmapSrc, mBitmapDst;

    public RoundImageView(Context context) {
        super(context);
    }

    public RoundImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public RoundImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mBitmapPaint = new Paint();
        mBitmapSrc = BitmapFactory.decodeResource(getResources(), R.drawable.xyjy);
        mBitmapDst = BitmapFactory.decodeResource(getResources(), R.drawable.shade);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(mBitmapDst, 0, 0, mBitmapPaint);
        mBitmapPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(mBitmapSrc, 0, 0, mBitmapPaint);

        mBitmapPaint.setXfermode(null);
    }
}
