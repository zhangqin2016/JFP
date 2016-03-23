package com.kspt.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kspt.model.ModelLibrary;

public interface ModelLibraryMapper {
    int deleteByPrimaryKey(String id);

    int insert(ModelLibrary record);

    int insertSelective(ModelLibrary record);

    ModelLibrary selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ModelLibrary record);

    int updateByPrimaryKey(ModelLibrary record);

	List<ModelLibrary> selectBySelective(ModelLibrary record);
}