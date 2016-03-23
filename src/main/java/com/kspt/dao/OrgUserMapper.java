package com.kspt.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kspt.model.OrgUser;

public interface OrgUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(OrgUser record);

    int insertSelective(OrgUser record);

    OrgUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OrgUser record);

    int updateByPrimaryKey(OrgUser record);

	List<OrgUser> selectBySelective(OrgUser record);
}