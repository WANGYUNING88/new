package net.onest.ch2_3_8_fragmentdemo;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button tb_1;
    private Button tb_2;
    private Fragment fragment1;
    private Fragment fragment2;
    private FragmentManager fragmentManager;
    private Fragment currentFragment = new Fragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        bindListeners();
//        //提交事务，调用commit方法提交。
//        fragmentTransaction.commit();

        //获取到FragmentManager，在V4包中通过getSupportFragmentManager，
        //在系统中原生的Fragment是通过getFragmentManager获得的。
        fragmentManager = getFragmentManager();
        //把自己创建好的fragment创建一个对象，作为一个标签项对应的页面
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();

        showFragment(fragment1);
    }

    private void findViews() {
        //用来切换不同fragment
        tb_1 = findViewById(R.id.btn1);
        tb_2 = findViewById(R.id.btn2);
    }

    private void bindListeners() {
        ButtonClickListener listener = new ButtonClickListener();
        tb_1.setOnClickListener(listener);
        tb_2.setOnClickListener(listener);
    }

    class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn1:
                    showFragment(fragment1);
                    break;
                case R.id.btn2:
                    showFragment(fragment2);
                    break;
            }
        }
    }

    /**
     * 展示Fragment,主要完成Fragment的隐藏和展示，也就是完成Fragment的切换功能。
     */
    private void showFragment(Fragment fragment) {
        if (currentFragment != fragment) {
            //开启一个事务，通过调用beginTransaction方法开启。
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.hide(currentFragment);
            currentFragment = fragment;
            if (!fragment.isAdded()) {
                //向容器内加入Fragment，一般使用add或者replace方法实现，需要传入容器的id和Fragment的实例。
                transaction.add(R.id.page_main, fragment).show(fragment).commit();
            } else {
                //提交事务，调用commit方法提交。
                transaction.show(fragment).commit();
            }
        }
    }

}
