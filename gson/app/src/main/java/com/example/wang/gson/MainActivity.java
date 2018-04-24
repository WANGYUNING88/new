package com.example.wang.gson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.lang.reflect.Field;

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

        Student student = new Student("0001","李四");
        String j = myToJson(student);
        Log.e("student",j);

    }

    //实现toJson
    private String myToJson(Object object){
        Class cla = object.getClass();
        Field[] field = cla.getDeclaredFields();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("{");
        stringBuffer.append("\n");
        for (Field f : field){
            f.setAccessible(true);
            String fieldName = f.getName();
            try {
                Object fieldValue = f.get(object);
                stringBuffer.append("\""+fieldName+"\": "+fieldValue+" ,");
                stringBuffer.append("\n");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        stringBuffer.deleteCharAt(stringBuffer.length()-3);
        stringBuffer.deleteCharAt(stringBuffer.length()-2);
        stringBuffer.deleteCharAt(stringBuffer.length()-1);
        stringBuffer.append("\n");
        stringBuffer.append("}");
        return String.valueOf(stringBuffer);
    }
    //实现fromJson
    private Object myFromJson(String str , Class cl){
        return null;
    }
}