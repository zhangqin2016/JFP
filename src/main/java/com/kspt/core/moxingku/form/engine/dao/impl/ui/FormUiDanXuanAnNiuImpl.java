package com.kspt.core.moxingku.form.engine.dao.impl.ui;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kspt.core.moxingku.form.engine.dao.impl.FormUiDaoImpl;
import com.kspt.core.moxingku.form.pojo.FormUIModel;

@Service("UI_单选按钮")
public class FormUiDanXuanAnNiuImpl extends FormUiDaoImpl{
	
	public String getUiEditPage(ModelMap modelMap) {
		FormUIModel formUIModel= super.getFormUIModel();
		modelMap.put("ui_html", formUIModel.getUi_html());
		modelMap.put("ui_length", formUIModel.getUi_length());
		 JSONObject danxuan=JSON.parseObject(formUIModel.getUi_param());
		 if(danxuan!=null){
		modelMap.put("danxuan", danxuan.getString("danxuan"));};
		return "moxingku/formmoxing/ui/form_ui_edit_danxuananniu";
	}

	public String getEditableUi() {
		String uiHtml="<input class=\"form-control\" type='text' name= '"+formUIModel.getUi_name()+"'  id= '"+formUIModel.getUi_name()+"' size='"+formUIModel.getUi_length()+"' value="+super.getValue()+"";
		return uiHtml;
	}

	public String getReadOnlyUi() {
		String uiHtml="<label>"+formUIModel.getUi_title()+"</label><input type='hidden' name= '"+formUIModel.getUi_name()+"'  id= '"+formUIModel.getUi_name()+"' size='"+formUIModel.getUi_length()+"' value="+super.getValue()+"";
		return uiHtml;
	}


 	
}
