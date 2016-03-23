package com.kspt.portal.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kspt.common.dao.daoImp.BaseDaoImp;
import com.kspt.dao.OrgRoleMapper;
import com.kspt.dao.OrgUserMapper;
import com.kspt.model.OrgRole;
import com.kspt.model.OrgUser;
import com.kspt.util.DataConnomImpl;
import com.kspt.util.MD5Lock;

@RequestMapping("/portal/org/user")

@Controller
public class UserAction {
	@Resource
	private BaseDaoImp baseDao;
	@Resource 
	private DataConnomImpl connomImpl;
	
	@Resource
	private OrgUserMapper orgUserMapper;
	
	@Resource
	private OrgRoleMapper orgRoleMapper;
	@RequestMapping(value="/addPage",method={RequestMethod.POST,RequestMethod.GET})
	public String addPage(String type,String id,ModelMap map){
		map.put("type", type);
		map.put("id", id);
		map.put("role_id", "0");
		map.put("user_roleName", "请选择...");
		return "renyuanjiegou/renyuan_useradd";
	}
	@RequestMapping(value="/editPage",method={RequestMethod.POST,RequestMethod.GET})
	public String editPage(String type,String id,ModelMap map){
		id=id.replace("user_", "");
		map.put("type", type);
		map.put("id", id);
		OrgUser model=orgUserMapper.selectByPrimaryKey(id);
		map.put("user_account", model.getUserAccount());
		map.put("user_name", model.getUserName());
		map.put("dept_manager",model.getDeptManager());
		map.put("user_tel", model.getUserTel());
		map.put("user_fax", model.getUserFax());
		map.put("user_mobile", model.getUserMobile());
		map.put("user_mail", model.getUserMail());
		map.put("user_code", model.getUserCode());
		map.put("user_sex", model.getUserSex());
		OrgRole role=orgRoleMapper.selectByPrimaryKey(model.getRoleId());
		if(role!=null){
		map.put("role_id", model.getRoleId());
		map.put("user_roleName", role.getRoleGroup()+"-"+role.getRoleName());
		}else{
		map.put("role_id", "0");
		map.put("user_roleName", "请选择...");
		}
		return "renyuanjiegou/renyuan_useradd";
	}
	
	@Transactional
	@RequestMapping(value="/addUser",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String addDept(OrgUser userModel,String id,String type){
		if(id.indexOf("_")==-1){
			orgUserMapper.updateByPrimaryKeySelective(userModel);
			return "更新成功";
		}else{
			OrgUser query=new OrgUser();
			query.setUserAccount(userModel.getUserAccount());
			List<OrgUser>lu=orgUserMapper.selectBySelective(query);
		if(lu==null||lu.size()==0){
		
		userModel.setId(connomImpl.getUUID());
		userModel.setUserPassword(MD5Lock.MD5("123456"));
		userModel.setOrderIndex(Long.parseLong(connomImpl.getSequence("zq_org_user")+""));
		if(type.equals("comp")){
			userModel.setCompId(id.replace("comp_", ""));
		}else{
			userModel.setDeptId(id.replace("dept_", ""));
		}
		orgUserMapper.insertSelective(userModel);
			return "添加成功";
		}else{
			return "已存在的账户";	
		}
		}
	}
}
