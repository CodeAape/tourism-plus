package com.tourism.mapper;

import com.tourism.entity.User;

public interface IUserMapper {
    int deleteByPrimaryKey(Integer uiD);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer uiD);

    int userNum();

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}