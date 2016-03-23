package com.kspt.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kspt.model.FormUi;
import com.kspt.model.FormUiWithBLOBs;

public interface FormUiMapper {
    int deleteByPrimaryKey(String id);

    int insert(FormUiWithBLOBs record);

    int insertSelective(FormUiWithBLOBs record);

    FormUiWithBLOBs selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FormUiWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(FormUiWithBLOBs record);

    int updateByPrimaryKey(FormUi record);

	List<FormUiWithBLOBs> selectBySelective(FormUiWithBLOBs record);
}