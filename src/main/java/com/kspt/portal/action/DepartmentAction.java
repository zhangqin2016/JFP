package com.kspt.portal.action;

import java.util.ArrayList;
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
import com.kspt.dao.OrgDepartmentMapper;
import com.kspt.dao.OrgUserMapper;
import com.kspt.model.OrgDepartment;
import com.kspt.model.OrgUser;
import com.kspt.util.DataConnomImpl;

@RequestMapping("/portal/org/dept")

@Controller
public class DepartmentAction {
	@Resource
	private BaseDaoImp baseDao;
	@Resource 
	private DataConnomImpl connomImpl;
	@Resource(name="orgDepartmentMapper")
	private OrgDepartmentMapper orgDepartmentMapper;
	@Resource(name="orgUserMapper")
	private OrgUserMapper orgUserMapper;
	@RequestMapping(method={RequestMethod.POST,RequestMethod.GET})
	public String getrenyuan_deptgrid(String type,String id,ModelMap map){
		map.put("type", type);
		map.put("id", id);
		return "renyuanjiegou/renyuan_deptgrid";
	}
	@RequestMapping(value="/gridJson",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String getDeptGridJson(String id,String type){
		
		Map<String, Object>map=new HashMap<String, Object>();
		List<OrgDepartment>ld=new ArrayList<OrgDepartment>();
		List<OrgUser>lu=new ArrayList<OrgUser>();
		if(type.equals("comp")){
			OrgDepartment orgDepartment=new OrgDepartment();
			orgDepartment.setCoId(id.replace("comp_", ""));
			ld=orgDepartmentMapper.selectBySelective(orgDepartment);
			OrgUser orgUser=new OrgUser();
			orgUser.setCompId(id.replace("comp_", ""));
			lu=orgUserMapper.selectBySelective(orgUser);
		}else{
			OrgDepartment orgDepartment=new OrgDepartment();
			orgDepartment.setDeptParentid(id.replace("dept_", ""));
			ld=orgDepartmentMapper.selectBySelective(orgDepartment);
			OrgUser orgUser=new OrgUser();
			orgUser.setDeptId(id.replace("dept_", ""));
			lu=orgUserMapper.selectBySelective(orgUser);
		}
	for (OrgUser  userModel : lu) {
		OrgDepartment departmentModel=new OrgDepartment();
		departmentModel.setId("user_"+userModel.getId());
		departmentModel.setDeptName(userModel.getUserName());
		departmentModel.setOrderIndex(userModel.getOrderIndex());
		ld.add(departmentModel);
	}
		map.put("root", ld);
		map.put("totalProperty", ld.size());
		return JsonUtil.toJSONString(map);
	}
	@RequestMapping(value="/editPage",method={RequestMethod.POST,RequestMethod.GET})
	public String editPage(String type,String id,ModelMap map){
		map.put("type", type);
		map.put("id", id);
		OrgDepartment orgDepartment= orgDepartmentMapper.selectByPrimaryKey(id);
		map.put("dept_code", orgDepartment.getDeptCode());
		map.put("dept_name", orgDepartment.getDeptName());
		return "renyuanjiegou/renyuan_deptadd";
	}
	@RequestMapping(value="/addPage",method={RequestMethod.POST,RequestMethod.GET})
	public String addPage(String type,String id,ModelMap map){
		map.put("type", type);
		map.put("id", id);
		return "renyuanjiegou/renyuan_deptadd";
	}
	
	@Transactional
	@RequestMapping(value="/addDept",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String addDept(OrgDepartment deptModel,String id,String type){
		if(id.indexOf("_")==-1){
			orgDepartmentMapper.updateByPrimaryKeySelective(deptModel);
			return "更新成功";
		}else{
		deptModel.setId(connomImpl.getUUID());
		deptModel.setOrderIndex(Long.parseLong(connomImpl.getSequence("zq_org_dept")+""));
		if(type.equals("comp")){
			deptModel.setCoId(id.replace("comp_", ""));
		}else{
			deptModel.setDeptParentid(id.replace("dept_", ""));
		}
		orgDepartmentMapper.insert(deptModel);
		return "添加成功";
		}
		
	}
	
	@Transactional
	@RequestMapping(value="/updateDept",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String updateDept(OrgDepartment deptModel){
		deptModel.setId(connomImpl.getUUID());
		deptModel.setOrderIndex(Long.parseLong(connomImpl.getSequence("zq_org_dept")+""));
		orgDepartmentMapper.updateByPrimaryKeySelective(deptModel);
		return "更新成功";
	}
	
	
	@Transactional
	@RequestMapping(value="/delDept",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String delDept(String ids[],String leixing){
		if(ids==null||ids.length==0){
			return "无符合的数据来进行删除!";
		}
		if(leixing.equals("user")){
			for (String id : ids) {
				orgDepartmentMapper.deleteByPrimaryKey(id);
			}
			return "操作用户成功！";
		}else{
		for (String id : ids) {
			OrgDepartment orgDepartment=new OrgDepartment();
			orgDepartment.setDeptParentid(id);
			List<OrgDepartment>list=orgDepartmentMapper.selectBySelective(orgDepartment);
			int count=list.size();
			if(count==0){
				orgDepartmentMapper.deleteByPrimaryKey(id);
			}
		}
		return "操作成功！如有未删除请清除部门下的部门和人员再进行删除！";
		}
		
	}
}
