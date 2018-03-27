package net.onest.ch_2_3_2_customadapterdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2018-02-27.
 */

public class CustomAdapter extends BaseAdapter {
    //上下文环境
    private Context context;
    //声明数据源
    private List<Map<String, Object>> dataSource;
    //声明列表项的布局
    private int item_layout_id;
    //声明列表项中的控件

    public CustomAdapter() {
    }

    /**
     *  Adapter的构造方法
     * @param context 上下文环境
     * @param dataSource 数据源
     * @param item_layout_id 展示数据的数据项布局文件
     */
    public CustomAdapter(Context context, List<Map<String, Object>> dataSource, int item_layout_id) {
        this.context = context;
        this.dataSource = dataSource;
        this.item_layout_id = item_layout_id;
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
        //加载列表项布局文件
        if(null == convertView){
            LayoutInflater mInflater = LayoutInflater.from(context);
            convertView = mInflater.inflate(item_layout_id, null);
        }

        //获取控件对象
        ImageView img_header = convertView.findViewById(R.id.img_header);
        TextView txt_name = convertView.findViewById(R.id.txt_name);
        TextView txt_desc = convertView.findViewById(R.id.txt_desc);

        //给数据项填充数据
        final Map<String, Object> mItemData = dataSource.get(position);
        img_header.setImageResource((int)mItemData.get("header"));
        txt_name.setText(mItemData.get("name").toString());
        txt_desc.setText(mItemData.get("desc").toString());

        //给列表项中的控件注册事件监听器
        img_header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "点击了" + position + "项：" + mItemData.get("name"), Toast.LENGTH_SHORT).show();
            }
        });
        //返回列表项
        return convertView;
    }
}
