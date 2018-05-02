package net.onest.okhttpdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BookListActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list2);

        listView = findViewById(R.id.lv_book);

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(Constant.BASE_URL + "GetBookList")
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String bookListStr = response.body().string();
                Gson gson = new Gson();
                Type type = new TypeToken<List<Book>>(){}.getType();
                List<Book> bookList = gson.fromJson(bookListStr, type);
                BookListAdapter bookListAdapter = new BookListAdapter(
                        BookListActivity.this, bookList);

                listView.setAdapter(bookListAdapter);
            }
        });

    }
}
