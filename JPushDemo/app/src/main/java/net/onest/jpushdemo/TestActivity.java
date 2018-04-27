package net.onest.jpushdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import cn.jpush.android.api.JPushInterface;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Bundle bundle = getIntent().getExtras();
        //获得自定义消息标题
        String messageTitle = bundle.getString(JPushInterface.EXTRA_TITLE);
        //获得自定义消息的内容
        String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
        TextView textView = findViewById(R.id.tv_message);
        textView.setText("标题：" + messageTitle + "\n内容：" + message);
    }
}
