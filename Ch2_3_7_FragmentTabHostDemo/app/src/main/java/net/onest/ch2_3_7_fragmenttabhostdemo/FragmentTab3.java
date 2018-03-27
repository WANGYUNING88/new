package net.onest.ch2_3_7_fragmenttabhostdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by lenovo on 2018-03-01.
 */

public class FragmentTab3 extends Fragment{

    public FragmentTab3() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View relativeLayout = inflater.inflate(R.layout.fragment_page, container,false);
        TextView contentPage = relativeLayout.findViewById(R.id.txt_page_content);
        contentPage.setText("模拟邮件页面");
        return relativeLayout;
//        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
