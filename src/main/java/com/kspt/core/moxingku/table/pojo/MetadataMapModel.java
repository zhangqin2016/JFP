package com.kspt.core.moxingku.table.pojo;

import java.io.Serializable;
public class MetadataMapModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5184090162882418126L;
	/**
	 * 
	 */
	private String id;
	private String metadata_id;
	private String column_name;
	private String column_title;
	private String column_length;
	private String column_type;
	private int order_index;
	private String default_value;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getColumn_name() {
		return column_name;
	}
	public void setColumn_name(String column_name) {
		this.column_name = column_name;
	}
	public String getMetadata_id() {
		return metadata_id;
	}
	public void setMetadata_id(String metadata_id) {
		this.metadata_id = metadata_id;
	}
	public String getColumn_title() {
		return column_title;
	}
	public void setColumn_title(String column_title) {
		this.column_title = column_title;
	}
	public String getColumn_length() {
		return column_length;
	}
	public void setColumn_length(String column_length) {
		this.column_length = column_length;
	}
	public String getColumn_type() {
		return column_type;
	}
	public void setColumn_type(String column_type) {
		this.column_type = column_type;
	}
	public int getOrder_index() {
		return order_index;
	}
	public void setOrder_index(int order_index) {
		this.order_index = order_index;
	}
	public String getDefault_value() {
		return default_value;
	}
	public void setDefault_value(String default_value) {
		this.default_value = default_value;
	}

}
