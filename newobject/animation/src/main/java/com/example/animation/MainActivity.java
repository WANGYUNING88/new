package com.example.animation;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button button = findViewById(R.id.animation);

        //通过java实现

        AnimationDrawable animationDrawable = new AnimationDrawable();
        for (int i = 0;i<7;i++){
            int id= getResources().getIdentifier("a"+i,
                    "drawable",
                    getPackageName());
            animationDrawable.addFrame(getResources().getDrawable(id),90);
        }
        animationDrawable.setOneShot(false);
        animationDrawable.start();
        ImageView imageView = findViewById(R.id.img);
        imageView.setImageDrawable(animationDrawable);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView imageView = findViewById(R.id.img);
                Animation animation = AnimationUtils.loadAnimation(
                        MainActivity.this,
                        R.anim.tweenanimation);
                imageView.startAnimation(animation);
            }
        });

        Button button1 = findViewById(R.id.tiaozhuan);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,newActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in_right,R.anim.anim_out_left);
            }
        });


        //通过布局文件实现
//        ImageView imageView = findViewById(R.id.img);
//        imageView.setImageResource(R.drawable.animationlist);
//        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getDrawable();
//        animationDrawable.start();
    }
}
