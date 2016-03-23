package com.kspt.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kspt.common.ZqConstant;
import com.kspt.common.dao.daoImp.BaseDaoImp;
import com.kspt.model.MetadataMap;
import com.kspt.util.DataConnomImpl;

@Service("metadataMapMapper")
public class MetadataMapMapperImp implements MetadataMapMapper{
	private static final String KEY="com.kspt.dao.MetadataMapMapper";
	@Resource
	private BaseDaoImp baseDao;
	@Resource 
	private DataConnomImpl connomImpl;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		return baseDao.delete(KEY+ZqConstant.DIAN+"deleteByPrimaryKey", id);
	}

	@Override
	public int insert(MetadataMap record) {
		return baseDao.insert(KEY+ZqConstant.DIAN+"insert", record);
	}

	@Override
	public int insertSelective(MetadataMap record) {
		return baseDao.insert(KEY+ZqConstant.DIAN+"insertSelective", record);
	}

	@Override
	public MetadataMap selectByPrimaryKey(String id) {
		return (MetadataMap) baseDao.queryOne(KEY+ZqConstant.DIAN+"selectByPrimaryKey", id);
	}

	@Override
	public int updateByPrimaryKeySelective(MetadataMap record) {
		return baseDao.update(KEY+ZqConstant.DIAN+"updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(MetadataMap record) {
		return baseDao.update(KEY+ZqConstant.DIAN+"updateByPrimaryKey", record);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MetadataMap> selectBySelective(MetadataMap record) {
		return  (List<MetadataMap>) baseDao.query(KEY+ZqConstant.DIAN+"selectBySelective", record);
	}
}