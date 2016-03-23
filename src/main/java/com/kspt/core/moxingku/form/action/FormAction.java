package com.kspt.core.moxingku.form.action;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.kspt.common.JsonUtil;
import com.kspt.common.dao.daoImp.BaseDaoImp;
import com.kspt.core.moxingku.form.pojo.FormModel;
import com.kspt.util.DataConnomImpl;

@RequestMapping("/moxingku/form")
@Controller
public class FormAction {
	@Resource
	private BaseDaoImp baseDao;
	@Resource 
	private DataConnomImpl connomImpl;
	@RequestMapping(method={RequestMethod.POST,RequestMethod.GET})
	public String getGrid(String metadata_id,ModelMap map){
		map.put("model_lib_id", metadata_id);
		return "moxingku/formmoxing/form_list";
	}
	@RequestMapping(value="/gridJson",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String getGridJson(String model_lib_id){
		Map<String, Object>map=new HashMap<String, Object>();
		List<FormModel>listModel=(List<FormModel>) baseDao.query("zq_form.getFormByModellibid",model_lib_id);
		map.put("root", listModel);
		map.put("totalProperty", listModel.size());
		return JsonUtil.toJSONString(map);
	}
	
	@RequestMapping(value="/del",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String delete(String []ids){
		StringBuffer returnValue=new StringBuffer("操作完成!");
		for (int i = 0; i < ids.length; i++) {
			try {
				if(validateForm(ids[i])){
					baseDao.delete("zq_form.deleteForm", ids[i]);
				}else{
					returnValue.append("");
				}
			} catch (SQLException e) {
				e.printStackTrace();
					return "删除失败!";
			} 
		}
		return returnValue.toString();
	}
	@RequestMapping(value="/save",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String save(String datas,String model_lib_id){
		List<FormModel> list=(List<FormModel>) JSONArray.parseArray(datas,FormModel.class);
		for (FormModel formModel : list) {
			if(formModel.getId().length()==0){
				formModel.setId(connomImpl.getUUID());
				formModel.setOrder_index(connomImpl.getSequence("zq_form"));
				formModel.setTemplate_name(formModel.getName()+".ftl");
				formModel.setModel_lib_id(model_lib_id);
				formModel.setMaster("");
				baseDao.insert("zq_form.insertForm", formModel);
			}else {
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("id", formModel.getId());
				map.put("name", formModel.getName());
				map.put("model_lib_id",model_lib_id);
				baseDao.update("zq_form.updateForm", map);
			}
		}
		return "操作成功!";
	}
	@RequestMapping(value="/updateFormBase",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String updateFormBase(String form_id,String form_name,String moxingku){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("id",form_id);
		map.put("name",form_name);
		map.put("model_lib_id", moxingku);
		baseDao.update("zq_form.updateForm", map);
		return "操作成功!";
	}
	
	private boolean validateForm(String id) throws SQLException{
		return true;
	}
}