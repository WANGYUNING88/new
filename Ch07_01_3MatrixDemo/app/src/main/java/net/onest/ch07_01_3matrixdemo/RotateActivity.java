package net.onest.ch07_01_3matrixdemo;

import android.content.SyncStatusObserver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RotateActivity extends AppCompatActivity {
    private ImageView ivOrigin, ivChange;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotate);

        tv = findViewById(R.id.tv);
        ivOrigin = findViewById(R.id.iv_origin);
        ivChange = findViewById(R.id.iv_change);
/*        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.fj4);
        // 创建操作图片用的matrix对象
        Matrix matrix2 = new Matrix();
        //旋转图片 动作
        matrix2.setRotate(-45);
        // 创建新的图片
        // bitmap是旋转之前的bitmap，(0,0)是开始截取点的坐标
        // (bitmap.getWidth(), bitmap.getHeight())是截止截取点的坐标
        // 这两个点是从旧bitmap截取内容，因为要整个旋转，所以是旧bitmap的所有内容
        Bitmap resizedBitmap2 = Bitmap.createBitmap(bitmap, 0, 0,
                bitmap.getWidth(), bitmap.getHeight(), matrix2, true);
        // 设置ImageView的图片为上面转换的图片
        ivChange.setImageBitmap(resizedBitmap2);*/

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        ivChange.setLayoutParams(params);
        ivChange.setImageResource(R.mipmap.fj4);
        ivChange.setScaleType(ImageView.ScaleType.MATRIX);
        // 创建操作图片用的matrix对象
        Matrix matrix2 = new Matrix();
        //旋转图片 动作
        matrix2.setRotate(-45);
        System.out.println(ivOrigin.getHeight());
        matrix2.postTranslate(200, 200);
        ivChange.setImageMatrix(matrix2);
    }
}
