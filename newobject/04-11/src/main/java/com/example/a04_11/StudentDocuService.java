package com.example.a04_11;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by wang on 2018/4/11.
 */

public class StudentDocuService {

   static public   List<Student> getStudentFromXML (InputStream inputStream) throws Exception {
       List<Student> studentList = new ArrayList<>();
        //创建DocumentBuilderFactory
       DocumentBuilderFactory documentBuilderFactory =
               DocumentBuilderFactory.newInstance();
       //创建DocumentBuilder
       DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

       Document document = documentBuilder.parse(inputStream);
       //获得节点
       NodeList nodeList = document.getElementsByTagName("student");

       for (int i = 0 ; i < nodeList.getLength() ; i ++){
           Node node = nodeList.item(i);
           Student student = new Student();
           //获取id
           student.setId(node.getAttributes().item(0).getTextContent());
           //获取标签的子标签
           NodeList childNodeList = node.getChildNodes();
           for (int j = 0 ; j < childNodeList.getLength() ; j ++){
               Node childNode = childNodeList.item(j);
               if (childNode.getNodeName().equals("name")){
                   student.setName(childNode.getTextContent());
               }else if (childNode.getNodeName().equals("age")){
                   student.setAge(childNode.getTextContent());
               }
           }
           studentList.add(student);
       }

       return studentList;
   }
}
