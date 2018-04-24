package com.example.wang.gson;

/**
 * Created by wang on 2018/4/24.
 */

public class Student {
    private String studentNum;
    private String studentName;

    Student(String num , String name){
        studentName = name;
        studentNum = num;
    }

    public String getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(String studentNum) {
        this.studentNum = studentNum;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}
