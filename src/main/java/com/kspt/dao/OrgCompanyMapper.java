package com.kspt.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kspt.model.OrgCompany;

public interface OrgCompanyMapper {
    int deleteByPrimaryKey(String id);

    int insert(OrgCompany record);

    int insertSelective(OrgCompany record);

    OrgCompany selectByPrimaryKey(String id);
    List<OrgCompany> selectBySelective(OrgCompany record);
    int updateByPrimaryKeySelective(OrgCompany record);

    int updateByPrimaryKeyWithBLOBs(OrgCompany record);

    int updateByPrimaryKey(OrgCompany record);
}