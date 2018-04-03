package com.example.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //创建数据库
        Button button = findViewById(R.id.btn_1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                String path = "/data/data/"
                        +MainActivity.this.getPackageName()
                        +"/databases";
                File file  = new File(path);
                //判断文件是否存在
                if (!file.exists()){
                    file.mkdirs();
                }
                SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(
                        "/data/data/"
                                +MainActivity.this.getPackageName()
                                +"/databases/mysql.db" ,null);
            }
        });
        Button button1 = findViewById(R.id.btn_2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建sql
                String stu_table ="CREATE TABLE COMPANY("
                        + "ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + "NAME TEXT NOT NULL, AGE INT NOT NULL,"
                        + "ADDRESS CHAR(50),   SALARY REAL)";
                //执行sql
                SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(
                        "/data/data/"
                                +MainActivity.this.getPackageName()
                                +"/databases/mysql.db" ,null);
                db.execSQL(stu_table);
            }
        });
        Button button2 = findViewById(R.id.btn_3);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(
                        "/data/data/"
                                +MainActivity.this.getPackageName()
                                +"/databases/mysql.db" ,null);
                ContentValues cValue = new ContentValues();
                cValue.put("NAME", "XiaoMing");
                cValue.put("AGE", 19);
                cValue.put("ADDRESS", "Hebei Normal University");
                cValue.put("SALARY", 1500.0);
                db.insert("COMPANY", null, cValue);

            }
        });
        Button button3 = findViewById(R.id.btn_4);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(
                        "/data/data/"
                                +MainActivity.this.getPackageName()
                                +"/databases/mysql.db" ,null);
                // 删除条件
                String whereClause = "id=?";
                // 删除条件参数
                String[] whereArgs = {String.valueOf(2)};
                // 执行删除
                db.delete("COMPANY", whereClause, whereArgs);

            }
        });
        Button button4 = findViewById(R.id.btn_5);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(
                        "/data/data/"
                                +MainActivity.this.getPackageName()
                                +"/databases/mysql.db" ,null);
                ContentValues values = new ContentValues();
                values.put("SALARY", 2000.0);
                // 修改条件
                String whereClause = "ID=?";
                // 修改添加参数
                String[] whereArgs={String.valueOf(1)};
                db.update("COMPANY", values, whereClause, whereArgs);

            }
        });
        Button button5 = findViewById(R.id.btn_6);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(
                        "/data/data/"
                                +MainActivity.this.getPackageName()
                                +"/databases/mysql.db" ,null);
                Cursor cursor
                        = db.rawQuery("SELECT * FROM COMPANY", null);

               //<=> Cursor cursor = db.query ("COMPANY",null,null,null,null,null,null);
                // 判断游标是否为空
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
    }
}
