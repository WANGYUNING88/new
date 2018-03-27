package net.onest.ch_2_3_2_customadapterdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
//    private ListView lv_persons;
    private GridView lv_persons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main_grid);

        //获取ListView控件
        lv_persons = findViewById(R.id.lv_persons);

        //准备数据源
        List<Map<String, Object>> dataSource = new ArrayList<>();
        Map<String, Object> itemData1 = new HashMap<>();
        //定义第1个数据项
        itemData1.put("header", R.mipmap.header_flower);
        itemData1.put("name", "校花");
        itemData1.put("desc", "女神太累");
        dataSource.add(itemData1);
        //第2个数据项
        Map<String, Object> itemData2 = new HashMap<>();
        itemData2.put("header", R.mipmap.header_wz1);
        itemData2.put("name", "屌丝");
        itemData2.put("desc", "屌丝也疯狂");
        dataSource.add(itemData2);
        //第3个数据项
        Map<String, Object> itemData3 = new HashMap<>();
        itemData3.put("header", R.mipmap.header_beautyfatgirl);
        itemData3.put("name", "气质女");
        itemData3.put("desc", "内在的美才是真的美");
        dataSource.add(itemData3);

        //创建Adapter适配器对象
        CustomAdapter customAdapter = new CustomAdapter(
                this, //上下文环境
                dataSource, //数据源
                R.layout.item_main //列表项布局文件
        );
        //给ListView控件设置适配器
        lv_persons.setAdapter(customAdapter);

        /*//创建Adapter适配器对象
        CustomAdapter2 customAdapter2 = new CustomAdapter2(this, dataSource, R.layout.item_main,
                new String[]{"header", "name", "desc"}, new int[]{R.id.img_header, R.id.txt_name, R.id.txt_desc});

        //给ListView控件设置适配器
        lv_persons.setAdapter(customAdapter2);*/

        //给ListView的列表项注册点击事件监听器
        lv_persons.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), parent.getAdapter().getItem(position).toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
