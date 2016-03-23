package com.kspt.core.moxingku.datalist.pojo;


public class DataListQuery {

	private String fieldName;
	private String title;
	private String where;
	private String uiType;
	private String id;
	private String uiParam;
	private int orderIndex;
	private boolean is_main;
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getQueryField() {
		
		return fieldName.substring(fieldName.indexOf("(")+1, fieldName.indexOf(")"));
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWhere() {
		return where;
	}
	public void setWhere(String where) {
		this.where = where;
	}
	public String getUiType() {
		return uiType;
	}
	public void setUiType(String uiType) {
		this.uiType = uiType;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUiParam() {
		return uiParam;
	}
	public void setUiParam(String uiParam) {
		this.uiParam = uiParam;
	}
	public int getOrderIndex() {
		return orderIndex;
	}
	public void setOrderIndex(int orderIndex) {
		this.orderIndex = orderIndex;
	}
	public boolean isIs_main() {
		return is_main;
	}
	public void setIs_main(boolean is_main) {
		this.is_main = is_main;
	}
	public DataListQuery(String fieldName, String title, String where,
			String uiType, String id, String uiParam, int orderIndex,
			boolean is_main) {
		super();
		this.fieldName = fieldName;
		this.title = title;
		this.where = where;
		this.uiType = uiType;
		this.id = id;
		this.uiParam = uiParam;
		this.orderIndex = orderIndex;
		this.is_main = is_main;
	}
	public DataListQuery() {
	}
}
