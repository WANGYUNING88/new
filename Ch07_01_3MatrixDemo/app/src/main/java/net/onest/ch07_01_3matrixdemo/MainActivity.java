package net.onest.ch07_01_3matrixdemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private Button btn1, btn2, btn3, btn4;
   /* private ImageView ivImg;
    //定义画布大小
    private int width;
    private int height;
    Bitmap bitmap;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.btn_translate);
        btn2 = findViewById(R.id.btn_rotate);
        btn3 = findViewById(R.id.btn_scale);
        btn4 = findViewById(R.id.btn_skew);
//        ivImg = findViewById(R.id.iv_img);
        OnClickListenerImpl listener = new OnClickListenerImpl();
        btn1.setOnClickListener(listener);
        btn2.setOnClickListener(listener);
        btn3.setOnClickListener(listener);
        btn4.setOnClickListener(listener);
    }

    private class OnClickListenerImpl implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            switch (v.getId()) {
                case R.id.btn_translate://平移
                    intent.setClass(getApplicationContext(), TranslateActivity.class);
                    break;
                case R.id.btn_rotate://旋转
                    intent.setClass(getApplicationContext(), RotateActivity.class);
                    break;
                case R.id.btn_scale://缩放
                    intent.setClass(getApplicationContext(), ScaleActivity.class);
                    break;
                case R.id.btn_skew://错切
                    intent.setClass(getApplicationContext(), SkewActivity.class);
                    break;
            }
            startActivity(intent);
        }
    }

   /* @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.fj4);

        //获取这个图片的宽和高
        width = bitmap.getWidth();
        height = bitmap.getHeight();
        super.onWindowFocusChanged(hasFocus);
    }*/
}
