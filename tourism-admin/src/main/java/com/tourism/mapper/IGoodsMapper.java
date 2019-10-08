package com.tourism.mapper;

import com.tourism.entity.Goods;

public interface IGoodsMapper {
    int deleteByPrimaryKey(Integer gId);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Integer gId);

    int goodsNum();

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);
}