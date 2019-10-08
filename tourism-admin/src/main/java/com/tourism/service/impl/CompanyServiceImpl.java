package com.tourism.service.impl;

import com.tourism.entity.Company;
import com.tourism.mapper.ICompanyMapper;
import com.tourism.service.ICompanyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CompanyServiceImpl implements ICompanyService {

    @Resource
    private ICompanyMapper companyMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return companyMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Company record) {
        return companyMapper.insert(record);
    }

    @Override
    public int insertSelective(Company record) {
        return companyMapper.insertSelective(record);
    }

    @Override
    public Company selectByPrimaryKey(Integer id) {
        return companyMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Company record) {
        return companyMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Company record) {
        return companyMapper.updateByPrimaryKey(record);
    }

}
