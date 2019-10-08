package com.tourism.entity;

import lombok.Data;

@Data
public class User {
    private Integer uId;

    private String nickname;

    private String username;

    private String password;

    private String sex;

    private Integer age;

    private String email;

    private String phone;

    private String address;

    private String describe;

    private Integer isDelete;

    public User(String nickname, String username, String password, String sex, String email, String phone, String describe) {
        this.nickname = nickname;
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.email = email;
        this.phone = phone;
        this.describe = describe;
    }
}