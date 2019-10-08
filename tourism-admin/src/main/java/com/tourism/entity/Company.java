package com.tourism.entity;

import lombok.Data;

@Data
public class Company {
    /**
     * 站点编号
     */
    private Integer id;

    /**
     * 公司名称
     */
    private String name;

    /**
     * 公司地址
     */
    private String address;

    /**
     * 邮政编码
     */
    private String postcode;

    /**
     * 公司联系人
     */
    private String contact;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 公司传真
     */
    private String fax;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 公司QQ
     */
    private String qq;
}