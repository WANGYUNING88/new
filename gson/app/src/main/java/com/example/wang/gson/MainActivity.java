package com.example.wang.gson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Serialization
        Gson gson = new Gson();
        gson.toJson(1);            // ==> 1
        gson.toJson("abcd");       // ==> "abcd"
         gson.toJson(new Long(10)); // ==> 10
         int[] values = { 1 };
         gson.toJson(values);       // ==> [1]
        // Deserialization
        int num1 = gson.fromJson("1", int.class);
        Integer num2 = gson.fromJson("1", Integer.class);
        Long num3 = gson.fromJson("1", Long.class);
        Boolean bool = gson.fromJson("false", Boolean.class);
        String str = gson.fromJson("\"abc\"", String.class);
        String[] str1 = gson.fromJson("[\"abc\"]",
                String[].class);

        // Serialization
        User user = new User("张三", 21, "男");
        String jsonObj = gson.toJson(user);
        Log.e("gson", jsonObj);
        // json is ：{"name":"张三","age":20,"sex":"男"}


    }
}
