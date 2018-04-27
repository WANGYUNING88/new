package net.onest.jpushdemo;

import android.app.Application;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by zyl on 2018/4/27.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化SDK
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }
}
