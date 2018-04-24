package com.example.wang.gson;

/**
 * Created by wang on 2018/4/24.
 */

public class Person {
    private String name;
    private User user;

    Person(String aName ,User aUer){
        name = aName;
        user = aUer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
