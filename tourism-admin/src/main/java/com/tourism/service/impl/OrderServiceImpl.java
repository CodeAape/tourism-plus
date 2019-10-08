package com.tourism.service.impl;

import com.tourism.entity.Order;
import com.tourism.mapper.IOrderMapper;
import com.tourism.service.IOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrderServiceImpl implements IOrderService {

    @Resource
    private IOrderMapper orderMapper;

    @Override
    public int deleteByPrimaryKey(Integer oId) {
        return orderMapper.deleteByPrimaryKey(oId);
    }

    @Override
    public int insert(Order record) {
        return orderMapper.insert(record);
    }

    @Override
    public int insertSelective(Order record) {
        return orderMapper.insertSelective(record);
    }

    @Override
    public Order selectByPrimaryKey(Integer oId) {
        return orderMapper.selectByPrimaryKey(oId);
    }

    @Override
    public int quireOrderNum() {
        return orderMapper.quireOrderNum();
    }

    @Override
    public int updateByPrimaryKeySelective(Order record) {
        return orderMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Order record) {
        return orderMapper.updateByPrimaryKey(record);
    }

}
