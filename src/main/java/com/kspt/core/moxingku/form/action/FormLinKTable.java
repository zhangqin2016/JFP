package com.kspt.core.moxingku.form.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kspt.common.dao.daoImp.BaseDaoImp;
import com.kspt.core.moxingku.form.pojo.FormModel;

@RequestMapping("/moxingku/form/linkTable")
@Controller
public class FormLinKTable {
	@Resource
	private BaseDaoImp baseDao;
	
	@RequestMapping(method={RequestMethod.GET})
	public String linkTable(String form_id,ModelMap map){
		
		map.put("form_id", form_id);
		return "moxingku/formmoxing/form_linkTable_main";
	}
	
	@RequestMapping(value="/base",method={RequestMethod.GET})
	public String base(String form_id,ModelMap map){
		map.put("form_id", form_id);
	FormModel form=	(FormModel) baseDao.queryOne("zq_form.getFormById", form_id);
	map.put("form_id", form.getId());
	map.put("form_name", form.getName());
	map.put("moxingkuid", form.getModel_lib_id());
	return "moxingku/formmoxing/form_linkTable_base";
	}
	

	
	@RequestMapping(value="/editForm",method={RequestMethod.GET})
	public String editForm(String form_id,ModelMap map){
		
		map.put("form_id", form_id);
		return "moxingku/formmoxing/form_linkTable_editForm";
	}
}
