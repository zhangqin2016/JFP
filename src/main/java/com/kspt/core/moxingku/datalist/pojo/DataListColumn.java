package com.kspt.core.moxingku.datalist.pojo;

import java.io.Serializable;


public class DataListColumn implements  Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3118485729746254323L;
	private String id;
	private String field;
	private String title;
	private boolean is_hidden;
	private int order_index;
	private boolean is_fuzzySearch;
	private HotLink hotLink;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getField() {
		return field;
	}
	
	public String getTableField() {
		
		return field.substring(field.indexOf("(")+1, field.indexOf(")"));
	}
	
	public void setField(String field) {
		this.field = field;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public boolean isIs_hidden() {
		return is_hidden;
	}
	public void setIs_hidden(boolean is_hidden) {
		this.is_hidden = is_hidden;
	}
	public boolean isIs_fuzzySearch() {
		return is_fuzzySearch;
	}
	public void setIs_fuzzySearch(boolean is_fuzzySearch) {
		this.is_fuzzySearch = is_fuzzySearch;
	}
	public int getOrder_index() {
		return order_index;
	}
	public void setOrder_index(int order_index) {
		this.order_index = order_index;
	}
	public HotLink getHotLink() {
		return hotLink;
	}
	public void setHotLink(HotLink hotLink) {
		this.hotLink = hotLink;
	}
	public DataListColumn(String id, String field, String title,
			boolean is_hidden, int order_index, boolean is_fuzzySearch,
			HotLink hotLink) {
		super();
		this.id = id;
		this.field = field;
		this.title = title;
		this.is_hidden = is_hidden;
		this.order_index = order_index;
		this.is_fuzzySearch = is_fuzzySearch;
		this.hotLink = hotLink;
	}
	public DataListColumn() {
	}
	
}
