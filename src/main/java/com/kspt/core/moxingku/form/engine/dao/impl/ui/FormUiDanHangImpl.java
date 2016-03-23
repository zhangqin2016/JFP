package com.kspt.core.moxingku.form.engine.dao.impl.ui;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.alibaba.fastjson.JSONObject;
import com.kspt.core.moxingku.form.engine.dao.impl.FormUiDaoImpl;
import com.kspt.core.moxingku.form.pojo.FormUIModel;

@Service("UI_文本")
public class FormUiDanHangImpl extends FormUiDaoImpl{
	
	public String getUiEditPage(ModelMap modelMap) {
		FormUIModel formUIModel= super.getFormUIModel();
	JSONObject jsonObject=	JSONObject.parseObject(formUIModel.getUi_param());
	String type="";
	if(jsonObject!=null){
		type=jsonObject.getString("type");
	}
		modelMap.put("type", type);
		modelMap.put("ui_html", formUIModel.getUi_html());
		modelMap.put("ui_length", formUIModel.getUi_length());
		return "moxingku/formmoxing/ui/form_ui_edit_danhang";
	}

	public String getEditableUi() {
		FormUIModel formUIModel= super.getFormUIModel();
		//查看字段类型
		boolean is_null=formUIModel.isIs_null();
		String display=formUIModel.getIs_display();
		JSONObject jsonObject=	JSONObject.parseObject(formUIModel.getUi_param());
		String checkedtype="";
		if(jsonObject!=null){
			checkedtype=jsonObject.getString("type");
		}
		String checked="";
		String type=" type=\"text\" ";
		if(!is_null){
			if(checkedtype!=null&&checkedtype.length()>0){
			checked="check-type='required "+checkedtype+"'";
			}else{
				checked="check-type='required'";
			}
		}else{
			checked="check-type='"+checkedtype+"'";
		}
		if(display.equals("隐藏")){
			type=" type=\"hidden\" ";
		}
		//check-type="required"
		String uiHtml="<input class=\"form-control\" "+type+" "+checked+" name= \""+formUIModel.getUi_name()+"\"  id= \""+formUIModel.getUi_name()+"\" value=\""+super.getValue()+"\"/>";
		return uiHtml;
	}

	public String getReadOnlyUi() {
		String uiHtml="<label>"+formUIModel.getUi_title()+"</label><input type='hidden' name= '"+formUIModel.getUi_name()+"'  id= '"+formUIModel.getUi_name()+"'  value='"+super.getValue()+"'/>";
		return uiHtml;
	}
}
