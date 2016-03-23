package com.kspt.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kspt.common.ZqConstant;
import com.kspt.common.dao.daoImp.BaseDaoImp;
import com.kspt.model.NavFirst;
import com.kspt.util.DataConnomImpl;

@Service("navFirstMapper")
public class NavFirstMapperImp implements NavFirstMapper{
	private static final String KEY="com.kspt.dao.NavFirstMapper";
	@Resource
	private BaseDaoImp baseDao;
	@Resource 
	private DataConnomImpl connomImpl;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		return baseDao.delete(KEY+ZqConstant.DIAN+"deleteByPrimaryKey", id);
	}

	@Override
	public int insert(NavFirst record) {
		return baseDao.insert(KEY+ZqConstant.DIAN+"insert", record);
	}

	@Override
	public int insertSelective(NavFirst record) {
		return baseDao.insert(KEY+ZqConstant.DIAN+"insertSelective", record);
	}

	@Override
	public NavFirst selectByPrimaryKey(String id) {
		return (NavFirst) baseDao.queryOne(KEY+ZqConstant.DIAN+"selectByPrimaryKey", id);
	}

	@Override
	public int updateByPrimaryKeySelective(NavFirst record) {
		return baseDao.update(KEY+ZqConstant.DIAN+"updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(NavFirst record) {
		return baseDao.update(KEY+ZqConstant.DIAN+"updateByPrimaryKey", record);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NavFirst> selectBySelective(NavFirst record) {
		return  (List<NavFirst>) baseDao.query(KEY+ZqConstant.DIAN+"selectBySelective", record);
	}

	@Override
	public NavFirst selectOneBySelective(NavFirst record) {
		// TODO Auto-generated method stub
		return (NavFirst) baseDao.queryOne(KEY+ZqConstant.DIAN+"selectBySelective", record);
	}
	@Override
	public void upOrderIndex(Map<String, Object> map) {
		baseDao.update(KEY+ZqConstant.DIAN+"upOrderIndex", map);
	}
	@Override
	public void downOrderIndex(Map<String, Object> map) {
		baseDao.update(KEY+ZqConstant.DIAN+"downOrderIndex", map);
	}
}