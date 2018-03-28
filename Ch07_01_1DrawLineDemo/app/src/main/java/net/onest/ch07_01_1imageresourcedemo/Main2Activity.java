package net.onest.ch07_01_1imageresourcedemo;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Main2Activity extends AppCompatActivity {
    private Paint  mPaint;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPaint=new Paint();
        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.STROKE);
        setContentView(new HandWritingImageView(this, mPaint));
    }
}
