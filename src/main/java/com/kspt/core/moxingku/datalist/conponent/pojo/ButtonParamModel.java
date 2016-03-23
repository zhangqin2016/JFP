package com.kspt.core.moxingku.datalist.conponent.pojo;

import javax.servlet.http.HttpServletRequest;

import com.kspt.core.moxingku.datalist.pojo.DataList;

public class ButtonParamModel {

	private DataList dataList;
	private HttpServletRequest httpServletRequest;
	private String params;
	public DataList getDataList() {
		return dataList;
	}
	public void setDataList(DataList dataList) {
		this.dataList = dataList;
	}
	public HttpServletRequest getHttpServletRequest() {
		return httpServletRequest;
	}
	public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
		this.httpServletRequest = httpServletRequest;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	
}
