package com.example.lovepets;

/**
 * Created by wang on 2018/5/2.
 */

public class User {
    private int userId;
    private String userName ;
    private String password;
    private String tel;
    private String email;

    public User() {

    }
    public User(int id,String user_name ,String user_password,String user_tel,String user_email) {
        userId = id;
        userName = user_name;
        password = user_password;
        tel = user_tel;
        email = user_email;
    }

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
