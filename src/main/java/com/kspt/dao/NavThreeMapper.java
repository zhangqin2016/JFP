package com.kspt.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kspt.model.NavThree;

public interface NavThreeMapper {
    int deleteByPrimaryKey(String id);

    int insert(NavThree record);

    int insertSelective(NavThree record);

    NavThree selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(NavThree record);

    int updateByPrimaryKey(NavThree record);

	List<NavThree> selectBySelective(NavThree record);
	NavThree selectOneBySelective(NavThree record);
	void upOrderIndex(Map<String, Object> map);
	void downOrderIndex(Map<String, Object> map);
}