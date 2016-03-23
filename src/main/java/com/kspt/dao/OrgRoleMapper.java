package com.kspt.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kspt.model.OrgRole;

public interface OrgRoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(OrgRole record);

    int insertSelective(OrgRole record);

    OrgRole selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OrgRole record);

    int updateByPrimaryKey(OrgRole record);

	List<OrgRole> selectBySelective(OrgRole record);
	List<OrgRole> selectAllGroup();
}