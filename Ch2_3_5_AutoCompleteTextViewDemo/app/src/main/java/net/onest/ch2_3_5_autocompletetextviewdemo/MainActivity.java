package net.onest.ch2_3_5_autocompletetextviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取控件对象
        AutoCompleteTextView auto1 = findViewById(R.id.auto1);
        MultiAutoCompleteTextView auto2 = findViewById(R.id.auto2);

        //定义数据源
        String[] books = {"JavaSE", "JavaEE", "JavaME"};

        //创建Adapter对象
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, books);

        //设置适配器
        auto1.setAdapter(adapter);
        auto2.setAdapter(adapter);

        //为MultiAutoCompleteTextView设置分隔符(可选）
        auto2.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    }
}
