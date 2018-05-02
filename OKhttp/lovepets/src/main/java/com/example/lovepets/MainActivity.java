package com.example.lovepets;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.IOException;
import java.text.MessageFormat;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private int USER_ID = 1;
    private static String BASE_URL = "http://192.168.23.1:8080/lovepets/";
    private static String TAG = "OKHTTP";
    private OkHttpClient okHttpClient = new OkHttpClient();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button login = findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name = findViewById(R.id.et_username);
                final String userName = name.getText().toString();
                EditText password = findViewById(R.id.et_password);
                final String userPassword = password.getText().toString();
                userLogin(userName,userPassword);
            }
        });
        Button register = findViewById(R.id.btn_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name = findViewById(R.id.et_username);
                final String userName = name.getText().toString();
                EditText password = findViewById(R.id.et_password);
                final String userPassword = password.getText().toString();
                EditText tel = findViewById(R.id.et_tel);
                final String userTel = tel.getText().toString();
                EditText email = findViewById(R.id.et_email);
                final String userEmail = email.getText().toString();
                userRegister(userName,userPassword,userTel,userEmail);
            }
        });

    }

    //注册
    private void userRegister(String username ,String password,String tel,String email){
        Gson gson = new Gson();
        User user = new User(USER_ID,username,password,tel,email);
        String str = gson.toJson(user);
        Log.e("user",str);
        //创建FromBody对象
        RequestBody requestBody =RequestBody.create(
                MediaType.parse("text/plain;charset=utf-8"),str);
        //创建请求对象
        final Request request = new Request.Builder()
                .post(requestBody)
                .url(BASE_URL + "UserRegisterServlet")
                .build();
        //创建Call对象，并执行请求
        final Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e(TAG, response.body().string());
            }
        });
    }

    //登录验证
    private void userLogin(String username,String password){

        //创建FromBody对象
        RequestBody requestBody = new FormBody.Builder()
                .add("user_name",username)
                .add("user_password",password)
                .build();
        //创建请求对象
        final Request request = new Request.Builder()
                .post(requestBody)
                .url(BASE_URL + "UserLoginServlet")
                .build();
        //创建Call对象，并执行请求
        final Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure( Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e(TAG, response.body().string());
            }
        });
    }
}
