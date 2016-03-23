package com.kspt.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kspt.model.Form;

public interface FormMapper {
    int deleteByPrimaryKey(String id);

    int insert(Form record);

    int insertSelective(Form record);

    Form selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Form record);

    int updateByPrimaryKey(Form record);

	List<Form> selectBySelective(Form record);
}