package net.onest.ch07_01_1imageresourcedemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by lenovo on 2018-03-23.
 */

public class CustomeView extends View {
    private List<Point> allPoints = new ArrayList<>();

    public CustomeView(Context context) {
        super(context);
    }

    //接受context以及属性集合(宽度，高度等)
    public CustomeView(Context context, @Nullable AttributeSet set) {
        super(context, set);
        super.setOnTouchListener(new OnTouchListenerImp());
    }

    public CustomeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private class OnTouchListenerImp implements OnTouchListener {

        public boolean onTouch(View v, MotionEvent event) {
            Point p = new Point((int) event.getX(), (int) event.getY());
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                //用户按下，表示重新开始保存点
                CustomeView.this.allPoints = new ArrayList<Point>();
                CustomeView.this.allPoints.add(p);
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                //用户松开
                CustomeView.this.allPoints.add(p);
                CustomeView.this.postInvalidate();//重绘图像
            } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                CustomeView.this.allPoints.add(p);
                CustomeView.this.postInvalidate();//重绘图像
            }
            return true;
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {

        Paint p=new Paint();//依靠此类开始画线
        p.setColor(Color.RED);
        if(CustomeView.this.allPoints.size()>1){
            //如果有坐标点，开始绘图
            Iterator<Point> iter=CustomeView.this.allPoints.iterator();
            Point first=null;
            Point last=null;
            while(iter.hasNext()){
                if(first==null){
                    first=(Point)iter.next();
                }else{
                    if(last!=null){
                        first=last;
                    }
                    last=(Point)iter.next();//结束
                    canvas.drawLine(first.x, first.y, last.x, last.y, p);
                }
            }
        }
    }
}
