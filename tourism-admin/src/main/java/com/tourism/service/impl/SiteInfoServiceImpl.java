package com.tourism.service.impl;

import com.tourism.entity.SiteInfo;
import com.tourism.mapper.ISiteInfoMapper;
import com.tourism.service.ISiteInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SiteInfoServiceImpl implements ISiteInfoService {

    @Resource
    private ISiteInfoMapper siteInfoMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return siteInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SiteInfo record) {
        return siteInfoMapper.insert(record);
    }

    @Override
    public int insertSelective(SiteInfo record) {
        return siteInfoMapper.insertSelective(record);
    }

    @Override
    public SiteInfo selectByPrimaryKey(Integer id) {
        return siteInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public SiteInfo quireSiteInfo() {
        return siteInfoMapper.quireSiteInfo();
    }

    @Override
    public int updateByPrimaryKeySelective(SiteInfo record) {
        return siteInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SiteInfo record) {
        return siteInfoMapper.updateByPrimaryKey(record);
    }

}
