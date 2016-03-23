package com.kspt.core.moxingku.form.engine.dao.impl.ui;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kspt.core.moxingku.form.engine.dao.impl.FormUiDaoImpl;
import com.kspt.core.moxingku.form.pojo.FormUIModel;

@Service("UI_日期")
public class FormUiRiQimpl extends FormUiDaoImpl{
	
	public String getUiEditPage(ModelMap modelMap) {
		FormUIModel formUIModel= super.getFormUIModel();
		modelMap.put("ui_html", formUIModel.getUi_html());
		modelMap.put("ui_length", formUIModel.getUi_length());
			JSONObject jsonObject=JSONObject.parseObject(formUIModel.getUi_param());
			String param="yyyy-MM-dd";
			if(jsonObject!=null){
				param=jsonObject.getString("param")==null?"yyyy-MM-dd":jsonObject.getString("param");
			}
			 modelMap.put("riqi", param);
		return "moxingku/formmoxing/ui/form_ui_edit_riqi";
	}

	public String getEditableUi() {
		FormUIModel formUIModel= super.getFormUIModel();
		//查看字段类型
		boolean is_null=formUIModel.isIs_null();
		String display=formUIModel.getIs_display();
		String checked="";
		String type=" type=\"text\" ";
		if(!is_null){
			checked="check-type='required'";
		}
		if(display.equals("不显示")){
			type=" type=\"hidden\" ";
		}
		JSONObject jsonObject=JSONObject.parseObject(formUIModel.getUi_param());
		String param="yyyy-MM-dd";
		if(jsonObject!=null){
			param=jsonObject.getString("param")==null?"yyyy-MM-dd":jsonObject.getString("param");
		}
		
		//check-type="required"
		String uiHtml="<input  class=\"form-control\" "+type+" onfocus=\"WdatePicker({dateFmt:'"+param+"'})\" "+checked+" name= \""+formUIModel.getUi_name()+"\"  id= \""+formUIModel.getUi_name()+"\" value=\""+super.getValue()+"\"/>";
		return uiHtml;
	}

	public String getReadOnlyUi() {
		String uiHtml="<label>"+formUIModel.getUi_title()+"</label><input type='hidden' name= '"+formUIModel.getUi_name()+"'  id= '"+formUIModel.getUi_name()+"'  value='"+super.getValue()+"'/>";
		return uiHtml;
	}
}
