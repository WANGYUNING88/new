package com.example.wang.gson;

/**
 * Created by wang on 2018/4/24.
 */

public class User {

    private String name;
    private int age;
    private String sex;


    User(String aName,int aAge,String aSex){
        name = aName;
        age = aAge;
        sex = aSex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
