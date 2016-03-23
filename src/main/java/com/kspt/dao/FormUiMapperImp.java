package com.kspt.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kspt.common.ZqConstant;
import com.kspt.common.dao.daoImp.BaseDaoImp;
import com.kspt.model.FormUi;
import com.kspt.model.FormUiWithBLOBs;
import com.kspt.util.DataConnomImpl;

@Service("formUiMapper")
public class FormUiMapperImp implements FormUiMapper{
	private static final String KEY="com.kspt.dao.FormUiMapper";
	@Resource
	private BaseDaoImp baseDao;
	@Resource 
	private DataConnomImpl connomImpl;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		return baseDao.delete(KEY+ZqConstant.DIAN+"deleteByPrimaryKey", id);
	}

	@Override
	public int insert(FormUiWithBLOBs record) {
		return baseDao.insert(KEY+ZqConstant.DIAN+"insert", record);
	}

	@Override
	public int insertSelective(FormUiWithBLOBs record) {
		return baseDao.insert(KEY+ZqConstant.DIAN+"insertSelective", record);
	}

	@Override
	public FormUiWithBLOBs selectByPrimaryKey(String id) {
		return (FormUiWithBLOBs) baseDao.queryOne(KEY+ZqConstant.DIAN+"selectByPrimaryKey", id);
	}

	@Override
	public int updateByPrimaryKeySelective(FormUiWithBLOBs record) {
		return baseDao.update(KEY+ZqConstant.DIAN+"updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(FormUi record) {
		return baseDao.update(KEY+ZqConstant.DIAN+"updateByPrimaryKey", record);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FormUiWithBLOBs> selectBySelective(FormUiWithBLOBs record) {
		return  (List<FormUiWithBLOBs>) baseDao.query(KEY+ZqConstant.DIAN+"selectBySelective", record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(FormUiWithBLOBs record) {
		return baseDao.update(KEY+ZqConstant.DIAN+"updateByPrimaryKeyWithBLOBs", record);
	}
}