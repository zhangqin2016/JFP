package com.kspt.dao;

import java.util.List;
import java.util.Map;

import com.kspt.model.NavFirst;

public interface NavFirstMapper {
    int deleteByPrimaryKey(String id);

    int insert(NavFirst record);

    int insertSelective(NavFirst record);

    NavFirst selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(NavFirst record);

    int updateByPrimaryKey(NavFirst record);

	List<NavFirst> selectBySelective(NavFirst record);
	 NavFirst  selectOneBySelective(NavFirst record);

	void upOrderIndex(Map<String, Object> map);

	void downOrderIndex(Map<String, Object> map);
}