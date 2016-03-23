package com.kspt.dao;

import java.util.List;

import com.kspt.model.OrgMembership;

public interface OrgMembershipMapper {
    int deleteByPrimaryKey(String id);

    int insert(OrgMembership record);

    int insertSelective(OrgMembership record);

    OrgMembership selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OrgMembership record);

    int updateByPrimaryKey(OrgMembership record);

	List<OrgMembership> selectBySelective(OrgMembership record);
}