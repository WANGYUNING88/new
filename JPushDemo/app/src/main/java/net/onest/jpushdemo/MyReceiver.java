package net.onest.jpushdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by zyl on 2018/4/27.
 */

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle bundle = intent.getExtras();

        //判断接收到自定义消息
        if (intent.getAction().equals(JPushInterface.ACTION_MESSAGE_RECEIVED)){
            //打开一个界面并且把自定义消息传递过去
            Intent intent1 = new Intent(context, TestActivity.class);
            intent1.putExtras(bundle);
            context.startActivity(intent1);
        }
    }
}
