package com.example.myapplication;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.btn_1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //查询一条
                ContentResolver contentResolver = getContentResolver() ;
                Uri uri = Uri.parse("content://com.example.sqlite//company/2");
                Cursor cursor = contentResolver.query(uri,null,
                        null,null,null);
                if(cursor.moveToFirst()) {
                    // 遍历游标
                    do {
                        int id = cursor.getInt(cursor.getColumnIndex("ID"));
                        String name=cursor.getString(cursor.getColumnIndex("NAME"));
                        int age=cursor.getInt(cursor.getColumnIndex("AGE"));
                        String address = cursor.getString(cursor.getColumnIndex("ADDRESS"));
                        String salary = cursor.getString(cursor.getColumnIndex("SALARY"));
                        Log.e("mydb", id + "|"+name+"|"+age+"|"
                                +address+"|"+salary);
                    } while (cursor.moveToNext());
                }
            }
        });

        Button button1 = findViewById(R.id.btn_2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //查询全部
                ContentResolver contentResolver = getContentResolver() ;
                Uri uri = Uri.parse("content://com.example.sqlite//companys");
                Cursor cursor = contentResolver.query(uri,null,
                        null,null,null);
                if(cursor.moveToFirst()) {
                    // 遍历游标
                    do {
                        int id = cursor.getInt(cursor.getColumnIndex("ID"));
                        String name=cursor.getString(cursor.getColumnIndex("NAME"));
                        int age=cursor.getInt(cursor.getColumnIndex("AGE"));
                        String address = cursor.getString(cursor.getColumnIndex("ADDRESS"));
                        String salary = cursor.getString(cursor.getColumnIndex("SALARY"));
                        Log.e("mydb", id + "|"+name+"|"+age+"|"
                                +address+"|"+salary);
                    } while (cursor.moveToNext());
                }
            }
        });
        Button button2 = findViewById(R.id.btn_3);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentResolver contentResolver = getContentResolver() ;
                Uri uri = Uri.parse("content://com.example.sqlite//companys");
                ContentValues cValue = new ContentValues();
                cValue.put("NAME", "DaMing");
                cValue.put("AGE", 22);
                cValue.put("ADDRESS", "Hebei Normal University");
                cValue.put("SALARY", 3200.0);
                Uri uri1  = contentResolver.insert(uri,cValue);

            }
        });
    }
}
