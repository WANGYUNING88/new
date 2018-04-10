package com.example.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by wang on 2018/4/10.
 */

public class MyService extends Service {

    private Boolean quit = false;
    private int runTime;
    private MyBinder myBinder = new MyBinder();

    public class MyBinder extends Binder{
        public int getRunTime(){
            return runTime;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    @Override
    public void onCreate() {
        Log.e("BindService:","Service被创建");
        super.onCreate();
        new Thread(){
            @Override
            public void run() {
                while (!quit){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runTime++;
                }
            }
        }.start();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("BindService:","Service被绑定");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        quit = true;
        Log.e("BindService:","Service被销毁");
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("BindService:","Service被解绑");
        return super.onUnbind(intent);
    }

}
