package com.example.wang.jpush;

import android.app.Application;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by wang on 2018/4/27.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

    }
}
