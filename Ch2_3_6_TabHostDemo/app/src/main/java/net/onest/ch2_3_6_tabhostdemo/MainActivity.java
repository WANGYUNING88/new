package net.onest.ch2_3_6_tabhostdemo;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取TabHost控件
        TabHost tabHost = findViewById(android.R.id.tabhost);

        //初始化TabHost容器
        tabHost.setup();
        //在TabHost创建标签，然后设置：标题／图标／标签页布局
        //创建第1个Tab页面
        TabHost.TabSpec tab1 = tabHost.newTabSpec("tab1")
                .setIndicator("短信")//设置选项卡名称称
                .setContent(R.id.tab1);//设置页面视图组件，可以是简单的View，也可以是Activity或Fragment
        //添加第1个Tab页面
        tabHost.addTab(tab1); // 添加选项卡

        //第2个页面
        tabHost.addTab(tabHost.newTabSpec("tab2")
                .setIndicator("电话")
                .setContent(R.id.tab2)
        );
        //第3个页面
        tabHost.addTab(tabHost.newTabSpec("tab3")
                .setIndicator("邮件")
                .setContent(R.id.tab3)
        );
/*
        //创建第1个Tab页面
        TabHost.TabSpec tab1 = tabHost.newTabSpec("tab1")
                .setIndicator(getTabView("短信", getResources().getDrawable(R.mipmap.message)))
                .setContent(R.id.tab1);
        //添加第1个Tab页面
        tabHost.addTab(tab1);

        //第2个页面
        tabHost.addTab(tabHost.newTabSpec("tab2")
                .setIndicator(getTabView("电话", getResources().getDrawable(R.mipmap.call)))
                .setContent(R.id.tab2)
        );
        //第3个页面
        tabHost.addTab(tabHost.newTabSpec("tab3")
                .setIndicator(getTabView("邮件", getResources().getDrawable(R.mipmap.mail)))
                .setContent(R.id.tab3)
        );*/
    }
    //自定义标签页的结构内容
    private View getTabView(String name, Drawable drawable){
        TextView textView = (TextView)LayoutInflater.from(this).inflate(R.layout.tab_view, null);
        textView.setText(name);
        //setCompoundDrawables(Drawable left,Drawable top,Drawable right,Drawable bottom)
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());//位置顺序：left、top、right、bottom
        textView.setCompoundDrawables(null, drawable, null, null);//设置图片显示在控件的哪个位置
        textView.setGravity(Gravity.CENTER); //设置控件内的内容垂直居中（图片和文字垂直居中对齐）
        return textView;
    }

}
