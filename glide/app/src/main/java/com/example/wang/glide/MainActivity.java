package com.example.wang.glide;

import android.Manifest;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_READ = 1;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.img_1);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_READ){
            Log.e("quanxian;","chenggong");
        }
    }

    public void getImage(View view){
        switch (view.getId()){
            case R.id.btn_bendi:

                //动态申请权限
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_READ);

                String imgPath =Environment.getExternalStorageDirectory().toString()+"/timg.jpg";
                Log.e("imgPath:",imgPath);

                File file = new File(imgPath);
                Glide.with(this).load(file).into(imageView);
                break;
            case R.id.btn_yuancheng:
                String imgPathInternet
                        = "https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality="
                        +"100&size=b4000_4000&sec=1524188942&di=1b6a64edafff0bf303c1a94f962b0d52&"
                        +"src=http://img4.duitang.com/uploads/item/201602/24/20160224020611_fVLWx.jpeg";

                String imageUrl  = "https://www.baidu.com/statics/images/nougat_bg.png";
                //使用请求选项设置占位符
                RequestOptions requestOptions = new RequestOptions()
                        .placeholder(R.drawable.loading)
                        .error(R.drawable.error)
                        .fallback(R.drawable.defaultimg)
                        .centerCrop();
//使用apply()方法应用占位

                Glide.with(this).
                        load(imgPathInternet).
                        apply(requestOptions).
                        into(imageView);
                break;
            case R.id.btn_ziyuan:
                Glide.with(this).load(R.drawable.qq).into(imageView);
                break;
        }
    }

}
