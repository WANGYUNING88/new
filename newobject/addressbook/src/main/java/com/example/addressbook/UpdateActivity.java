package com.example.addressbook;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wang on 2018/4/6.
 */

public class UpdateActivity extends Activity {

    private Map<String,Object> mapTel;
    private Map<String,Object> mapEmail;
    private List<Map<String,Object>> telListUpdate;
    private List<Map<String,Object>> emailListUpdate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_update);
        Intent intent = getIntent();
        int position = intent.getIntExtra("position",
                0);
        HashMap<String,Object> contactMap =
                (HashMap<String, Object>) intent.getSerializableExtra("contact_map");
        final TextView textView = findViewById(R.id.contact_name);
        textView.setText((String)contactMap.get("contact_name"));
        List<Map<String,Object>> telList = (List<Map<String,Object>>) contactMap.get("contact_tel");
        final List<Map<String,Object>> emailList = (List<Map<String,Object>>) contactMap.get("contact_email");
        final LinearLayout linearLayoutTel = findViewById(R.id.detail_tel);
        if (telList != null) {

            for (Map<String,Object> telMap : telList) {
                View telView = View.inflate(this, R.layout.layout_update_tel, null);
//                telView.setId(id);
//                id++;
                EditText editText = telView.findViewById(R.id.contact_tel);
                editText.setText(telMap.get("tel").toString());
                TextView textView1 = telView.findViewById(R.id.contact_id);
                textView1.setText(String.valueOf(telMap.get("id")));
                linearLayoutTel.addView(telView);
            }
        }
        final LinearLayout linearLayoutEamil = findViewById(R.id.detail_email);
        if (emailList != null) {
            for (Map<String,Object> emailMap : emailList) {
                View emailView = View.inflate(this, R.layout.layout_update_email, null);

                EditText editText = emailView.findViewById(R.id.contact_email);
                editText.setText(emailMap.get("email").toString());
                TextView textView1 = emailView.findViewById(R.id.contact_id);
                textView1.setText(String.valueOf(emailMap.get("id")));
                linearLayoutEamil.addView(emailView);
            }
        }
        Button button_update =  findViewById(R.id.detail_update);
        button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setClass(ContactActivity.this,UpdataActivity.class);
////              startActivityForResult(intent, 0);
//                startActivity(intent);
                telListUpdate = new ArrayList<>();
               for (int i = 0; i < linearLayoutTel.getChildCount(); i++){
                   mapTel = new HashMap<>();
                   View view  = linearLayoutTel.getChildAt(i);
                   EditText editText = view.findViewById(R.id.contact_tel);
                   String tel = String.valueOf(editText.getText());
                   mapTel.put("tel",tel);
                   TextView textView1 = view.findViewById(R.id.contact_id);
                   int id = Integer.parseInt(String.valueOf(textView1.getText()));
                   mapTel.put("id",id);
                   telListUpdate.add(mapTel);
               }
               emailListUpdate = new ArrayList<>();
               for (int i = 0 ; i < linearLayoutEamil.getChildCount() ; i++){

               }
                try {
                    updateContact();
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });
        Button button_cancel =  findViewById(R.id.detail_cancel);
    }
    public void updateContact() throws Exception{
        ContentResolver contentResolver = getContentResolver();
        Uri uri = Uri.parse("content://com.android.contacts/data");
        for (Map<String,Object> telMap : telListUpdate) {
            int id = (int) mapTel.get("id");
            Log.e("id", String.valueOf(id));
            ContentValues ct = new ContentValues();
            ct.put("data1",telMap.get("tel").toString() );
            contentResolver.update(uri,
                    ct,
                    ContactsContract.CommonDataKinds.Phone._ID+"=?",
                    new String[]{id+""} );

        }
        for (Map<String,Object> emailMap : emailListUpdate) {
            int id = (int) emailMap.get("id");
            Log.e("id", String.valueOf(id));
            ContentValues ct = new ContentValues();
            ct.put("data1",emailMap.get("email").toString() );
            contentResolver.update(uri,
                    ct,
                    ContactsContract.CommonDataKinds.Email._ID+"=?",
                    new String[]{id+""} );

        }
    }
}
