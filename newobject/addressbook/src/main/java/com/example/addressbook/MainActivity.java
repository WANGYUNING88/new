package com.example.addressbook;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.AnimationDrawable;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Map<String,Object> mapTel;
    private Map<String,Object> mapEmail;
    private Map<String,Object> map ;
    private List<Map<String,Object>> telList;
    private List<Map<String,Object>> emailList;
    private ContactAdapter contactAdapter;
    private ViewFlipper allFlipper;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            switch (msg.what) {
                case 1:
                    //切换到主页面
                    allFlipper.setDisplayedChild(1);
                    break;
            }
        }
    };
    private  TextView textViewStart;
    private CountDownTimer timer = new CountDownTimer(10000, 1000) {


        @Override
        public void onTick(long millisUntilFinished) {
            textViewStart.setText("剩余"+(millisUntilFinished / 1000) + "s");
        }

        @Override
        public void onFinish() {
            textViewStart.setText("剩余0s");
        }
    };
    public void restart(View v) {
        timer.start();
    }

    private class ContactAdapter extends BaseAdapter {
        private Context context;    // 上下文环境
        private int item_layout_id;// item视图布局文件
        private List<Map<String, Object>> dataSource; // 数据

        public ContactAdapter(Context context,
                             int item_layout_id,
                             List<Map<String, Object>> dataSource) {
            this.context = context;
            this.item_layout_id = item_layout_id;
            this.dataSource = dataSource;
        }

        @Override
        public int getCount() {
            return dataSource.size();
        }

        @Override
        public Object getItem(int position) {
            return dataSource.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView,
                            ViewGroup parent) {
            if(null == convertView) {
                convertView = LayoutInflater.from(context)
                        .inflate(item_layout_id, null);
            }
            ImageView contact_img = convertView.findViewById(R.id.contact_img);
            TextView contact_name = convertView.findViewById(R.id.contact_name);

            Map<String, Object> itemData = dataSource.get(position);
            contact_img.setImageResource((Integer) itemData.get("contact_img"));
//            contact_delete.setImageResource((Integer) itemData.get("contact_delete"));
            contact_name.setText(itemData.get("contact_name").toString());
            return convertView;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //计时
        textViewStart = (TextView)findViewById(R.id.textView_start);
        textViewStart.getBackground().setAlpha(100);
        restart(textViewStart);

        //设置点击事件
        textViewStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allFlipper = (ViewFlipper) findViewById(R.id.allFlipper);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        handler.sendEmptyMessage(1); //给UI主线程发送消息
                    }
                },0);
            }
        });

        //设置动画
        ImageView imageView = findViewById(R.id.imageView_start);
        imageView.setImageResource(R.drawable.startlist);
        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getDrawable();
        animationDrawable.start();

        //开线程延迟跳转
        allFlipper = (ViewFlipper) findViewById(R.id.allFlipper);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                handler.sendEmptyMessage(1); //给UI主线程发送消息
            }
        },10000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getData(); //给UI主线程发送消息
            }
        },2000);
        //生成数据

    }
    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            getData();
        }
    }
    public void getData(){
        final List<Map<String, Object>> dataSource = new ArrayList<>();
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,null,
                null,null,null);
        if (cursor.moveToFirst()){
            do {
                map = new HashMap<>();
                map.put("contact_img",R.drawable.contact);
                map.put("contact_delete",R.drawable.delete);
                //通讯录名字
                int indexName = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
                String contact_name = cursor.getString(indexName);
                map.put("contact_name",contact_name);
                //id
                int indexId = cursor.getColumnIndex(ContactsContract.Contacts._ID);

                int contact_id = cursor.getInt(indexId);

                map.put("contact_id",contact_id);
//                根据id获得tel
                Cursor cursor1 = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID
                                +"="+contact_id,null,
                        null);
                telList = new ArrayList<>();
                if (cursor1.moveToFirst()){
                    do {
                        mapTel = new HashMap<>();
                        int id = cursor1.getInt(cursor1.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone._ID));
                        mapTel.put("id",id);
                        String tel = cursor1.getString(cursor1.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER));
                        mapTel.put("tel",tel);
                        telList.add(mapTel);
                    }while (cursor1.moveToNext());
                }
                map.put("contact_tel",telList);
                //根据id获得email
                Cursor cursor2 = contentResolver.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                        null,ContactsContract.CommonDataKinds.Email.CONTACT_ID
                                +"="+contact_id,null,
                        null);
                emailList = new ArrayList<>();
                if (cursor2.moveToFirst()){

                    do {
                        mapEmail = new HashMap<>();
                        int id = cursor2.getInt(cursor2.getColumnIndex(
                                ContactsContract.CommonDataKinds.Email._ID));
                        mapEmail.put("id",id);
                        String email = cursor2.getString(cursor2.getColumnIndex(
                                ContactsContract.CommonDataKinds.Email.DATA));
                        mapEmail.put("email",email);
                        emailList.add(mapEmail);
                    }while (cursor2.moveToNext());
                }
                map.put("contact_email",emailList);
                dataSource.add(map);
            }while (cursor.moveToNext());
        }

        ListView listView = findViewById(R.id.lv_contact);
        contactAdapter = new ContactAdapter(this,
                R.layout.layout_contact_item, dataSource);
        listView.setAdapter(contactAdapter);

        //设置监听事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,
                        ContactActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("contact_map", (Serializable) dataSource.get(position));
                startActivityForResult(intent, 0);
            }
        });
    }
}
