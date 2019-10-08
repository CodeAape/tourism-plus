package com.tourism.service;

import com.tourism.entity.Article;

import java.util.List;

public interface IArticleService {


    int deleteByPrimaryKey(Integer articleId);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer articleId);

    int selectPage();

    List<Article> selectByList(int pageStart, int pageEnd);

    List<Article> list();

    List<Article> selectUnPage();

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKey(Article record);

}
