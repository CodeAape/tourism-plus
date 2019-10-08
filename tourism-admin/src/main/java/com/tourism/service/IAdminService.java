package com.tourism.service;

import com.tourism.entity.Admin;

public interface IAdminService {


    int deleteByPrimaryKey(Integer aId);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer aId);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    int findByUP(String username, String password);

    int idByName(String username);

}
