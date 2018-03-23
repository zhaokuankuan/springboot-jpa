package com.kk.springbootjpa.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class User {
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false,unique = true)
    private String userName;
    @Column(nullable = false)
    private String passWord;
    @Column(nullable = false)
    private int age;
    @Column(nullable = false)
    private Date regTime;

    public User() {
    }

    public User(String userName, String passWord, int age, Date regTime) {
        this.userName = userName;
        this.passWord = passWord;
        this.age = age;
        this.regTime = regTime;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public int getAge() {
        return age;
    }

    public Date getRegTime() {
        return regTime;
    }
}
