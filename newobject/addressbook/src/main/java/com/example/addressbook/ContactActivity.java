package com.example.addressbook;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wang on 2018/4/4.
 */

public class ContactActivity extends Activity {
    private HashMap<String,Object> contactMap;

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String name = data.getStringExtra("contact_name");
            TextView textView = findViewById(R.id.contact_name);
            textView.setText(name);
            contactMap.put("contact_name",name);
            List<Map<String,Object>> telList = (List<Map<String,Object>>) data.getSerializableExtra("telListUpdate");
            List<Map<String,Object>> emailList = (List<Map<String,Object>>) data.getSerializableExtra("emailListUpdate");
            contactMap.put("contact_tel",telList);
            contactMap.put("contact_email",emailList);
            LinearLayout linearLayoutMsg = findViewById(R.id.detail_msg);

            linearLayoutMsg.removeAllViews();

            if (telList != null) {
                for (Map<String,Object> telMap : telList) {
                    View telView = View.inflate(linearLayoutMsg.getContext(), R.layout.layout_tel_item, null);
                    TextView textView1 = telView.findViewById(R.id.contact_tel);
                    textView1.setText(telMap.get("tel").toString());
                    linearLayoutMsg.addView(telView);
                }
            }
            if (emailList != null) {
                for (Map<String,Object> emailMap : emailList) {
                    View emailView = View.inflate(linearLayoutMsg.getContext(), R.layout.layout_email_item, null);
                    TextView textView2 = emailView.findViewById(R.id.contact_email);
                    textView2.setText(emailMap.get("email").toString());
                    linearLayoutMsg.addView(emailView);
                }
            }
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_details);
        Intent intent = getIntent();
        int position = intent.getIntExtra("position",
                0);
        contactMap =
                (HashMap<String, Object>) intent.getSerializableExtra("contact_map");
        TextView textView = findViewById(R.id.contact_name);
        textView.setText((String)contactMap.get("contact_name"));
        List<Map<String,Object>> telList = (List<Map<String,Object>>) contactMap.get("contact_tel");
        List<Map<String,Object>> emailList = (List<Map<String,Object>>) contactMap.get("contact_email");
        LinearLayout linearLayoutMsg = findViewById(R.id.detail_msg);
        if (telList != null) {
            for (Map<String,Object> telMap : telList) {
                View telView = View.inflate(linearLayoutMsg.getContext(), R.layout.layout_tel_item, null);
                TextView textView1 = telView.findViewById(R.id.contact_tel);
                textView1.setText(telMap.get("tel").toString());
                linearLayoutMsg.addView(telView);
            }
        }
        if (emailList != null) {
            for (Map<String,Object> emailMap : emailList) {
                View emailView = View.inflate(linearLayoutMsg.getContext(), R.layout.layout_email_item, null);
                TextView textView2 = emailView.findViewById(R.id.contact_email);
                textView2.setText(emailMap.get("email").toString());
                linearLayoutMsg.addView(emailView);
            }
        }
        Button button =  findViewById(R.id.detail_update);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ContactActivity.this,UpdateActivity.class);
                intent.putExtra("contact_map",contactMap);
                startActivityForResult(intent,0);
//                startActivity(intent);
            }
        });
        Button button1 =findViewById(R.id.detail_delete);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = (int) contactMap.get("contact_id");
                Log.e("contact_id", String.valueOf(id));
                ContentResolver contentResolver = getContentResolver();
                int rs =  contentResolver.delete(ContactsContract.Contacts.CONTENT_URI,
                        ContactsContract.Contacts._ID+"=?",
                        new String[]{id+""});
                contentResolver.delete(ContactsContract.RawContacts.CONTENT_URI,
                        ContactsContract.RawContacts.CONTACT_ID + " =?",
                        new String[]{id+""});
                Intent intent1 = new Intent();
                setResult(RESULT_OK,intent1);
                finish();

            }
        });
    }

}

