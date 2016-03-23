package com.kspt.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kspt.common.ZqConstant;
import com.kspt.common.dao.daoImp.BaseDaoImp;
import com.kspt.model.NavThree;
import com.kspt.util.DataConnomImpl;

@Service("navThreeMapper")
public class NavThreeMapperImp implements NavThreeMapper{
	private static final String KEY="com.kspt.dao.NavThreeMapper";
	@Resource
	private BaseDaoImp baseDao;
	@Resource 
	private DataConnomImpl connomImpl;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		return baseDao.delete(KEY+ZqConstant.DIAN+"deleteByPrimaryKey", id);
	}

	@Override
	public int insert(NavThree record) {
		return baseDao.insert(KEY+ZqConstant.DIAN+"insert", record);
	}

	@Override
	public int insertSelective(NavThree record) {
		return baseDao.insert(KEY+ZqConstant.DIAN+"insertSelective", record);
	}

	@Override
	public NavThree selectByPrimaryKey(String id) {
		return (NavThree) baseDao.queryOne(KEY+ZqConstant.DIAN+"selectByPrimaryKey", id);
	}

	@Override
	public int updateByPrimaryKeySelective(NavThree record) {
		return baseDao.update(KEY+ZqConstant.DIAN+"updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(NavThree record) {
		return baseDao.update(KEY+ZqConstant.DIAN+"updateByPrimaryKey", record);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NavThree> selectBySelective(NavThree record) {
		return  (List<NavThree>) baseDao.query(KEY+ZqConstant.DIAN+"selectBySelective", record);
	}

	@Override
	public NavThree selectOneBySelective(NavThree record) {
		return  (NavThree) baseDao.query(KEY+ZqConstant.DIAN+"selectBySelective", record);
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