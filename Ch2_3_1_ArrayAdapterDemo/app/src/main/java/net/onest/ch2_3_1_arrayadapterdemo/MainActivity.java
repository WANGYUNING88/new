package net.onest.ch2_3_1_arrayadapterdemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    // 声明控件
    private ListView lv_subjects;
    private ListView lv_roles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取控件
        lv_subjects = findViewById(R.id.lv_subjects);
        lv_roles = findViewById(R.id.lv_roles);

        //第一个AdapterView展示课程信息,已经在XML布局文件中添加了数据源

        //第二个AdapterView展示演员信息
        //定义演员数组
        final String[] roles = {"赵丽颖", "张翰", "孙红雷"};
        //定义Adapter
        final ArrayAdapter<String> rolesAdapter = new ArrayAdapter<String>(
                this, //上下文环境
                android.R.layout.simple_list_item_1, //内置布局视图，也可以是自定义的布局
                roles   //数据源
        );

        //给AdapterView（lv_roles)设置Adapter
        lv_roles.setAdapter(rolesAdapter);

        //为ListView的每一项绑定选择事件监听器
        lv_roles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //parent：该项目父适配器的引用
                //view：当前项目视图控件的引用
                //position: 当前项目在ListView中的位置序号，序号从0开始
                //id：当前项目在ListView中的行号
                Log.i("position", position+"");
                Log.i("item",rolesAdapter.getItem(position));
            }
        });
    }
}
