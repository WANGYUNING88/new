package net.onest.ch2_3_4_gallaydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //定义UI组件
        final ImageView iv= findViewById(R.id.ImageView01);
        Gallery g = findViewById(R.id.Gallery01);

        //设置图片匹配器
        g.setAdapter(new ImageAdapter(this));

        //设置AdapterView点击监听器，Gallery是AdapterView的子类
        g.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //显示点击的是第几张图片
                Toast.makeText(getApplicationContext(), "" + position,
                        Toast.LENGTH_SHORT).show();
                //设置背景部分的ImageView显示当前Item的图片
                iv.setImageResource((int)view.getId());
            }
        });
    }
}
