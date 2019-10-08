package com.tourism.service;

import com.tourism.entity.Goods;

import java.util.List;

public interface IGoodsService {


    int deleteByPrimaryKey(Integer gId);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Integer gId);

    List selectList();

    int goodsNum();

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);

}
