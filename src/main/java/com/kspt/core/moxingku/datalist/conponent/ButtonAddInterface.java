package com.kspt.core.moxingku.datalist.conponent;

import com.kspt.core.moxingku.datalist.conponent.pojo.ButtonParamModel;

public interface ButtonAddInterface {

	
	/**
	 * 添加button
	 */
	public void addButton();
	public String getButtonName();
	public String getButtonGroup();
	public String getButtonJavascript(ButtonParamModel buttonParamModel);
	public String getButtonWeb();
}
