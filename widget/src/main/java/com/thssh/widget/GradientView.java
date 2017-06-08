package com.thssh.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * <h1>Shader</h1>
 * <p><strong>着色器</strong></p>
 * <p>Canvasde drawXXX这个方法是画具体的形状，画笔的shader定义的就是图形的着色和外观</p>
 *
 * <p>BitmapShader 位图图像渲染</p>
 * <p>TileMode 拉伸方式</p>
 *
 *
 * <p>LinearGradient 线性渲染</p>
 * <p>SweepGradient 渐变渲染/梯度渲染</p>
 * <p>RadialGradient 环形渲染</p>
 * <p></p>
 * <p></p>
 *
 * Created by zhangyugehu on 2017/5/18.
 */

public class GradientView extends View {

    private Bitmap mBitmap;
    private Paint mPaint;

    private int[] mColors;
    private float[] mPositions;

    private int mWidth, mHeight;

    public GradientView(Context context) {
        super(context);
    }

    public GradientView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        BitmapDrawable drawable = (BitmapDrawable) getResources().getDrawable(R.drawable.xyjy);
        mBitmap = drawable.getBitmap();
        mPaint = new Paint();
        mHeight = mBitmap.getHeight();
        mWidth = mBitmap.getWidth();
        mColors = new int[]{Color.RED, Color.GREEN, Color.BLUE};
        mPositions = new float[]{0.2f, 0.5f, 0.3f};
    }

    public GradientView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.WHITE);

        BitmapShader bitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.MIRROR);
//        mPaint.setShader(bitmapShader);
//        mPaint.setAntiAlias(true);


        // 画圆
//        canvas.drawCircle(mHeight/2, mHeight/2, mHeight/2, mPaint);

//        // 缩放比
//        float scale = Math.max(mWidth, mHeight) / Math.min(mWidth, mHeight);
//        Matrix matrix = new Matrix();
//        matrix.setScale(scale, scale);
//        bitmapShader.setLocalMatrix(matrix);
//        // 画椭圆
//        canvas.drawOval(new RectF(0, 0, 1000, 1800), mPaint);


        ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
        shapeDrawable.getPaint().setShader(bitmapShader);
        shapeDrawable.setBounds(0, 0, mHeight, mHeight);
        shapeDrawable.draw(canvas);


//        LinearGradient linearGradient = new LinearGradient(0, 0, 400, 400, mColors, null, Shader.TileMode.MIRROR);
//        mPaint.setShader(linearGradient);
//        canvas.drawRect(0, 0, 400, 400, mPaint);


//        SweepGradient sweepGradient = new SweepGradient(300, 300, mColors, null);
//        mPaint.setShader(sweepGradient);
//        canvas.drawCircle(300, 300, 300, mPaint);


    }
}
