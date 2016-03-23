package com.kspt.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kspt.model.NavSecond;

public interface NavSecondMapper {
    int deleteByPrimaryKey(String id);

    int insert(NavSecond record);

    int insertSelective(NavSecond record);

    NavSecond selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(NavSecond record);

    int updateByPrimaryKey(NavSecond record);

	List<NavSecond> selectBySelective(NavSecond record);
	NavSecond selectOneBySelective(NavSecond record);

	void upOrderIndex(Map<String, Object> map);

	void downOrderIndex(Map<String, Object> map);
}