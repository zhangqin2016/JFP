package com.kspt.portal.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kspt.common.JsonUtil;
import com.kspt.common.dao.daoImp.BaseDaoImp;
import com.kspt.dao.OrgRoleMapper;
import com.kspt.dao.OrgUserMapper;
import com.kspt.model.OrgRole;
import com.kspt.model.OrgUser;
import com.kspt.util.DataConnomImpl;

@RequestMapping("/portal/org/role")

@Controller
public class RoleAction {
	@Resource
	private BaseDaoImp baseDao;
	@Resource 
	private DataConnomImpl connomImpl;
	@Resource
	private OrgRoleMapper orgRoleMapper;
	@Resource
	private OrgUserMapper orgUserMapper;
	@RequestMapping(value="/addRolePage",method={RequestMethod.POST,RequestMethod.GET})
	public String addRolePage(String role_group,ModelMap map){
		if(role_group==null){
			map.put("role_group", "请选择...");
		}else{
		map.put("role_group", role_group);
		}
		return "renyuanjiegou/renyuan_roleadd";
	}
	@RequestMapping(method={RequestMethod.POST,RequestMethod.GET})
	public String getrenyuan_rolegrid(String role_group,ModelMap map){
		map.put("role_group", role_group);
		return "renyuanjiegou/renyuan_rolegrid";
	}
	@RequestMapping(value="/rolegroup",method={RequestMethod.POST,RequestMethod.GET})
	public String getrenyuan_rolegroupgrid(){
		
		return "renyuanjiegou/renyuan_rolegroupgrid";
	}
	@RequestMapping(value="/gridAllRoleJson",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String gridAllRoleJson(){
		List<OrgRole>lc=orgRoleMapper.selectBySelective(new OrgRole());
		return JsonUtil.toJSONString(lc);
	}
	@RequestMapping(value="/gridGroupJson",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String gridGroupJson(){
		Map<String, Object>map=new HashMap<String, Object>();
		List<OrgRole>lc=orgRoleMapper.selectAllGroup();
		map.put("root", lc);
		map.put("totalProperty", lc.size());
		return JsonUtil.toJSONString(map);
	}
	@RequestMapping(value="/gridJson",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String gridJson(String role_group){
		Map<String, Object>map=new HashMap<String, Object>();
		OrgRole orgRole=new OrgRole();
		orgRole.setRoleGroup(role_group);
		List<OrgRole>lc=orgRoleMapper.selectBySelective(orgRole);
		map.put("root", lc);
		map.put("totalProperty", lc.size());
		return JsonUtil.toJSONString(map);
	}
	
	@Transactional
	@RequestMapping(value="/deleteRoleById",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String deleterole(String[] ids){
		
		for (String id : ids) {
			OrgUser orgUser=new OrgUser();
			orgUser.setRoleId(id);
			List<OrgUser>ls=orgUserMapper.selectBySelective(orgUser);
			OrgRole model=orgRoleMapper.selectByPrimaryKey(id);
			if(ls.size()>0){
				return "角色"+model.getRoleName()+"被"+ls.get(0).getUserName()+"引用不允许删除!";
			}else{
				orgRoleMapper.deleteByPrimaryKey(id);
			}
		}
		return "删除操作执行完毕!";
		
	}
	
	@Transactional
	@RequestMapping(value="/addRole",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String addRole(OrgRole roleModel){
		if(roleModel.getId().length()==0){
			roleModel.setOrderIndex(Long.parseLong(connomImpl.getSequence("zq_org_role")+""));
			roleModel.setId(connomImpl.getUUID());
			orgRoleMapper.insertSelective(roleModel);
			 return "添加成功!";
		}else{
			orgRoleMapper.updateByPrimaryKey(roleModel);
			 return "更新成功!";
		}
	}
}
