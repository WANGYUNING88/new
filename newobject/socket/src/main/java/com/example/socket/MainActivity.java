package com.example.socket;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.btn_img_1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Log.e("按钮","被点击");
                    ImageView imageView = findViewById(R.id.img_1);
                    String url =
                           "http://g.hiphotos.baidu.com/image/h%3D300/sign=5a0f156f57b5c9ea7df305e3e538b622/cf1b9d16fdfaaf519d4aa2db805494eef01f7a2c.jpg";
                    LoadImageAsyncTask l = new LoadImageAsyncTask(url,imageView);
                    WebView webView = findViewById(R.id.wv);
                    webView.loadUrl(url);

            }
        });
        Button button1 = findViewById(R.id.btn_2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CityThread cityThread = new CityThread();
            }
        });
    }

    private class LoadImageAsyncTask extends AsyncTask{

        private String aUrl;
        private Bitmap aBitmap = null;
        private ImageView aImageView;

        LoadImageAsyncTask(String url,ImageView imageView){
            aUrl = url;
            aImageView = imageView;
        }

        //子线程执行耗时任务
        @Override
        protected Object doInBackground(Object[] objects) {
            try {

                URL url = new URL(aUrl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                //
                httpURLConnection.setRequestMethod("GET");
                //连接时间
                httpURLConnection.setConnectTimeout(8000);
                //传输时间
                httpURLConnection.setReadTimeout(8000);
                //获取服务器输入流
                InputStream inputStream = httpURLConnection.getInputStream();
                //输入流解码
                aBitmap = BitmapFactory.decodeStream(inputStream);
                //关闭输入流
                inputStream.close();
                //关闭连接
                httpURLConnection.disconnect();
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        //执行完
        @Override
        protected void onPostExecute(Object o) {
            if (aBitmap != null) {
                aImageView.setImageBitmap(aBitmap);
            }
        }
    }
}
