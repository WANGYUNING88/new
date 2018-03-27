package net.onest.ch2_3_3_spinnerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取Spinner控件
        Spinner spinner = findViewById(R.id.sp_arrays);
        Spinner sp_books = findViewById(R.id.sp_books);
        final TextView txt_book = findViewById(R.id.txt_book);
        final TextView txt_film = findViewById(R.id.txt_film);
        sp_books.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                txt_book.setText(parent.getAdapter().getItem(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //创建数据源
        String[] arrays = {"何以笙箫默", "寻梦环游记", "唐人街探案2"};

        //创建适配器对象
        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_selectable_list_item,
                arrays
        );

        //给Spinner控件设置适配器
        spinner.setAdapter(mAdapter);
        //给Spinner控件注册事件监听器
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                txt_film.setText(parent.getAdapter().getItem(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
