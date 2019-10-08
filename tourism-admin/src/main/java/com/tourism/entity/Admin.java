package com.tourism.entity;

import lombok.Data;

@Data
public class Admin {
    private Integer aId;

    private String username;

    private String nickname;

    private String password;

    private String lastLogin;

    private String isDelete;

    public Admin(Integer aId, String username, String nickname, String password, String lastLogin, String isDelete) {
        this.aId = aId;
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.lastLogin = lastLogin;
        this.isDelete = isDelete;
    }

    public Admin() {
    }

    public Admin(String username, String nickname, String password) {
        this.username = username;
        this.nickname = nickname;
        this.password = password;
    }

    public Admin(Integer aId, String password) {
        this.aId = aId;
        this.password = password;
    }

    public Admin(String nickname) {
        this.nickname = nickname;
    }
}