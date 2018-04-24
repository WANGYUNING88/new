package com.example.wang.gson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Serialization
        //Gson gson = new Gson();
        Gson gson = new GsonBuilder()
                .serializeNulls()
                .setPrettyPrinting()
                .setDateFormat("yyyy-MM-dd")
                .create();
        gson.toJson(1);            // ==> 1
        gson.toJson("abcd");       // ==> "abcd"
        gson.toJson(new Long(10)); // ==> 10
        int[] values = {1};
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


        // POJO Deserialization
         String jsonString ="{\"name\":\"lily\",\"age\":24,\"sex\":\"男\"}";
         User user1 = gson.fromJson(jsonString, User.class);

        Log.e("name", user1.getName());
        Log.e("age", String.valueOf(user1.getAge()));
        Log.e("sex", user1.getSex());


    }
    private String myToGson(Object object){
        return null;
    }
    private Object myFromDson(String str , Class cl){
        return null;
    }
}
