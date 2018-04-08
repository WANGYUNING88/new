package com.example.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private BindService.MyBinder myBinder;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        //连接成功时
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder = (BindService.MyBinder) service;
            Log.e("MainActivity:","BindService连接成功");
        }
        //连接断开时
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.btn_Service1);
        //启动 一般服务
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MyIntentService.class);
                startService(intent);
            }
        });
        //关闭一般服务
        Button button1 = findViewById(R.id.btn_Service2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,FirstService.class);
                stopService(intent);
            }
        });

        final Intent intent = new Intent(MainActivity.this,BindService.class);
        //绑定bindService;
        Button button2 = findViewById(R.id.btn_bindService1);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindService(intent,serviceConnection,BIND_AUTO_CREATE);

            }
        });
        //解绑bindService;
        Button button3 = findViewById(R.id.btn_bindService2);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(serviceConnection);
            }
        });
        //关闭bindService;
        Button button4 = findViewById(R.id.btn_bindService3);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("MainActivity:", String.valueOf(myBinder.getRunTime()));
            }
        });
    }
}
