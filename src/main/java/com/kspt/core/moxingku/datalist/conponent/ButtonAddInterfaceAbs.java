package com.kspt.core.moxingku.datalist.conponent;

import javax.annotation.Resource;

import com.kspt.core.moxingku.datalist.conponent.cache.ButtonConponentCache;
import com.kspt.core.moxingku.datalist.conponent.pojo.ButtonComponent;

public abstract  class ButtonAddInterfaceAbs implements ButtonAddInterface {
	
	
	
	@Resource
	ButtonConponentCache buttoncache;
	public void toAddButton(Class<?>clazz) {
		ButtonComponent button=new ButtonComponent();
		button.setId(clazz.getName());
		button.setButtonName(getButtonName());
		button.setImpClass(clazz.getName());
		button.setButtonGroup(getButtonGroup());
		button.setButtonPage(getButtonWeb());
		buttoncache.put(button);
	}
}
