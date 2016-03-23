package com.kspt.core.moxingku.table.pojo;


public class MetadataModel {
	private String id;
	private String table_name;
	private String table_title;
	private String table_type;
	private String model_lib_id;
	private String master;
	private int order_index;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTable_name() {
		return table_name;
	}
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
	public String getTable_title() {
		return table_title;
	}
	public void setTable_title(String table_title) {
		this.table_title = table_title;
	}
	public String getTable_type() {
		return table_type;
	}
	public void setTable_type(String table_type) {
		this.table_type = table_type;
	}
	public String getModel_lib_id() {
		return model_lib_id;
	}
	public void setModel_lib_id(String model_lib_id) {
		this.model_lib_id = model_lib_id;
	}
	public String getMaster() {
		return master;
	}
	public void setMaster(String master) {
		this.master = master;
	}
	public int getOrder_index() {
		return order_index;
	}
	public void setOrder_index(int order_index) {
		this.order_index = order_index;
	}

}
