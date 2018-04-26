package com.example.wang.okhttp;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private File imgfile;
    private static final int RESULT_PERMISSION = 1;
    private static final int RESULT_CODE = 1;
    //    private static RequestBody requestBody = RequestBody.create(
//            MediaType.parse("text/plain;charset=utf-8"),
//            "{\"username\":\"wang\", \"userpassword\":\"123456\"}");
    private static String BASE_URL = "http://10.0.2.2:8080/myservice/";
    private static String TAG = "OKHTTP";
    private OkHttpClient okHttpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e("sdk", String.valueOf(Build.VERSION.SDK_INT));

        okHttpClient = new OkHttpClient();

        Button button = findViewById(R.id.btn_get_sync);
        button.setOnClickListener(this);

        Button button1 = findViewById(R.id.btn_get_async);
        button1.setOnClickListener(this);

        Button button2 = findViewById(R.id.btn_upload);
        button2.setOnClickListener(this);

    }

    private void doUploadFile(File file){
        RequestBody requestBody = RequestBody.create(
                MediaType.parse("image/*"),file);

        Request.Builder builder = new Request.Builder();
        Request request = builder
                .post(requestBody)
                .url(BASE_URL + "UploadImgServlet")
                .build();


    }

    private void doGetAsync(){
        //创建请求对象
        final Request request = new Request.Builder()
//                .url("https://www.baidu.com/")
//                .post(requestBody)
                .url(BASE_URL+"LoginServlet?name=wang&password=123456")
                .build();
        //创建call对象并执行,获得响应
        final Call call = okHttpClient.newCall(request);
        //
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e(TAG,response.body().string());
            }
        });
    }

    private void doGetSync(){
        //创建请求对象
        final Request request = new Request.Builder()
//                .url("https://www.baidu.com/")
//                .post(requestBody)
                .url(BASE_URL+"LoginServlet?name=wang&password=123456")
                .build();
        //创建call对象并执行,获得响应
        final Call call = okHttpClient.newCall(request);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = call.execute();
                    if (response.isSuccessful()){
                        Log.e(TAG,response.body().string());
                    }else{
                        Log.e(TAG, String.valueOf(response.code()));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    //重写onclick方法
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_get_sync:
                doGetSync();
                break;
            case R.id.btn_get_async:
                doGetAsync();
                break;
            case R.id.btn_upload:
                //动态
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        RESULT_PERMISSION);
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == RESULT_PERMISSION){
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent,RESULT_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RESULT_CODE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();

            imgfile = new File(selectedImage.toString());
            String picturePath = imgfile.getAbsolutePath();
            doUploadFile(imgfile);
            Log.e("TAG", imgfile.getAbsolutePath());
        }else{

            Log.e("TAG", "图片不正常");
        }
    }
}
