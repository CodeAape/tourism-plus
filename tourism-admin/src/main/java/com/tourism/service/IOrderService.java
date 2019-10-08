package com.tourism.service;

import com.tourism.entity.Order;

public interface IOrderService {


    int deleteByPrimaryKey(Integer oId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer oId);

    int quireOrderNum();

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

}
