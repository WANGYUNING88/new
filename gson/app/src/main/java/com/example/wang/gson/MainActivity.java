package com.example.wang.gson;

import android.support.v4.widget.ListViewAutoScrollHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;

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

        //嵌套类型
        Person p = new Person("wang",user);
        String json = gson.toJson(p);
        Log.e("preson",json);

        // POJO Deserialization
         String jsonString ="{\"name\":\"lily\",\"age\":24,\"sex\":\"男\"}";
         User user1 = gson.fromJson(jsonString, User.class);

        Log.e("name", user1.getName());
        Log.e("age", String.valueOf(user1.getAge()));
        Log.e("sex", user1.getSex());

        Student student = new Student("0001","李四");
        String j = myToJson(student);
        Log.e("student",j);

//        String stuJson = "{\"studentNum\":\"002\",\"studentName\":\"王五\"}";
        Student student1 = (Student) myFromJson(j,Student.class);
        Log.e("stunum",student1.getStudentNum());
        Log.e("stuname",student1.getStudentName());

        //数组的序列化和反序列化
        int[] ints = {1, 2, 3, 4, 5};
        String[] strings = {"abc", "def", "ghi"};
                // Serialization
        gson.toJson(ints);     // ==> [1,2,3,4,5]
        gson.toJson(strings);  // ==> ["abc", "def", "ghi"]

        // Deserialization
        int[] ints2 = gson.fromJson("[1,2,3,4,5]", int[].class);
        // ==> ints2 will be same as ints


        //集合的序列化和反序列化
//        Collection<Integer> ints1 =
//                Lists.immutableList(1,2,3,4,5);

        // Serialization
        String json1 = gson.toJson(ints);  // ==> json is [1,2,3,4,5]

        // Deserialization
        Type collectionType = new TypeToken<Collection<Integer>>(){}.getType();
        Collection<Integer> integer = gson.fromJson(json1, collectionType);


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
    private Object myFromJson(String str , Class cla){
        Object object = null;
        try {
            //利用反射调用构造器
            Constructor[] cons = cla.getDeclaredConstructors();
            object = cons[0].newInstance();

            //得到Json串中key的名字
            JSONObject jsonObject = new JSONObject(str);
            Iterator<String> keys = jsonObject.keys();

            //得到对象中所有属性的名字
            Field[] fields = cla.getDeclaredFields();

            while (keys.hasNext()){
                String fieldName = keys.next();
                for (Field f : fields){
                    f.setAccessible(true);
                    if (f.getName().equals(fieldName)){
                        f.set(object, jsonObject.get(fieldName));
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return object;
    }
}
