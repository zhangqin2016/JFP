package com.kspt.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kspt.common.ZqConstant;
import com.kspt.common.dao.daoImp.BaseDaoImp;
import com.kspt.model.OrgDepartment;
import com.kspt.util.DataConnomImpl;

@Service("orgDepartmentMapper")
public class OrgDepartmentMapperImp implements OrgDepartmentMapper{
	private static final String KEY="com.kspt.dao.OrgDepartmentMapper";
	@Resource
	private BaseDaoImp baseDao;
	@Resource 
	private DataConnomImpl connomImpl;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		return baseDao.delete(KEY+ZqConstant.DIAN+"deleteByPrimaryKey", id);
	}

	@Override
	public int insert(OrgDepartment record) {
		return baseDao.insert(KEY+ZqConstant.DIAN+"insert", record);
	}

	@Override
	public int insertSelective(OrgDepartment record) {
		return baseDao.insert(KEY+ZqConstant.DIAN+"insertSelective", record);
	}

	@Override
	public OrgDepartment selectByPrimaryKey(String id) {
		return (OrgDepartment) baseDao.queryOne(KEY+ZqConstant.DIAN+"selectByPrimaryKey", id);
	}

	@Override
	public int updateByPrimaryKeySelective(OrgDepartment record) {
		return baseDao.update(KEY+ZqConstant.DIAN+"updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(OrgDepartment record) {
		return baseDao.update(KEY+ZqConstant.DIAN+"updateByPrimaryKey", record);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrgDepartment> selectBySelective(OrgDepartment record) {
		return  (List<OrgDepartment>) baseDao.query(KEY+ZqConstant.DIAN+"selectBySelective", record);
	}
}