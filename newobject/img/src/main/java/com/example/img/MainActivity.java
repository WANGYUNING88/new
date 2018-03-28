package com.example.img;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.img);
        Button button = findViewById(R.id.img_assets);
        button.setOnClickListener(new MyListener(R.id.img_assets));

        Button button1 = findViewById(R.id.img_line);
        button1.setOnClickListener(new MyListener(R.id.img_line));

        Button  button2 = findViewById(R.id.img_path);
        button2.setOnClickListener(new MyListener(R.id.img_path));

        Button  button3 = findViewById(R.id.img_path2);
        button3.setOnClickListener(new MyListener(R.id.img_path2));
    }
    private class MyListener implements View.OnClickListener{

        private int id;
        MyListener(int id){
            this.id = id;
        }

        @Override
        public void onClick(View v) {
            switch (id){
                case R.id.img_assets:
                    loadAssetsImage();
                    break;
                case R.id.img_line:
                    drawLine();
                    break;
                case R.id.img_path:
                    drawPath();
                    break;
                case R.id.img_path2:
                    drawPathEFF();
                    break;
            }
        }
    }
    private void loadAssetsImage(){
        AssetManager assetManager = getAssets();
        try {
            InputStream inputStream = assetManager.open("wechat.png");
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
            imageView.setImageBitmap(bitmap);
        } catch (IOException e) {
            Log.e("error",e.toString());
            e.printStackTrace();
        }
    }
    private void drawLine(){
        Bitmap bitmap = Bitmap.createBitmap(400,
                400,
                Bitmap.Config.ARGB_8888);
        //画布
        Canvas canvas = new Canvas(bitmap);
        //画笔
        Paint paint = new Paint();
        //抗锯齿操作
        paint.setAntiAlias(true);
        //设置线宽
        paint.setStrokeWidth(50);
        //设置颜色
        paint.setColor(Color.parseColor("#00ff00"));
        //线帽 BUTT:无圆角
        paint.setStrokeCap(Paint.Cap.BUTT);
        //绘制直线
        canvas.drawLine(0,0,100,0,paint);

        Paint paint1 = new Paint();
        //抗锯齿操作
        paint1.setAntiAlias(true);
        //设置线宽
        paint1.setStrokeWidth(50);
        //设置颜色
        paint1.setColor(Color.parseColor("#ff0000"));
        //线帽 BUTT:无圆角
        paint1.setStrokeCap(Paint.Cap.ROUND);
        //绘制直线
        canvas.drawLine(0,100,100,100,paint1);

        Paint paint2 = new Paint();
        //抗锯齿操作
        paint2.setAntiAlias(true);
        //设置线宽
        paint2.setStrokeWidth(50);
        //设置颜色
        paint2.setColor(Color.parseColor("#0000ff"));
        //线帽 BUTT:无圆角
        paint2.setStrokeCap(Paint.Cap.SQUARE);
        //绘制直线
        canvas.drawLine(0,150,100,150,paint2);


        imageView.setImageBitmap(bitmap);
    }
    private void drawPath(){
        Bitmap bitmap = Bitmap.createBitmap(400,
                400,
                Bitmap.Config.ARGB_8888);
        //画布
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        //抗锯齿操作
        paint.setAntiAlias(true);
        //设置线宽
        paint.setStrokeWidth(30);
        //设置颜色
        paint.setColor(Color.parseColor("#00ff00"));
        //画笔的风格
        paint.setStyle(Paint.Style.STROKE);
        //笔帽
        paint.setStrokeJoin(Paint.Join.ROUND);
        //绘制折线
        Path path = new Path();

        path.moveTo(20,20);
        path.lineTo(120,120);
        path.lineTo(220,20);

        canvas.drawPath(path,paint);

        imageView.setImageBitmap(bitmap);
    }
    private void drawPathEFF(){
        Bitmap bitmap = Bitmap.createBitmap(400,
                400,
                Bitmap.Config.ARGB_8888);
        //画布
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        //抗锯齿操作
        paint.setAntiAlias(true);
        //设置线宽
        paint.setStrokeWidth(30);
        //设置颜色
        paint.setColor(Color.parseColor("#00ff00"));
        //画笔的风格
        paint.setStyle(Paint.Style.STROKE);
        paint.setPathEffect(new CornerPathEffect(10));
        canvas.translate(0, 0);
        //笔帽
        paint.setStrokeJoin(Paint.Join.ROUND);
        //绘制折线
       canvas.drawPath(getPath(),paint);
       imageView.setImageBitmap(bitmap);
    }
    private Path getPath(){
        Path path = new Path();
        path.moveTo(0,0);
        for (int i =0;i<= 50;i++){
            path.lineTo(20*i, (float) (Math.random()*50*i)%200);
        }
        return path;
    }
}
