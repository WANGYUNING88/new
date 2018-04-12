package com.example.a04_12;

import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer = new MediaPlayer();
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.btn_text);
        textView.setText("音乐停止...");
        initMediaPlayer();
        onClick();
        getSwitch();

    }

    private void initMediaPlayer() {

        //获取mp3文件的路径
        File file = new File(Environment.getExternalStorageDirectory(),"lucky.mp3");
        if (file != null) {
            Log.e("music",file.getPath());
        }else {
            Log.e("music","找不到");
        }
        try {
            mediaPlayer.setDataSource(file.getPath()); //为播放器设置mp3文件的路径
            mediaPlayer.prepare(); //做好准备
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onClick(){
         Button button = findViewById(R.id.btn_start);
         button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if(!mediaPlayer.isPlaying())
                 {
                     mediaPlayer.start();
                     textView.setText("音乐播放...");

                 }
             }
         });
    }
    public  void getSwitch(){
        ToggleButton toggleButton = findViewById(R.id.btn_switch);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mediaPlayer.isPlaying()) {
                    if (isChecked) {
                        textView.setText("音乐暂停...");
                        mediaPlayer.pause();
                    } else {
                        textView.setText("音乐播放...");
                        mediaPlayer.start();
                    }
                }else {
                    textView.setText("音乐未播放...");
                }
            }
        });
    }
    //注销
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayer != null)
        {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

}
