package com.kspt.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kspt.common.ZqConstant;
import com.kspt.common.dao.daoImp.BaseDaoImp;
import com.kspt.model.ModelLibrary;
import com.kspt.util.DataConnomImpl;

@Service("modelLibraryMapper")
public class ModelLibraryMapperImp implements ModelLibraryMapper{
	private static final String KEY="com.kspt.dao.ModelLibraryMapper";
	@Resource
	private BaseDaoImp baseDao;
	@Resource 
	private DataConnomImpl connomImpl;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		return baseDao.delete(KEY+ZqConstant.DIAN+"deleteByPrimaryKey", id);
	}

	@Override
	public int insert(ModelLibrary record) {
		return baseDao.insert(KEY+ZqConstant.DIAN+"insert", record);
	}

	@Override
	public int insertSelective(ModelLibrary record) {
		return baseDao.insert(KEY+ZqConstant.DIAN+"insertSelective", record);
	}

	@Override
	public ModelLibrary selectByPrimaryKey(String id) {
		return (ModelLibrary) baseDao.queryOne(KEY+ZqConstant.DIAN+"selectByPrimaryKey", id);
	}

	@Override
	public int updateByPrimaryKeySelective(ModelLibrary record) {
		return baseDao.update(KEY+ZqConstant.DIAN+"updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(ModelLibrary record) {
		return baseDao.update(KEY+ZqConstant.DIAN+"updateByPrimaryKey", record);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ModelLibrary> selectBySelective(ModelLibrary record) {
		return  (List<ModelLibrary>) baseDao.query(KEY+ZqConstant.DIAN+"selectBySelective", record);
	}
}