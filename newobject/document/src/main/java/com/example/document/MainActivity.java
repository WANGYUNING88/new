package com.example.document;

import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences preferences;
    private File file;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //本程序的读写;
        Button button = findViewById(R.id.btn_w);
        Button button1 = findViewById(R.id.btn_d);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //写
                preferences = getSharedPreferences("name",MODE_PRIVATE);
                SharedPreferences.Editor ed = preferences.edit();
                ed.putString("one","55555");
                ed.commit();
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //读
                preferences = getSharedPreferences("name",MODE_PRIVATE);
                String value = preferences.getString("one","000");
                Log.e("one",value);
            }
        });

        Button button2 = findViewById(R.id.btn_filew);
        Button button3 = findViewById(R.id.btn_filer);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Environment.getExternalStorageState().equals(android
                        .os.Environment.MEDIA_MOUNTED)) {
                     file
                            = new File(Environment.getExternalStorageDirectory(),
                            "abc.txt");
                    try {
                        FileInputStream inputStream
                                = new FileInputStream(file);
                        InputStreamReader inputStreamReader
                                = new InputStreamReader(inputStream);
                        BufferedReader bufferedReader
                                = new BufferedReader(inputStreamReader);
                        String strMsg = bufferedReader.readLine();
                        while(strMsg != null) {
                            Log.e("sdmsg", strMsg);
                            strMsg = bufferedReader.readLine();
                        }
                        Log.e("sdmsg", "读取完成");
                        inputStreamReader.close();

                    } catch (FileNotFoundException e) {
                        Log.e("error", e.toString());
                        e.printStackTrace();
                    } catch (IOException e) {
                        Log.e("error", e.toString());
                        e.printStackTrace();
                    }

                }
            }

        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // true 追加模式 false 覆盖模式
                try {
                    FileOutputStream outputStream
                            = new FileOutputStream(file, true);
                    String strMsg = "abcdefg\n1234567\n";
                    outputStream.write(strMsg.getBytes());
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
