package com.tourism.mapper;

import com.tourism.entity.Admin;
import org.apache.ibatis.annotations.Param;

public interface IAdminMapper {
    int deleteByPrimaryKey(Integer aId);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer aId);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    int findByUP(String username, String password);

    int idByName(@Param("username") String username);
}