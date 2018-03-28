package net.onest.ch07_01_2drawshapedemo;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private Button btnCircle, btnRect, btnEclipse;
    private ImageView ivImg;
    //定义画布大小
    private int width;
    private int height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCircle = findViewById(R.id.btn_circle);
        btnRect = findViewById(R.id.btn_rect);
        btnEclipse = findViewById(R.id.btn_eclipse);
        ivImg = findViewById(R.id.iv_img);
        OnClickListenerImpl listener = new OnClickListenerImpl();
        btnEclipse.setOnClickListener(listener);
        btnRect.setOnClickListener(listener);
        btnCircle.setOnClickListener(listener);
    }

    private class OnClickListenerImpl implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint();
            Path path = new Path();
            paint.setStrokeWidth(5);
            switch (v.getId()) {
                case R.id.btn_circle://画圆
                    paint.setColor(Color.BLUE);
                    canvas.drawCircle(400, 400, 150, paint);
                    canvas.drawPath(path,paint);

                    ivImg.setImageBitmap(bitmap);
                    break;
                case R.id.btn_rect://画矩形
                    paint.setColor(Color.GREEN);
                    paint.setStyle(Paint.Style.STROKE);
                    canvas.drawRect(100, 100, 600, 600, paint);
                    ivImg.setImageBitmap(bitmap);
                    break;
                case R.id.btn_eclipse://画椭圆
                    paint.setColor(Color.BLACK);
                    /*
                    Paint.Style.STROKE 只绘制图形轮廓（描边）
                    Paint.Style.FILL 只绘制图形内容
                    Paint.Style.FILL_AND_STROKE 既绘制轮廓也绘制内容
                     */
                    paint.setStyle(Paint.Style.STROKE);
                    //Path.Direction只有两个常量值CCW和CW分别表示逆时针方向闭合和顺时针方向闭合
                    //CCW:文字在形状在内部，CW：文字在形状外部；起点位置均是0°位置
                    path.addOval(50, 350, 600, 600, Path.Direction.CCW);
                    canvas.drawPath(path, paint);
                    paint.setTextSize(50);
                    // 绘制路径上的文字
                    canvas.drawTextOnPath("123456789", path, 0, 0, paint);
                    ivImg.setImageBitmap(bitmap);
                    break;
            }
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        width = ivImg.getWidth();
        height = ivImg.getHeight();
        super.onWindowFocusChanged(hasFocus);
    }
}
