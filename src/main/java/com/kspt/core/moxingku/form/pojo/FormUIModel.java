package com.kspt.core.moxingku.form.pojo;

import java.io.Serializable;


public class FormUIModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 表单模型UI
	 */
	private String id;
	private String ui_length_type;
	private String form_er_id;
	private String metadata_map_id;
	private String ui_name;
	private String ui_title;
	private String default_value;
	private boolean is_null;
	private boolean is_edit;
	private String is_display;
	private String ui_type;
	private String ui_length;
	private String ui_param;
	private String ui_html;
	private int order_index;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUi_length_type() {
		return ui_length_type;
	}
	public void setUi_length_type(String ui_length_type) {
		this.ui_length_type = ui_length_type;
	}
	public String getForm_er_id() {
		return form_er_id;
	}
	public void setForm_er_id(String form_er_id) {
		this.form_er_id = form_er_id;
	}
	public String getMetadata_map_id() {
		return metadata_map_id;
	}
	public void setMetadata_map_id(String metadata_map_id) {
		this.metadata_map_id = metadata_map_id;
	}
	public String getUi_name() {
		return ui_name;
	}
	public void setUi_name(String ui_name) {
		this.ui_name = ui_name;
	}
	public String getUi_title() {
		return ui_title;
	}
	public void setUi_title(String ui_title) {
		this.ui_title = ui_title;
	}
	public String getDefault_value() {
		return default_value;
	}
	public void setDefault_value(String default_value) {
		this.default_value = default_value;
	}
	public boolean isIs_null() {
		return is_null;
	}
	public void setIs_null(boolean is_null) {
		this.is_null = is_null;
	}
	public boolean isIs_edit() {
		return is_edit;
	}
	public void setIs_edit(boolean is_edit) {
		this.is_edit = is_edit;
	}
	public String getIs_display() {
		return is_display;
	}
	public void setIs_display(String is_display) {
		this.is_display = is_display;
	}
	public String getUi_type() {
		return ui_type;
	}
	public void setUi_type(String ui_type) {
		this.ui_type = ui_type;
	}
	public String getUi_length() {
		return ui_length;
	}
	public void setUi_length(String ui_length) {
		this.ui_length = ui_length;
	}
	public String getUi_param() {
		return ui_param;
	}
	public void setUi_param(String ui_param) {
		this.ui_param = ui_param;
	}
	public String getUi_html() {
		return ui_html;
	}
	public void setUi_html(String ui_html) {
		this.ui_html = ui_html;
	}
	public int getOrder_index() {
		return order_index;
	}
	public void setOrder_index(int order_index) {
		this.order_index = order_index;
	}
	
}
