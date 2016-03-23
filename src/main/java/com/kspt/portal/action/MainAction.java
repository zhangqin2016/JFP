package com.kspt.portal.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kspt.common.JsonUtil;
import com.kspt.common.dao.daoImp.BaseDaoImp;
import com.kspt.dao.OrgCompanyMapper;
import com.kspt.dao.OrgDepartmentMapper;
import com.kspt.dao.OrgRoleMapper;
import com.kspt.dao.OrgUserMapper;
import com.kspt.model.OrgCompany;
import com.kspt.model.OrgDepartment;
import com.kspt.model.OrgRole;
import com.kspt.model.OrgUser;

@RequestMapping("/portal/org")
@Controller
public class MainAction {
	@Resource
	private BaseDaoImp baseDao;
	@Resource(name="orgCompanyMapper")
	private OrgCompanyMapper orgCompanyMapper;
	@Resource(name="orgDepartmentMapper")
	private OrgDepartmentMapper orgDepartmentMapper;
	@Resource
	private OrgRoleMapper orgRoleMapper;
	@Resource
	private OrgUserMapper orgUserMapper;
	@RequestMapping(method={RequestMethod.POST,RequestMethod.GET})
	public String getMainPage(){
		return "renyuanjiegou/renyuan_main";
	}
	@RequestMapping(value="/treeJson",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String getTreeJson(String node){
		Map<String, Object>map=new HashMap<String, Object>();
		List<Map<String, Object>> listm=new ArrayList<Map<String,Object>>();
		if(node.equals("root")){
			Map<String, Object> nodeMap=new HashMap<String, Object>();
			nodeMap.put("id", "org");
			nodeMap.put("text", "组织结构");
			nodeMap.put("cls", "treeNodeOrg");
			nodeMap.put("expanded", true);
			nodeMap.put("leaf", false);
			listm.add(nodeMap);
			Map<String, Object> nodeMap2=new HashMap<String, Object>();
			nodeMap2.put("id", "role");
			nodeMap2.put("text", "岗位角色");
			nodeMap2.put("cls", "treeNodeRole");
			nodeMap.put("leaf", false);
			listm.add(nodeMap2);
		}else if(node.equals("org")){
			List<OrgCompany>lc=orgCompanyMapper.selectBySelective(new OrgCompany());
			for (OrgCompany companyModel : lc) {
				Map<String, Object> nodeMap=new HashMap<String, Object>();
				nodeMap.put("id", "comp_"+companyModel.getId());
				nodeMap.put("text",companyModel.getCoName());
				nodeMap.put("cls", "treeNodeComp");
				nodeMap.put("leaf", false);
				listm.add(nodeMap);
			}
		}else if(node.startsWith("comp_")){
			OrgDepartment orgDepartment=new OrgDepartment();
			orgDepartment.setCoId(node.replace("comp_", ""));
			List<OrgDepartment>lc=orgDepartmentMapper.selectBySelective(orgDepartment);
			for (OrgDepartment departmentModel : lc) {
				Map<String, Object> nodeMap=new HashMap<String, Object>();
				nodeMap.put("id", "dept_"+departmentModel.getId());
				nodeMap.put("text",departmentModel.getDeptName());
				nodeMap.put("cls", "treeNodeDept");
				nodeMap.put("leaf", false);
				listm.add(nodeMap);
			}
			OrgUser orgUser=new OrgUser(); 
			orgUser.setCompId(node.replace("comp_", ""));
			List<OrgUser>lu=orgUserMapper.selectBySelective(orgUser);
			for (OrgUser userModel : lu) {
				Map<String, Object> nodeMap=new HashMap<String, Object>();
				nodeMap.put("id", "user_"+userModel.getId());
				nodeMap.put("text",userModel.getUserName());
				nodeMap.put("cls", "treeNodeUser");
				nodeMap.put("leaf", true);
				listm.add(nodeMap);
			}
		}else if(node.startsWith("dept_")){
			OrgDepartment orgDepartment=new OrgDepartment();
			orgDepartment.setDeptParentid(node.replace("dept_", ""));
			List<OrgDepartment>lc=orgDepartmentMapper.selectBySelective(orgDepartment);
			for (OrgDepartment departmentModel : lc) {
				Map<String, Object> nodeMap=new HashMap<String, Object>();
				nodeMap.put("id", "dept_"+departmentModel.getId());
				nodeMap.put("text",departmentModel.getDeptName());
				nodeMap.put("cls", "treeNodeDept");
				nodeMap.put("leaf", false);
				listm.add(nodeMap);
			}
			OrgUser orgUser=new OrgUser();
			orgUser.setDeptId(node.replace("dept_", ""));
			List<OrgUser>lu=orgUserMapper.selectBySelective(orgUser);
			for (OrgUser userModel : lu) {
				Map<String, Object> nodeMap=new HashMap<String, Object>();
				nodeMap.put("id", "user_"+userModel.getId());
				nodeMap.put("text",userModel.getUserName());
				nodeMap.put("cls", "treeNodeUser");
				nodeMap.put("leaf", true);
				listm.add(nodeMap);
			}
		}else if(node.equals("role")){
			List<OrgRole>lr=orgRoleMapper.selectAllGroup();
			for (OrgRole roleModel : lr) {
				Map<String, Object> nodeMap=new HashMap<String, Object>();
				nodeMap.put("id", "rolegroup_"+roleModel.getRoleGroup());
				nodeMap.put("text",roleModel.getRoleGroup());
				nodeMap.put("cls", "treeNodeRole");
				nodeMap.put("leaf", false);
				listm.add(nodeMap);
			}
		}
		else if(node.startsWith("rolegroup_")){
			OrgRole orgRole=new OrgRole();
			orgRole.setRoleGroup(node.replace("rolegroup_", ""));
			List<OrgRole>lr=orgRoleMapper.selectBySelective(orgRole);
			for (OrgRole roleModel : lr) {
				Map<String, Object> nodeMap=new HashMap<String, Object>();
				nodeMap.put("id", "role_"+roleModel.getId());
				nodeMap.put("text",roleModel.getRoleName());
				nodeMap.put("cls", "treeNodeRoleMan");
				nodeMap.put("leaf", true);
				listm.add(nodeMap);
			}
		}
		
		map.put("root", listm);
		return JsonUtil.toJSONString(map);
	}
	
}
