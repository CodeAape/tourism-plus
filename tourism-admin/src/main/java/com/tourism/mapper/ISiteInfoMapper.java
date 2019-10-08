package com.tourism.mapper;

import com.tourism.entity.SiteInfo;

public interface ISiteInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SiteInfo record);

    int insertSelective(SiteInfo record);

    SiteInfo selectByPrimaryKey(Integer id);

    SiteInfo quireSiteInfo();

    int updateByPrimaryKeySelective(SiteInfo record);

    int updateByPrimaryKey(SiteInfo record);
}