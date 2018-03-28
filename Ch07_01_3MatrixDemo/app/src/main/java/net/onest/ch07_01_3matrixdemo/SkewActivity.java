package net.onest.ch07_01_3matrixdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class SkewActivity extends AppCompatActivity {
    private ImageView ivOrigin, ivChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skew);

        ivOrigin = findViewById(R.id.iv_origin);
        ivChange = findViewById(R.id.iv_change);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.fj4);
        // 创建操作图片用的matrix对象
        Matrix matrix4 = new Matrix();
//                    matrix4.set(ivImg.getImageMatrix());
        //setSkew(float kx, float ky, float px, float py)
        // kx，ky分别代表了x，y上的错切因子，px，py代表了错切的中心
        matrix4.postSkew(0.1f, 0.1f);
        // 创建新的图片
        Bitmap resizedBitmap4 = Bitmap.createBitmap(bitmap, 0, 0,
                bitmap.getWidth(), bitmap.getHeight(), matrix4, true);
        // 设置ImageView的图片为上面转换的图片
        ivChange.setImageBitmap(resizedBitmap4);
    }
}
