package net.onest.ch07_01_1imageresourcedemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by lenovo on 2018-03-25.
 */

public class HandWritingImageView extends AppCompatImageView {

    private Paint mPaint;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Path mPath;
    private float mX, mY;
    private Paint mBitmapPaint = new Paint();
    private static final float TOUCH_TOLERANCE = 4;


    public HandWritingImageView(Context context, Paint mPaint) {
        super(context);
        mBitmap = Bitmap.createBitmap(1080, 1584, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        mPath = new Path();
        this.mPaint = mPaint;
    }
    public void setPaint(Paint mPaint){
        this.mPaint = mPaint;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
        canvas.drawPath(mPath, mPaint);

    }

    private void touch_start(float x, float y) {
        mPath.reset();
        mPath.moveTo(x, y);
        mX = x;
        mY = y;
    }

    private void touch_move(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            mX = x;
            mY = y;
        }
    }

    private void touch_up() {
        mPath.lineTo(mX, mY);
        mCanvas.drawPath(mPath, mPaint);
        mPath.reset();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touch_start(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                touch_move(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                touch_up();
                invalidate();
                break;
        }
        return true;

    }
}
