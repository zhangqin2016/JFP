package com.kspt.core.moxingku.form.engine.dao.impl;

import javax.servlet.http.HttpServletRequest;

import com.kspt.core.moxingku.form.engine.dao.FormUiDao;
import com.kspt.core.moxingku.form.pojo.FormUIModel;

/**
 * 处理ui组建
 * @author Administrator
 *
 */
public abstract class FormUiDaoImpl implements FormUiDao{
	
	protected FormUIModel formUIModel;
	protected HttpServletRequest request;
	public FormUIModel getFormUIModel() {
		return formUIModel;
	}

	public void setFormUIModel(FormUIModel formUIModel) {
		this.formUIModel = formUIModel;
	}

	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public String getJavaScriptValue(){
		return "formObject."+getFormUIModel().getUi_name()+"=(zq_form."+getFormUIModel().getUi_name()+".value==''?null:zq_form."+getFormUIModel().getUi_name()+".value);";
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
}
