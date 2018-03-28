package net.onest.ch07_01_1imageresourcedemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private Button btnPaint;
    private Button btnPath;
    private Button btnPathEffect;
    private Button btnMulLine;
    private Button btnDraw;
    private ImageView ivImg;
    private int imgWidth;
    private int imgHeight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivImg = findViewById(R.id.iv_img);
        btnPaint = findViewById(R.id.btn_paint);
        btnPath = findViewById(R.id.btn_path);
        btnPathEffect = findViewById(R.id.btn_pathEffect);
        btnMulLine = findViewById(R.id.btn_mulLine);
        btnDraw = findViewById(R.id.btn_draw);
        OnClickListenerImpl listener = new OnClickListenerImpl();
        btnPaint.setOnClickListener(listener);
        btnPath.setOnClickListener(listener);
        btnPathEffect.setOnClickListener(listener);
        btnMulLine.setOnClickListener(listener);
        btnDraw.setOnClickListener(listener);
    }


    private class OnClickListenerImpl implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_paint:
                    drawStrokeCap();
                    break;
                case R.id.btn_path:
                    drawStrokePath();
                    break;
                case R.id.btn_pathEffect:
                    drawPathEffect();
                    break;
                case R.id.btn_mulLine:
                    drawMutilCornerPathEffect();
                    break;
                case R.id.btn_draw:
                    Intent intent = new Intent();
                    intent.setClass(getApplicationContext(), Main2Activity.class);
                    startActivity(intent);
                    break;
            }
        }
    }

    private void drawStrokeCap() {

        Bitmap bitmap = Bitmap.createBitmap(imgWidth, imgHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        Paint paint = new Paint();

        //Paint.setAntiAlias() 该方法作用是抗锯齿,尽量使有弧度的边平滑
        //Paint.setDither() 该方法是设置防抖动,尽量使颜色过渡柔和
        paint.setAntiAlias(true);
        //paint.setStrokeWidth() 该方法作用是设置线宽
        paint.setStrokeWidth(50);
        paint.setColor(Color.parseColor("#00ff00"));
        paint.setStrokeCap(Paint.Cap.BUTT);       // 线帽，即画的线条两端是否带有圆角，butt，无圆角
        canvas.drawLine(100, 100, 400, 100, paint);

        paint.setColor(Color.parseColor("#ff0000"));
        paint.setStrokeCap(Paint.Cap.ROUND);       // 线帽，即画的线条两端是否带有圆角，ROUND，圆角
        canvas.drawLine(100, 200, 400, 200, paint);

        paint.setColor(Color.parseColor("#0000ff"));
        paint.setStrokeCap(Paint.Cap.SQUARE);       // 线帽，即画的线条两端是否带有圆角，SQUARE，矩形
        canvas.drawLine(100, 300, 400, 300, paint);

        ivImg.setImageBitmap(bitmap);
    }

    private void drawStrokePath() {
        Bitmap bitmap = Bitmap.createBitmap(imgWidth, imgHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        Paint paint = new Paint();

        paint.setAntiAlias(true);
        paint.setStrokeWidth(20);
        paint.setStyle(Paint.Style.STROKE); // 默认是填充 Paint.Style.FILL
        paint.setColor(Color.parseColor("#0000ff"));

        Path path = new Path();
        path.moveTo(100, 100); // 路径path默认是在原点(0,0)，当前移植到(100,100)
        path.lineTo(400, 100);
        path.lineTo(200, 200);
        //多线条连接拐角弧度，StrokeJoin
        // enum Paint.join：设置结合处的形状，有三个选择：BEVEL、MITER、ROUND，分别表示直线、直角、圆角
        paint.setStrokeJoin(Paint.Join.BEVEL);
        canvas.drawPath(path, paint);

        paint.setStyle(Paint.Style.STROKE);
        path.moveTo(100, 300); // 路径path默认是在原点(0,0)，当前移植到(100,300)
        path.lineTo(500, 300);
        path.lineTo(100, 400);
        paint.setStrokeJoin(Paint.Join.ROUND);
        canvas.drawPath(path, paint);

        ivImg.setImageBitmap(bitmap);
    }

    private void drawPathEffect() {
        Bitmap bitmap = Bitmap.createBitmap(imgWidth, imgHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        Path path = new Path();
        path.moveTo(100, 600); // 路径path默认是在原点(0,0)，当前移植到(100,600)
        path.lineTo(400, 100);
        path.lineTo(500, 600);

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(3);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);

        // 50,圆角, 像素
        paint.setPathEffect(new CornerPathEffect(50));
        canvas.drawPath(path, paint);

        paint.setPathEffect(new CornerPathEffect(100));
        canvas.drawPath(path, paint);

        ivImg.setImageBitmap(bitmap);
    }

    //在onCreate或onResume回调方法中获取的高度和宽度均为0，因为那时控件还未绘制出来
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

//        imgHeight = ivImg.getMeasuredHeight();
//        imgWidth = ivImg.getMeasuredWidth();
        imgHeight = ivImg.getHeight();
        imgWidth = ivImg.getWidth();
        System.out.println("高度：" + imgHeight + "；" + "宽度：" + imgWidth);
        super.onWindowFocusChanged(hasFocus);
    }

    private void drawMutilCornerPathEffect() {

        Bitmap bitmap = Bitmap.createBitmap(imgWidth, imgHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        Paint paint = new Paint();
        paint.setStrokeWidth(3);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);

        Path path = getPath();
        canvas.drawPath(path, paint);
        // canvas.save(); // 保存上一次绘制(画布)，保证下一次绘制不受影响

        paint.setPathEffect(new CornerPathEffect(10));
        canvas.translate(0, 300);
        canvas.drawPath(path, paint);
        ivImg.setImageBitmap(bitmap);
    }

    private Path getPath() {
        Path path = new Path();
        path.moveTo(0, 0);
        for (int i = 1; i <= 50; i++) {
            path.lineTo(20 * i, (float) ((Math.random() * 50 * i) % 200));
        }
        return path;
    }


}
