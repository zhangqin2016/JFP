package com.kspt.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kspt.model.MetadataMap;

public interface MetadataMapMapper {
    int deleteByPrimaryKey(String id);

    int insert(MetadataMap record);

    int insertSelective(MetadataMap record);

    MetadataMap selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MetadataMap record);

    int updateByPrimaryKey(MetadataMap record);

	List<MetadataMap> selectBySelective(MetadataMap record);
}