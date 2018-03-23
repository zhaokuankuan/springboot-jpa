package com.kk.springbootjpa.param;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class UserParam {

    @NotEmpty(message = "用户姓名不能为空！")
    private String userName;
    @NotEmpty(message = "密码不能为空！")
    @Length(min = 6,message = "密码不能小于6位！")
    private String passWord;

    private int age;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public void setAge(int age) {
        this.age = age;
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
}
