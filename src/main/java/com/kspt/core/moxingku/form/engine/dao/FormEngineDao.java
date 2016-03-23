package com.kspt.core.moxingku.form.engine.dao;

import com.kspt.core.moxingku.form.engine.dao.impl.FormUiDaoImpl;

public interface FormEngineDao {
	
	/**
	 * 获取表单UI的模型
	 * 
	 * @param componentId
	 *            模型ID
	 * @return
	 */
	public  FormUiDaoImpl getFormUIComponent(String componentId);

}
