package com.example.a04_11;

import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by wang on 2018/4/11.
 */

public class StudentPullService {

    static public  List<Student> getStudentFromXML (InputStream inputStream) throws Exception {


        List<Student> studentList = new ArrayList<>();
        Student student =new Student();
        //创建解析器
        XmlPullParser parser = Xml.newPullParser();
        //初始化解析器
        parser.setInput(inputStream,"utf-8");
        //读取文件类型
        int type = parser.getEventType();
        //循环处理
        while (type != XmlPullParser.END_DOCUMENT){
            type = parser.next();
            switch (type){
                case XmlPullParser.START_TAG:
                    if ("students".equals(parser.getName())){
                        studentList = new ArrayList<>();
                    }else if ("student".equals(parser.getName())){
                        student = new Student();
                        String id = parser.getAttributeValue(null,"id");
                        student.setId(id);
                    } else if ("name".equals(parser.getName())){
                        String name = parser.nextText();
                        student.setName(name);
                    }else if ("age".equals(parser.getName())){
                        String age = parser.nextText();
                        student.setAge(age);
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if ("student".equals(parser.getName())){
                        studentList.add(student);
                    }
                    break;
            }

            //读取下一个

        }



        return studentList;
    }
}
