package com.thssh.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.mtp.MtpConstants;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by zhangyugehu on 2017/5/18.
 */

public class LinearGradientTextView extends android.support.v7.widget.AppCompatTextView {
    private static final String TAG = "LinearGradientTextView";
    private static float DELTAX = 20;
    private TextPaint mPaint;
    private LinearGradient mLinearGradient;
    private Matrix mMatrix;
    private float mTranslate;

    public LinearGradientTextView(Context context) {
        super(context);
    }

    public LinearGradientTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LinearGradientTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        mMatrix = new Matrix();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mPaint = getPaint();

        String text = getText().toString();
        float textWidth = mPaint.measureText(text);
        int gradientSize = (int) (textWidth / text.length() * 3);

        mLinearGradient = new LinearGradient(-gradientSize, 0, gradientSize, 0,
                new int[]{0x22ffffff, 0xffffffff, 0x22ffffff},
                new float[]{0, 0.5f, 1},
                Shader.TileMode.CLAMP);
        mPaint.setShader(mLinearGradient);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mTranslate += DELTAX;
        float textWidth = getPaint().measureText(getText().toString());
        if(mTranslate > textWidth + 1 || mTranslate < 1){
            DELTAX = -DELTAX;
        }
        Log.d(TAG, "onDraw: mTranslate > " + mTranslate);
        // 在onDraw中不断改变Paint
        mMatrix.setTranslate(mTranslate, 0);

        mLinearGradient.setLocalMatrix(mMatrix);

        postInvalidateDelayed(50);

    }
}
