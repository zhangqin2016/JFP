package com.kspt.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kspt.common.ZqConstant;
import com.kspt.common.dao.daoImp.BaseDaoImp;
import com.kspt.model.NavSecond;
import com.kspt.util.DataConnomImpl;

@Service("navSecondMapper")
public class NavSecondMapperImp implements NavSecondMapper {
	private static final String KEY="com.kspt.dao.NavSecondMapper";
	@Resource
	private BaseDaoImp baseDao;
	@Resource 
	private DataConnomImpl connomImpl;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		return baseDao.delete(KEY+ZqConstant.DIAN+"deleteByPrimaryKey", id);
	}

	@Override
	public int insert(NavSecond record) {
		return baseDao.insert(KEY+ZqConstant.DIAN+"insert", record);
	}

	@Override
	public int insertSelective(NavSecond record) {
		return baseDao.insert(KEY+ZqConstant.DIAN+"insertSelective", record);
	}

	@Override
	public NavSecond selectByPrimaryKey(String id) {
		return (NavSecond) baseDao.queryOne(KEY+ZqConstant.DIAN+"selectByPrimaryKey", id);
	}

	@Override
	public int updateByPrimaryKeySelective(NavSecond record) {
		return baseDao.update(KEY+ZqConstant.DIAN+"updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(NavSecond record) {
		return baseDao.update(KEY+ZqConstant.DIAN+"updateByPrimaryKey", record);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NavSecond> selectBySelective(NavSecond record) {
		return  (List<NavSecond>) baseDao.query(KEY+ZqConstant.DIAN+"selectBySelective", record);
	}

	@Override
	public NavSecond selectOneBySelective(NavSecond record) {
		// TODO Auto-generated method stub
		return (NavSecond) baseDao.queryOne(KEY+ZqConstant.DIAN+"selectBySelective", record);
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