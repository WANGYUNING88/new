package net.onest.ch07_01_3matrixdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ScaleActivity extends AppCompatActivity {
    private ImageView ivOrigin, ivChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scale);

        ivOrigin = findViewById(R.id.iv_origin);
        ivChange = findViewById(R.id.iv_change);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.fj4);
        // 创建操作图片用的matrix对象
        Matrix matrix3 = new Matrix();
        // 缩放图片动作，放大为原来的1.5倍
        matrix3.postScale(1.5f, 1.5f);
        // 创建新的图片
        Bitmap resizedBitmap3 = Bitmap.createBitmap(bitmap, 0, 0,
                bitmap.getWidth(), bitmap.getHeight(), matrix3, true);
        // 设置ImageView的图片为上面转换的图片
        ivChange.setImageBitmap(resizedBitmap3);
    }

}
