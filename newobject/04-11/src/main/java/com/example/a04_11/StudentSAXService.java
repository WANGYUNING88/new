package com.example.a04_11;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by wang on 2018/4/11.
 */

public class StudentSAXService {
    static public  List<Student> getStudentFromXML (InputStream inputStream) throws Exception {
        List<Student> studentList = new ArrayList<>();
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        MyHandle myHandle = new MyHandle();
        saxParser.parse(inputStream,myHandle);
        return myHandle.getStudentList();
    }
    static class MyHandle extends DefaultHandler {
        private Student student;
        private List<Student> studentList;
        private String tag;

        public List<Student> getStudentList(){
            return studentList;
        }
        //开始解析文档时
        @Override
        public void startDocument() throws SAXException {
            Log.e("MSG:", "开始解析");
            studentList = new ArrayList<>();
        }


        //标签开始
        @Override
        public void startElement(String uri,
                                 String localName, String qName,
                                 Attributes attributes) throws SAXException {
            if (qName.equals("student")) {
                student = new Student();
                student.setId(attributes.getValue("id"));
            }
            tag = qName;
        }
        //标签结束
        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if (qName.equals("student")) {
                studentList.add(student);
            }
            tag = null;
        }
        //标签内容
        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String data = new String(ch, start, length);
            if (data != null && tag != null){
                if (tag.equals("name")){
                    student.setName(data);
                }else if (tag.equals("age")){
                    student.setAge(data);
                }
            }
        }
        //解析结束时
        @Override
        public void endDocument() throws SAXException {
            Log.e("MSG:", "结束解析");
        }
    }
}
