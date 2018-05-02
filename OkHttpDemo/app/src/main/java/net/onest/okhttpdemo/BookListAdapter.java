package net.onest.okhttpdemo;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zyl on 2018/4/26.
 */

public class BookListAdapter extends BaseAdapter {
    private Activity context;
    private List<Book> bookList = new ArrayList<>();

    public BookListAdapter(Activity context,
                           List<Book> bookList ){

        this.context = context;
        this.bookList = bookList;
    }

    @Override
    public int getCount() {
        return bookList.size();
    }

    @Override
    public Object getItem(int position) {
        return bookList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = context.getLayoutInflater().inflate(
                    R.layout.book_list_item, null);
        }
        ImageView imageView = convertView.findViewById(R.id.iv_book);
        TextView textViewName = convertView.findViewById(R.id.tv_book_name);
        TextView textViewPrice = convertView.findViewById(R.id.tv_book_price);
        Glide.with(context)
                .load(Constant.BASE_URL + bookList.get(position).getBookImages().get(0))
                .into(imageView);
        textViewName.setText(bookList.get(position).getBookName());
        textViewPrice.setText(bookList.get(position).getBookPrice());
        return convertView;
    }
}
