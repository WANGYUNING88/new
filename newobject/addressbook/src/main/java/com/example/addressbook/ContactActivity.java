package com.example.addressbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by wang on 2018/4/4.
 */

public class ContactActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_details);
        Intent intent = getIntent();
        int position = intent.getIntExtra("position",
                0);
        final HashMap<String,Object> contactMap =
                (HashMap<String, Object>) intent.getSerializableExtra("contact_map");
        TextView textView = findViewById(R.id.contact_name);
        textView.setText((String)contactMap.get("contact_name"));
        List<String> telList = (List<String>) contactMap.get("contact_tel");
        List<String> emailList = (List<String>) contactMap.get("contact_email");
        LinearLayout linearLayoutTel = findViewById(R.id.detail_tel);
        if (telList != null) {
            for (String tel : telList) {
                View telView = View.inflate(linearLayoutTel.getContext(), R.layout.layout_tel_item, null);
                TextView textView1 = telView.findViewById(R.id.contact_tel);
                textView1.setText(tel);
                linearLayoutTel.addView(telView);
            }
        }
        LinearLayout linearLayoutEamil = findViewById(R.id.detail_email);
        if (emailList != null) {
            for (String email : emailList) {
                View emailView = View.inflate(linearLayoutEamil.getContext(), R.layout.layout_email_item, null);
                TextView textView2 = emailView.findViewById(R.id.contact_email);
                textView2.setText(email);
                linearLayoutEamil.addView(emailView);
            }
        }
        Button button =  findViewById(R.id.detail_update);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ContactActivity.this,UpdateActivity.class);
                intent.putExtra("contact_map",contactMap);
//              startActivityForResult(intent, 0);
                startActivity(intent);
            }
        });
    }

}

