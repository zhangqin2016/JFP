package com.kspt.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kspt.common.ZqConstant;
import com.kspt.common.dao.daoImp.BaseDaoImp;
import com.kspt.model.OrgRole;
import com.kspt.util.DataConnomImpl;

@Service("orgRoleMapper")
public class OrgRoleMapperImp implements OrgRoleMapper{
	private static final String KEY="com.kspt.dao.OrgRoleMapper";
	@Resource
	private BaseDaoImp baseDao;
	@Resource 
	private DataConnomImpl connomImpl;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		return baseDao.delete(KEY+ZqConstant.DIAN+"deleteByPrimaryKey", id);
	}

	@Override
	public int insert(OrgRole record) {
		return baseDao.insert(KEY+ZqConstant.DIAN+"insert", record);
	}

	@Override
	public int insertSelective(OrgRole record) {
		return baseDao.insert(KEY+ZqConstant.DIAN+"insertSelective", record);
	}

	@Override
	public OrgRole selectByPrimaryKey(String id) {
		return (OrgRole) baseDao.queryOne(KEY+ZqConstant.DIAN+"selectByPrimaryKey", id);
	}

	@Override
	public int updateByPrimaryKeySelective(OrgRole record) {
		return baseDao.update(KEY+ZqConstant.DIAN+"updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(OrgRole record) {
		return baseDao.update(KEY+ZqConstant.DIAN+"updateByPrimaryKey", record);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrgRole> selectBySelective(OrgRole record) {
		return  (List<OrgRole>) baseDao.query(KEY+ZqConstant.DIAN+"selectBySelective", record);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrgRole> selectAllGroup() {
		return(List<OrgRole>) baseDao.query(KEY+ZqConstant.DIAN+"selectAllGroup");
	}
}