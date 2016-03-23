package com.kspt.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kspt.model.SysSequence;

public interface SysSequenceMapper {
    int deleteByPrimaryKey(String sequenceName);

    int insert(SysSequence record);

    int insertSelective(SysSequence record);

    SysSequence selectByPrimaryKey(String sequenceName);

    int updateByPrimaryKeySelective(SysSequence record);

    int updateByPrimaryKey(SysSequence record);

	List<SysSequence> selectBySelective(SysSequence record);
}