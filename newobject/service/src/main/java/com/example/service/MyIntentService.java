package com.example.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by wang on 2018/4/8.
 */

public class MyIntentService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MyIntentService(String name) {
        super(name);
    }
    public MyIntentService(){
        super("name");

    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.e("MainActivity;","开始");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.e("MainActivity:","结束");
    }

}
