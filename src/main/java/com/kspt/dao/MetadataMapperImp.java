package com.kspt.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kspt.common.ZqConstant;
import com.kspt.common.dao.daoImp.BaseDaoImp;
import com.kspt.model.Metadata;
import com.kspt.util.DataConnomImpl;

@Service("metadataMapper")
public class MetadataMapperImp implements MetadataMapper{
	private static final String KEY="com.kspt.dao.MetadataMapper";
	@Resource
	private BaseDaoImp baseDao;
	@Resource 
	private DataConnomImpl connomImpl;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		return baseDao.delete(KEY+ZqConstant.DIAN+"deleteByPrimaryKey", id);
	}

	@Override
	public int insert(Metadata record) {
		return baseDao.insert(KEY+ZqConstant.DIAN+"insert", record);
	}

	@Override
	public int insertSelective(Metadata record) {
		return baseDao.insert(KEY+ZqConstant.DIAN+"insertSelective", record);
	}

	@Override
	public Metadata selectByPrimaryKey(String id) {
		return (Metadata) baseDao.queryOne(KEY+ZqConstant.DIAN+"selectByPrimaryKey", id);
	}

	@Override
	public int updateByPrimaryKeySelective(Metadata record) {
		return baseDao.update(KEY+ZqConstant.DIAN+"updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(Metadata record) {
		return baseDao.update(KEY+ZqConstant.DIAN+"updateByPrimaryKey", record);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Metadata> selectBySelective(Metadata record) {
		return  (List<Metadata>) baseDao.query(KEY+ZqConstant.DIAN+"selectBySelective", record);
	}
}