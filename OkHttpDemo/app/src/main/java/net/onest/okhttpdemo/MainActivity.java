package net.onest.okhttpdemo;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener{

    private static final int REQUSET_PERMISSION = 1;
    private static final int REQUEST_CODE = 2;
    private String BASE_URL = "http://192.168.188.2:8080/OkhttpDemo/";
    private static final String TAG = "OKHTTP";
    private OkHttpClient okHttpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnGetSync = findViewById(R.id.btn_getsync);
        btnGetSync.setOnClickListener(this);
        Button btnGetAsync = findViewById(R.id.btn_getasync);
        btnGetAsync.setOnClickListener(this);
        Button btnPostString = findViewById(R.id.btn_poststring);
        btnPostString.setOnClickListener(this);
        Button btnPostForm = findViewById(R.id.btn_postform);
        btnPostForm.setOnClickListener(this);
        Button btnUploadFile = findViewById(R.id.btn_postfile);
        btnUploadFile.setOnClickListener(this);
        Button btnDownload = findViewById(R.id.btn_downloadfile);
        btnDownload.setOnClickListener(this);

        //创建OkHttpClient对象
        okHttpClient = new OkHttpClient();
    }

    /**
     * 同步的get请求
     */
    private void doGetSync(){

        //创建请求对象
        Request request = new Request.Builder()
//                .url("http://www.baidu.com")
                .url(BASE_URL + "Login?username=zhangsan&password=123")
                .build();

        //创建Call对象，并执行请求，获得响应
        final Call call = okHttpClient.newCall(request);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = call.execute();
                    if (response.isSuccessful()){
                        Log.e(TAG, response.body().string());
                    }else {
                        Log.e(TAG, response.code()+"");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 异步get请求
     */
    private void doGetAsyn(){
        //创建请求对象
        Request request = new Request.Builder()
//                .url("http://www.baidu.com")
                .url(BASE_URL + "Login?username=zhangsan&password=123")
                .build();

        //创建Call对象，并执行请求，获得响应
        final Call call = okHttpClient.newCall(request);
        //执行异步请求
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

    /**
     * 普通文本post请求
     */
    private void doPostString(){

        Gson gson = new Gson();

        User user = new User();
        user.setUsername("zhangsan");
        user.setPassword("123");
        String userStr = gson.toJson(user);

        //创建请求体对象
        RequestBody requestBody = RequestBody.create(
                MediaType.parse("text/plain;charset=utf-8"),
                userStr);

        //创建请求对象
        Request request = new Request.Builder()
                .post(requestBody)
                .url(BASE_URL + "SendMessage")
                .build();
        //创建Call对象，并执行请求
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e(TAG, response.body().string());
            }
        });
    }

    /**
     * 表单数据post请求
     */
    private void doPostForm(){

        //创建FromBody对象
        RequestBody requestBody = new FormBody.Builder()
                .add("username","zhangsan")
                .add("password","123")
                .build();
        //创建请求对象
        Request request = new Request.Builder()
                .post(requestBody)
                .url(BASE_URL + "Login")
                .build();
        //创建Call对象，并执行请求
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e(TAG, response.body().string());
            }
        });
    }

    /**
     * 上传文件
     * @param file
     */
    private void doUploadFile(File file){
        //构建请求体
        RequestBody requestBody = RequestBody.create(
                MediaType.parse("image/*"),file);
        Request request = new Request.Builder()
                .url(BASE_URL + "UploadFile")
                .post(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e(TAG, response.body().string());
            }
        });
        }

    private void doDomnloadFile(){

        Request request = new Request.Builder()
                .url(BASE_URL + "test.txt")
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is = response.body().byteStream();
                File file = new File(Environment.getExternalStorageDirectory()+"/a.txt");
                FileOutputStream fos = new FileOutputStream(file);
                int len ;
                byte[] buffer = new byte[1024];
                while ((len = is.read(buffer)) != -1){
                    fos.write(buffer);
                }

                is.close();
                fos.close();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_getsync:
//                doGetSync();
                Intent intent = new Intent(MainActivity.this,
                        BookListActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_getasync:
                doGetAsyn();
                break;
            case R.id.btn_poststring:
                doPostString();
                break;
            case R.id.btn_postform:
                doPostForm();
                break;
            case R.id.btn_postfile:
                //打开手机相册，动态申请权限
                ActivityCompat.requestPermissions(
                        this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUSET_PERMISSION);
                break;
            case R.id.btn_downloadfile:
                doDomnloadFile();
                break;
        }
    }

    //相册界面返回之后的回调方法
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE){
            //获取照片
            Uri uri =  data.getData();
            Cursor cursor = getContentResolver().query(uri,null,null,
                    null,null);
            cursor.moveToFirst();
            String column = MediaStore.Images.Media.DATA;
            int columnIndex = cursor.getColumnIndex(column);
            String path = cursor.getString(columnIndex);
            File file = new File(path);
            doUploadFile(file);
        }
    }

    //申请动态权限的回调方法
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //打开手机相册
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_CODE);
    }
}
