package com.kspt.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kspt.model.OrgDepartment;

public interface OrgDepartmentMapper {
    int deleteByPrimaryKey(String id);

    int insert(OrgDepartment record);

    int insertSelective(OrgDepartment record);

    OrgDepartment selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OrgDepartment record);

    int updateByPrimaryKey(OrgDepartment record);

	List<OrgDepartment> selectBySelective(OrgDepartment record);
}