package net.onest.ch2_3_7_fragmenttabhostdemo;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {

    private FragmentTabHost myTabhost;

    //标签项图片
    private int mImages[] = {
            R.mipmap.call, R.mipmap.message, R.mipmap.mail
    };
    //标签项数据
    private String mFragmentTags[] = {
            "电话", "短信", "邮件"
    };
    //加载Fragment页面
    private Class mFragment[] = {
            FragmentTab1.class, FragmentTab2.class, FragmentTab3.class
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //以下一行语句的作用是让手机屏幕保持一种不暗不关闭的效果。通常的应用场景是视频播放器。
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化FragmentTabHost，并创建选项卡
        initTabHost();
        //注册事件监听器
        myTabhost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                Toast.makeText(getApplicationContext(), tabId, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initTabHost() {
        myTabhost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        myTabhost.setup(this, getSupportFragmentManager(), android.R.id.tabhost);
        //去掉分割线
        myTabhost.getTabWidget().setDividerDrawable(null);
        for (int i = 0; i < mFragmentTags.length; i++) {
            //对Tab按钮添加标记和图片
            TabHost.TabSpec tabSpec = myTabhost.newTabSpec(mFragmentTags[i]).setIndicator(getTextView(i));
            //添加Fragment
            myTabhost.addTab(tabSpec, mFragment[i], null);
            //设置选项卡的背景
//            myTabhost.getTabWidget().getChildAt(i).setBackgroundResource(R.color.gray);
        }
        //设置默认选中的选项卡
        myTabhost.setCurrentTab(0);
        //获取当前是第几个选项卡,从0开始
//        myTabhost.getCurrentTab()
    }
    //创建标签项
    private View getTextView(int index){
        View view = getLayoutInflater().inflate(R.layout.fragment_tab, null);
        TextView textView = view.findViewById(R.id.txt_tab_pic);
        textView.setText(mFragmentTags[index]);
        textView.setCompoundDrawablesWithIntrinsicBounds(null, this.getResources().getDrawable(mImages[index], null), null, null);
        return view;
    }

}
