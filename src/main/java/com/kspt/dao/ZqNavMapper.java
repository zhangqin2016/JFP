package com.kspt.dao;

import java.util.List;

import com.kspt.model.ZqNav;

public interface ZqNavMapper {

    int deleteByPrimaryKey(String id);

    int insert(ZqNav record);

    int insertSelective(ZqNav record);

    ZqNav selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ZqNav record);

    int updateByPrimaryKey(ZqNav record);
    
	List<ZqNav> selectBySelective(ZqNav record);
}