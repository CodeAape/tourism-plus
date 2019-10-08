package com.tourism.service.impl;

import com.tourism.entity.Admin;
import com.tourism.mapper.IAdminMapper;
import com.tourism.service.IAdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminServiceImpl implements IAdminService {

    @Resource
    private IAdminMapper adminMapper;

    @Override
    public int deleteByPrimaryKey(Integer aId) {
        return adminMapper.deleteByPrimaryKey(aId);
    }

    @Override
    public int insert(Admin record) {
        return adminMapper.insert(record);
    }

    @Override
    public int insertSelective(Admin record) {
        return adminMapper.insertSelective(record);
    }

    @Override
    public Admin selectByPrimaryKey(Integer aId) {
        return adminMapper.selectByPrimaryKey(aId);
    }

    @Override
    public int updateByPrimaryKeySelective(Admin record) {
        return adminMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Admin record) {
        return adminMapper.updateByPrimaryKey(record);
    }

    @Override
    public int findByUP(String username, String password) {
        return adminMapper.findByUP(username, password);
    }

    @Override
    public int idByName(String username) {
        return adminMapper.idByName(username);
    }
}
