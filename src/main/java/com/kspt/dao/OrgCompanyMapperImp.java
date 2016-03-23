package com.kspt.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kspt.common.ZqConstant;
import com.kspt.common.dao.daoImp.BaseDaoImp;
import com.kspt.model.OrgCompany;
import com.kspt.util.DataConnomImpl;

@Service("orgCompanyMapper")
public class OrgCompanyMapperImp implements OrgCompanyMapper{
	private static final String KEY="com.kspt.dao.OrgCompanyMapper";

	@Resource
	private BaseDaoImp baseDao;
	@Resource 
	private DataConnomImpl connomImpl;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		return baseDao.delete(KEY+ZqConstant.DIAN+"deleteByPrimaryKey", id);
	}

	@Override
	public int insert(OrgCompany record) {
		return baseDao.insert(KEY+ZqConstant.DIAN+"insert", record);
	}

	@Override
	public int insertSelective(OrgCompany record) {
		return baseDao.insert(KEY+ZqConstant.DIAN+"insertSelective", record);
	}

	@Override
	public OrgCompany selectByPrimaryKey(String id) {
		return (OrgCompany) baseDao.queryOne(KEY+ZqConstant.DIAN+"selectByPrimaryKey", id);
	}

	@Override
	public int updateByPrimaryKeySelective(OrgCompany record) {
		return baseDao.update(KEY+ZqConstant.DIAN+"updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(OrgCompany record) {
		return baseDao.update(KEY+ZqConstant.DIAN+"updateByPrimaryKeyWithBLOBs", record);
	}

	@Override
	public int updateByPrimaryKey(OrgCompany record) {
		return baseDao.update(KEY+ZqConstant.DIAN+"updateByPrimaryKey", record);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrgCompany> selectBySelective(OrgCompany record) {
		return  (List<OrgCompany>) baseDao.query(KEY+ZqConstant.DIAN+"selectBySelective", record);
	}


	
}