package com.tourism.mapper;

import com.tourism.entity.Category;

public interface ICategoryMapper {
    int deleteByPrimaryKey(Integer catId);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer catId);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);
}