package net.onest.ch_2_3_2_customadapterdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.speech.tts.TextToSpeech;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

/**
 * Created by lenovo on 2018-02-27.
 */

public class CustomAdapter2 extends BaseAdapter {
    //上下文环境
    private Context context;
    //声明数据源
    private List<Map<String, Object>> dataSource;
    //声明列表项包含的项目
    private String[] content;
    //声明列表项的布局
    private int item_layout_id;
    //声明列表项中的控件
    private int[] ids;


    public CustomAdapter2() {
    }

    /**
     *  Adapter的构造方法
     * @param context 上下文环境
     * @param dataSource 数据源
     * @param content 数据源具体项目
     * @param item_layout_id 展示数据的数据项布局文件
     * @param ids 数据项布局文件中显示各种数据的控件
     */
    public CustomAdapter2(Context context, List<Map<String, Object>> dataSource, int item_layout_id, String[] content, int[] ids) {
        this.context = context;
        this.dataSource = dataSource;
        this.content = content;
        this.item_layout_id = item_layout_id;
        this.ids = ids;
    }

    /**
     * 很重要的一个方法，返回Adapter中包含多少个列表项
     * @return 返回Adapter中包含多少个列表项
     */
    @Override
    public int getCount() {
        return dataSource.size();
    }

    /**
     *
     * @param position Adapter中第几个列表项，从0开始
     * @return 返回position位置处的列表项的内容
     */
    @Override
    public Object getItem(int position) {
        return dataSource.get(position);
    }

    /**
     * 很重要的一个方法，返回position位置处的列表项的ID
     * @param position Adapter中第几个列表项，从0开始
     * @return 返回position位置处的列表项的ID
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     *
     * @param position
     * @param convertView
     * @param parent
     * @return 返回position位置处的列表项组件
     */
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHoder viewHoder = null;
        if(null == convertView){
            viewHoder = new ViewHoder();
            //加载列表项布局文件
            LayoutInflater mInflater = LayoutInflater.from(context);
            convertView = mInflater.inflate(item_layout_id, null);
            //获取控件对象
            viewHoder.img_header = convertView.findViewById(ids[0]);
            viewHoder.txt_name = convertView.findViewById(ids[1]);
            viewHoder.txt_desc = convertView.findViewById(ids[2]);
            convertView.setTag(viewHoder);
        } else {
            viewHoder = (ViewHoder)convertView.getTag();
        }
        //给数据项填充数据
        final Map<String, Object> mItemData = dataSource.get(position);
        /*id转化graphic.drawable
        Drawable drawable = getResources().getDrawable(R.drawable.icon);
        //示例代码
        Drawable header = context.getResources().getDrawable((Integer)mItemData.get(content[0]), null);
        viewHoder.img_header.setImageDrawable(header);

        id转化成Bitmap
        Bitmap bitmap = BitmapFactory. decodeResource (Resources   res, int id)
        //示例代码
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), (Integer)mItemData.get(content[0]));
        viewHoder.img_header.setImageBitmap(bitmap); */
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), (Integer)mItemData.get(content[0]));
        viewHoder.img_header.setImageBitmap(bitmap);
        viewHoder.txt_name.setText(mItemData.get(content[1]).toString());
        viewHoder.txt_desc.setText(mItemData.get(content[2]).toString());

        //给列表项中的控件注册事件监听器
        viewHoder.img_header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "点击了" + position + "项：" + mItemData.get("name"), Toast.LENGTH_SHORT).show();
            }
        });
        //返回列表项
        return convertView;
    }

    private static class ViewHoder{
        ImageView img_header;
        TextView txt_name;
        TextView txt_desc;
    }
}
