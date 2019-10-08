package com.tourism.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Article {
    private Integer articleId;

    private String title;

    private String author;

    private String content;

    private Date time;

    private Integer isDelete;
}