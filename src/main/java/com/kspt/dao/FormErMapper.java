package com.kspt.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kspt.model.FormEr;

public interface FormErMapper {
    int deleteByPrimaryKey(String id);

    int insertSelective(FormEr record);

    FormEr selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FormEr record);

	List<FormEr> selectBySelective(FormEr record);
}