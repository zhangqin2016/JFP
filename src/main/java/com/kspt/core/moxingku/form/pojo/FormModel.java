package com.kspt.core.moxingku.form.pojo;

public class FormModel {
	  private String id;
	  private int type;
	  private String name;
	  private String model_lib_id;
	  private String master;
	  private String template_name;
	  private int order_index;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getTemplate_name() {
		return template_name;
	}
	public void setTemplate_name(String template_name) {
		this.template_name = template_name;
	}
	public int getOrder_index() {
		return order_index;
	}
	public void setOrder_index(int order_index) {
		this.order_index = order_index;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	  
}
