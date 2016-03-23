package com.kspt.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kspt.model.Metadata;

public interface MetadataMapper {
    int deleteByPrimaryKey(String id);

    int insert(Metadata record);

    int insertSelective(Metadata record);

    Metadata selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Metadata record);

    int updateByPrimaryKey(Metadata record);

	List<Metadata> selectBySelective(Metadata record);
}