package com.kspt.core.moxingku.form.engine.dao;

import org.springframework.ui.ModelMap;

import com.kspt.core.moxingku.form.engine.dao.impl.FormUiDaoImpl;

public interface FormUiDao {

	public String getUiEditPage( ModelMap modelMap);
	public String getEditableUi();
	public String getReadOnlyUi();
	public String getJavaScriptValue();
	
}
