package com.kspt.core.moxingku.form.engine.dao.impl;

import org.springframework.stereotype.Service;

import com.kspt.common.SpringContextUtil;
import com.kspt.core.moxingku.form.engine.dao.FormEngineDao;

@Service("FormEngineDaoImpl")
public  class FormEngineDaoImpl implements FormEngineDao{

	public FormUiDaoImpl getFormUIComponent(String componentId) {
		return (FormUiDaoImpl) SpringContextUtil.getBean(componentId);
	}



}
