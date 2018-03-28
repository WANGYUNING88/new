package net.onest.ch07_01_3matrixdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class TranslateActivity extends AppCompatActivity {
    private ImageView ivOrigin, ivChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);

        ivOrigin = findViewById(R.id.iv_origin);
        ivChange = findViewById(R.id.iv_change);

        Matrix matrix1 = new Matrix();
        //平移图片 动作
        matrix1.postTranslate(700, ivOrigin.getHeight()+20);
        ivChange.setImageMatrix(matrix1);
    }
}
