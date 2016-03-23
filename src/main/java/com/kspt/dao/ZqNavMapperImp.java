package com.kspt.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kspt.common.ZqConstant;
import com.kspt.common.dao.daoImp.BaseDaoImp;
import com.kspt.model.ZqNav;
import com.kspt.util.DataConnomImpl;

@Service("sysSequenceMapper")
public class ZqNavMapperImp implements ZqNavMapper{
	private static final String KEY="com.kspt.dao.SysSequenceMapper";
	@Resource
	private BaseDaoImp baseDao;
	@Resource 
	private DataConnomImpl connomImpl;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		return baseDao.delete(KEY+ZqConstant.DIAN+"deleteByPrimaryKey", id);
	}

	@Override
	public int insert(ZqNav record) {
		return baseDao.insert(KEY+ZqConstant.DIAN+"insert", record);
	}

	@Override
	public int insertSelective(ZqNav record) {
		return baseDao.insert(KEY+ZqConstant.DIAN+"insertSelective", record);
	}

	@Override
	public ZqNav selectByPrimaryKey(String id) {
		return (ZqNav) baseDao.queryOne(KEY+ZqConstant.DIAN+"selectByPrimaryKey", id);
	}

	@Override
	public int updateByPrimaryKeySelective(ZqNav record) {
		return baseDao.update(KEY+ZqConstant.DIAN+"updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(ZqNav record) {
		return baseDao.update(KEY+ZqConstant.DIAN+"updateByPrimaryKey", record);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ZqNav> selectBySelective(ZqNav record) {
		return  (List<ZqNav>) baseDao.query(KEY+ZqConstant.DIAN+"selectBySelective", record);
	}
}