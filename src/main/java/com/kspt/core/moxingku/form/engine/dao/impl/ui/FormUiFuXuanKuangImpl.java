package com.kspt.core.moxingku.form.engine.dao.impl.ui;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kspt.core.moxingku.form.engine.dao.impl.FormUiDaoImpl;
import com.kspt.core.moxingku.form.pojo.FormUIModel;

@Service("UI_复选框")
public class FormUiFuXuanKuangImpl extends FormUiDaoImpl{
	
	public String getUiEditPage(ModelMap modelMap) {
		FormUIModel formUIModel= super.getFormUIModel();
		modelMap.put("ui_html", formUIModel.getUi_html());
		modelMap.put("ui_length", formUIModel.getUi_length());
		 JSONObject fuxuankuang=JSON.parseObject(formUIModel.getUi_param());
		 if(fuxuankuang!=null)
		modelMap.put("fuxuankuang", fuxuankuang.getString("fuxuankuang"));
		return "moxingku/formmoxing/ui/form_ui_edit_fuxuankuang";
	}

	public String getEditableUi() {
		String uiHtml="<input  class=\"form-control\" type='text' name= '"+formUIModel.getUi_name()+"'  id= '"+formUIModel.getUi_name()+"' size='"+formUIModel.getUi_length()+"' value="+super.getValue()+"";
		return uiHtml;
	}

	public String getReadOnlyUi() {
		String uiHtml="<label>"+super.getValue()+"</label><input type='hidden' name= '"+formUIModel.getUi_name()+"'  id= '"+formUIModel.getUi_name()+"' size='"+formUIModel.getUi_length()+"' value="+super.getValue()+"";
		return uiHtml;
	}

 	
}
