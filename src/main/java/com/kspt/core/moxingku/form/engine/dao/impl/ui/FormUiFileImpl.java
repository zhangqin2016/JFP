package com.kspt.core.moxingku.form.engine.dao.impl.ui;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.kspt.core.moxingku.form.engine.dao.impl.FormUiDaoImpl;
import com.kspt.core.moxingku.form.pojo.FormUIModel;

@Service("UI_附件")
public class FormUiFileImpl extends FormUiDaoImpl{
	
	public String getUiEditPage(ModelMap modelMap) {
		FormUIModel formUIModel= super.getFormUIModel();
		modelMap.put("ui_html", formUIModel.getUi_html());
		modelMap.put("ui_length", formUIModel.getUi_length());
		return "moxingku/formmoxing/ui/form_ui_edit_fujian";
	}

	public String getEditableUi() {
		StringBuffer uiHtml=new StringBuffer("<input type='hidden' name= '"+formUIModel.getUi_name()+"'  id= '"+formUIModel.getUi_name()+"' value='"+super.getValue()+"'><input  class=\"form-control\" type='file' name= '"+formUIModel.getUi_name()+"_file'  id= '"+formUIModel.getUi_name()+"_file'><a href=\"javascript:$('#"+formUIModel.getUi_name()+"_file').uploadify('upload','*')\">开始上传</a>");
		uiHtml.append("<script type=\"text/javascript\"> \r\n");
		uiHtml.append("	$(function() { \r\n");
	uiHtml.append("	    $(\"#"+formUIModel.getUi_name()+"_file\").uploadify({                 \r\n"); 
	uiHtml.append("	        'auto'     : false,                       \r\n"); 
	uiHtml.append("	        'swf'      : '"+super.request.getContextPath()+"/chajian/uploadify/uploadify.swf',  \r\n"); 
	uiHtml.append("	        'uploader' : '"+super.request.getContextPath()+"/run/form/upload.zq' ,  \r\n"); 
	uiHtml.append("	 'fileObjName' : 'file', \r\n"); 
	uiHtml.append("	   'buttonText' : '请选择文件', \r\n"); 
	uiHtml.append("	'onUploadSuccess' : function(file, data, response) {\r\n"); 
	uiHtml.append("		$(\"#"+formUIModel.getUi_name()+"_file\").after(jQuery.parseJSON(data).html); \r\n"); 
	uiHtml.append("		$(\"#"+formUIModel.getUi_name()+"\").val(jQuery.parseJSON(data).value); \r\n"); 
	uiHtml.append("");
	uiHtml.append("      }  \r\n"); 
	uiHtml.append("	    });                                           \r\n"); 
	uiHtml.append("	});                                                \r\n"); 
	uiHtml.append("</script>  \r\n");    	
	return uiHtml.toString();
	}

	public String getReadOnlyUi() {
		String uiHtml="<label>"+super.getValue()+"</label><input type='hidden' name= '"+formUIModel.getUi_name()+"'  id= '"+formUIModel.getUi_name()+"' size='"+formUIModel.getUi_length()+"' value="+super.getValue()+"";
		return uiHtml;
	}

 	
}
