package com.kspt.portal.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kspt.common.JsonUtil;
import com.kspt.dao.OrgCompanyMapper;
import com.kspt.dao.OrgDepartmentMapper;
import com.kspt.model.OrgCompany;
import com.kspt.model.OrgDepartment;
import com.kspt.util.DataConnomImpl;

@RequestMapping("/portal/org/company")

@Controller
public class CompanyAction {
	@Resource(name="orgCompanyMapper")
	private OrgCompanyMapper orgCompanyMapper;
	@Resource(name="orgDepartmentMapper")
	private OrgDepartmentMapper orgDepartmentMapper;
	@Resource 
	private DataConnomImpl connomImpl;
	@RequestMapping(method={RequestMethod.POST,RequestMethod.GET})
	public String getrenyuan_companygrid(){
		return "renyuanjiegou/renyuan_companygrid";
	}
	@RequestMapping(value="/gridJson",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String getCompanyGridJson(){
		Map<String, Object>map=new HashMap<String, Object>();
		List<OrgCompany>lc= orgCompanyMapper.selectBySelective(new OrgCompany());
		map.put("root", lc);
		map.put("totalProperty", lc.size());
			 	return JsonUtil.toJSONString(map);
	}
	
	@RequestMapping(value="/addPage",method={RequestMethod.POST,RequestMethod.GET})
	public String addPage(){
		return "renyuanjiegou/renyuan_companyadd";
	}
	
	@Transactional
	@RequestMapping(value="/addCompany",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String addCompany(OrgCompany companyModel){
		companyModel.setId(connomImpl.getUUID());
		companyModel.setOrderIndex(Long.parseLong(String.valueOf(connomImpl.getSequence("zq_org_company"))));
		orgCompanyMapper.insertSelective(companyModel);
		return "添加成功";
	}
	
	@Transactional
	@RequestMapping(value="/updateCompany",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String updateCompany(OrgCompany companyModel){
		companyModel.setId(connomImpl.getUUID());
		companyModel.setOrderIndex(Long.parseLong(String.valueOf(connomImpl.getSequence("zq_org_company"))));
		orgCompanyMapper.updateByPrimaryKeySelective(companyModel);
		return "更新成功";
	}
	
	@Transactional
	@RequestMapping(value="/delCompany",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String delCompany(String ids[]){
		for (String string : ids) {
			OrgDepartment orgDepartment=new OrgDepartment();
			orgDepartment.setCoId(string);
			List<OrgDepartment>orgDepartments=orgDepartmentMapper.selectBySelective(orgDepartment);
			int count=orgDepartments==null?0:orgDepartments.size();
			if(count==0){
				orgCompanyMapper.deleteByPrimaryKey(string);
			}
		}
		return "操作成功！如有未删除请清除单位下的部门再进行删除！";
	}
}
