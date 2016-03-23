package com.kspt.core.moxingku.form.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kspt.common.dao.BaseDao;
import com.kspt.core.moxingku.form.pojo.FormUIModel;

@Controller
@RequestMapping("/moxingku/form/ui/window")
public class FormGridUi {
	@Resource
 private BaseDao baseDao;
	@RequestMapping(value="/save",method={RequestMethod.POST})
	public @ResponseBody String  save(String ui_length,String ui_html,String ui_param,String id,String ui_type){
		FormUIModel formUIModel=(FormUIModel) baseDao.queryOne("zq_form_ui.get", id);
		formUIModel.setId(id);
		formUIModel.setUi_html(ui_html);
		formUIModel.setUi_length(ui_length);
		formUIModel.setUi_param(ui_param);
		formUIModel.setUi_type(ui_type);
		baseDao.update("zq_form_ui.update", formUIModel);
		return "操作成功!";
		
	}
}
