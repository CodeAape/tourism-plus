package com.tourism.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Category {
    private Integer catId;

    private String catName;

    private Date date;

    private String about;

    private Integer goodsId;
}