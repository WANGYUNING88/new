package com.example.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int mHiddenHeigh = 800;
    private TextView textView;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text);
        textView.setVisibility(View.GONE);
        imageView = findViewById(R.id.arrow);
        imageView.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             if (textView.getVisibility() == View.GONE) {
                                                 textView.setVisibility(View.VISIBLE);
                                                 imageView.setImageResource(R.drawable.image_arrow_up);
                                                 ValueAnimator animator =
                                                         createDropAnimator(0,mHiddenHeigh,textView);
                                                 animator.start();
                                             } else {
                                                 imageView.setImageResource(R.drawable.image_arrow_down);
                                                 ValueAnimator animator = createDropAnimator(mHiddenHeigh,0,textView);
                                                 animator.addListener(new AnimatorListenerAdapter() {
                                                     @Override
                                                     public void onAnimationEnd(Animator animation) {
                                                         textView.setVisibility(View.GONE);
                                                     }
                                                 });
                                                 animator.start();
                                             }
                                         }
                                     });


//
//        Button button = findViewById(R.id.animation);
//
//        //通过java实现
//
//        AnimationDrawable animationDrawable = new AnimationDrawable();
//        for (int i = 0;i<7;i++){
//            int id= getResources().getIdentifier("a"+i,
//                    "drawable",
//                    getPackageName());
//            animationDrawable.addFrame(getResources().getDrawable(id),90);
//        }
//        animationDrawable.setOneShot(false);
//        animationDrawable.start();
//        ImageView imageView = findViewById(R.id.img);
//        imageView.setImageDrawable(animationDrawable);
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ImageView imageView = findViewById(R.id.img);
//                Animation animation = AnimationUtils.loadAnimation(
//                        MainActivity.this,
//                        R.anim.tweenanimation);
//                imageView.startAnimation(animation);
//            }
//        });
//
//        Button button1 = findViewById(R.id.tiaozhuan);
//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this,newActivity.class);
//                startActivity(intent);
//                overridePendingTransition(R.anim.anim_in_right,R.anim.anim_out_left);
//            }
//        });


        //通过布局文件实现
//        ImageView imageView = findViewById(R.id.img);
//        imageView.setImageResource(R.drawable.animationlist);
//        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getDrawable();
//        animationDrawable.start();
    }
    private ValueAnimator createDropAnimator(int start,int end,
                                             final View view){
        ValueAnimator animator = ValueAnimator.ofInt(start,end);
        animator.setDuration(1000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams
                        = view.getLayoutParams();
                layoutParams.height = value;
                view.setLayoutParams(layoutParams);
            }
        });
        return animator;
    }
}
