package com.kspt.core.moxingku.datalist.pojo;

public class DataListButton {
	private String id     ;
	private String title  ;
	private String workFlowId;
	private int orderIndex;
	private String tooltip ;
	private String expandParam ;
	private String workListId;
	private String buttonId;
	private String icon;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWorkFlowId() {
		return workFlowId;
	}
	public void setWorkFlowId(String workFlowId) {
		this.workFlowId = workFlowId;
	}
	public int getOrderIndex() {
		return orderIndex;
	}
	public void setOrderIndex(int orderIndex) {
		this.orderIndex = orderIndex;
	}
	public String getTooltip() {
		return tooltip;
	}
	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}
	public String getExpandParam() {
		return expandParam;
	}
	public void setExpandParam(String expandParam) {
		this.expandParam = expandParam;
	}
	public String getWorkListId() {
		return workListId;
	}
	public void setWorkListId(String workListId) {
		this.workListId = workListId;
	}
	public String getButtonId() {
		return buttonId;
	}
	public void setButtonId(String buttonId) {
		this.buttonId = buttonId;
	}
}
