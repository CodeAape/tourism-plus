package com.tourism.service.impl;

import com.tourism.entity.Category;
import com.tourism.mapper.ICategoryMapper;
import com.tourism.service.ICategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Resource
    private ICategoryMapper categoryMapper;

    @Override
    public int deleteByPrimaryKey(Integer catId) {
        return categoryMapper.deleteByPrimaryKey(catId);
    }

    @Override
    public int insert(Category record) {
        return categoryMapper.insert(record);
    }

    @Override
    public int insertSelective(Category record) {
        return categoryMapper.insertSelective(record);
    }

    @Override
    public Category selectByPrimaryKey(Integer catId) {
        return categoryMapper.selectByPrimaryKey(catId);
    }

    @Override
    public int updateByPrimaryKeySelective(Category record) {
        return categoryMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Category record) {
        return categoryMapper.updateByPrimaryKey(record);
    }

}
