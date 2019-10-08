package com.tourism.service.impl;

import com.tourism.entity.Article;
import com.tourism.mapper.IArticleMapper;
import com.tourism.service.IArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleServiceImpl implements IArticleService {

    @Resource
    private IArticleMapper articleMapper;

    @Override
    public int deleteByPrimaryKey(Integer articleId) {
        return articleMapper.deleteByPrimaryKey(articleId);
    }

    @Override
    public int insert(Article record) {
        return articleMapper.insert(record);
    }

    @Override
    public int insertSelective(Article record) {
        return articleMapper.insertSelective(record);
    }

    @Override
    public Article selectByPrimaryKey(Integer articleId) {
        return articleMapper.selectByPrimaryKey(articleId);
    }

    @Override
    public int selectPage() {
        return articleMapper.selectPage();
    }

    @Override
    public List<Article> selectByList(int pageStart, int pageEnd) {
        return articleMapper.selectByList(pageStart, pageEnd);
    }

    @Override
    public List<Article> list() {
        return articleMapper.list();
    }

    @Override
    public List<Article> selectUnPage() {
        return articleMapper.selectUnPage();
    }


    @Override
    public int updateByPrimaryKeySelective(Article record) {
        return articleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Article record) {
        return articleMapper.updateByPrimaryKey(record);
    }

}
