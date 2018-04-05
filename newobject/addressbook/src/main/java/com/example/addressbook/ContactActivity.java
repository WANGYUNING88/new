package com.example.addressbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

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
        String contact_name = intent.getStringExtra("contact_name");
        TextView textView = findViewById(R.id.contact_name);
        textView.setText(contact_name);
    }
}
