package com.kspt.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kspt.common.ZqConstant;
import com.kspt.common.dao.daoImp.BaseDaoImp;
import com.kspt.model.FormEr;
import com.kspt.util.DataConnomImpl;

@Service("formErMapper")
public class FormErMapperImp implements FormErMapper{
	private static final String KEY="com.kspt.dao.FormErMapper";
	@Resource
	private BaseDaoImp baseDao;
	@Resource 
	private DataConnomImpl connomImpl;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		return baseDao.delete(KEY+ZqConstant.DIAN+"deleteByPrimaryKey", id);
	}


	@Override
	public int insertSelective(FormEr record) {
		return baseDao.insert(KEY+ZqConstant.DIAN+"insertSelective", record);
	}

	@Override
	public FormEr selectByPrimaryKey(String id) {
		return (FormEr) baseDao.queryOne(KEY+ZqConstant.DIAN+"selectByPrimaryKey", id);
	}

	@Override
	public int updateByPrimaryKeySelective(FormEr record) {
		return baseDao.update(KEY+ZqConstant.DIAN+"updateByPrimaryKeySelective", record);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<FormEr> selectBySelective(FormEr record) {
		return  (List<FormEr>) baseDao.query(KEY+ZqConstant.DIAN+"selectBySelective", record);
	}
}