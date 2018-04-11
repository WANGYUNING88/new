package com.example.json;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editText = findViewById(R.id.et);
        Button btn1 = findViewById(R.id.btn_1);
        Button btn2 = findViewById(R.id.btn_2);
        Button btn3 = findViewById(R.id.btn_3);
        Button btn4 = findViewById(R.id.btn_4);
        // 生成对象JSON串
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("id", "0001");
                    jsonObject.put("name", "西门吹雪");
                    jsonObject.put("age", 22);
                    editText.setText(jsonObject.toString(1));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        // 解析对象JSON串
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = editText.getText().toString();
                try {
                    JSONObject jsonObject = new JSONObject(str);
                    Log.e("test", jsonObject.getString("id"));
                    Log.e("test", jsonObject.getString("name"));
                    Log.e("test", jsonObject.getString("age"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        // 生成数组JSON串
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] name = {"恶贯满盈", "无恶不作", "南海恶神", "穷凶极恶"};
                int[] age = {20, 19, 18, 17};
                String[] id = {"0001", "0002", "0003", "0004"};
                JSONArray jsonArray = new JSONArray();
                try {
                    for (int i = 0; i < 4; ++i) {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("id", id[i]);
                        jsonObject.put("name", name[i]);
                        jsonObject.put("age", age[i]);
                        jsonArray.put(jsonObject);
                    }
                    JSONObject object = new JSONObject();
                    object.put("students", jsonArray);
                    editText.setText(object.toString(1));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        // 解析数组JSON串
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String str = editText.getText().toString();
                    JSONObject jsonObject = new JSONObject(str);
                    JSONArray jsonArray
                            = jsonObject.getJSONArray("students");
                    for(int i=0; i<jsonArray.length(); ++i) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        Log.e("test", object.getString("id"));
                        Log.e("test", object.getString("name"));
                        Log.e("test", object.getString("age"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}