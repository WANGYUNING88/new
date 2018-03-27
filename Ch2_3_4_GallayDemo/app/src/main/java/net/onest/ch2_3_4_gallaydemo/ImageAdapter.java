package net.onest.ch2_3_4_gallaydemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

/**
 * Created by lenovo on 2018-02-28.
 */

public class ImageAdapter extends BaseAdapter {
    //Item的修饰背景，可选
    int mGalleryItemBackground;

    //上下文对象
    private Context mContext;

    //图片数组
    private Integer[] mImageIds = {
            R.mipmap.fj1, R.mipmap.fj2,
            R.mipmap.fj3, R.mipmap.fj4,
            R.mipmap.fj5, R.mipmap.fj6,
            R.mipmap.fj7, R.mipmap.fj8,
            R.mipmap.fj9, R.mipmap.fj10
    };

    //构造方法
    public ImageAdapter(Context c) {
        mContext = c;
        //读取styleable资源 （可选）
        TypedArray a = mContext.obtainStyledAttributes(R.styleable.HelloGallery);
        mGalleryItemBackground = a.getResourceId(
                R.styleable.HelloGallery_android_galleryItemBackground, 0);
        a.recycle();

    }

    //返回项目数量
    @Override
    public int getCount() {
        return mImageIds.length;
    }

    //返回项目
    @Override
    public Object getItem(int position) {
        return position;
    }

    //返回项目Id
    @Override
    public long getItemId(int position) {
        return position;
    }

    //返回视图
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView iv = new ImageView(mContext);
        iv.setImageResource(mImageIds[position]);
        //给生成的ImageView设置Id，不设置的话Id都是-1
        iv.setId(mImageIds[position]);
        iv.setLayoutParams(new Gallery.LayoutParams(120, 160));
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        //设置背景样式,可选
        iv.setBackgroundResource(mGalleryItemBackground);
        return iv;
    }

}
