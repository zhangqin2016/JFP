package com.kspt.core.moxingku.form.pojo;

import java.io.Serializable;


public class FormEntityRelationshipModel  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3466581083574698503L;
	/**
	 * 表单模型数据源
	 */
	  private String id;
	    private String form_id;
	    private String metadata_id;
	    private int er_type;
	    private int page_size;
	    private String grid_order;
	    private int er_layer;
	    private String parent_er_id;
	    private int order_index;
	    private int grid_height;
	    private String grid_title;
	    private String table_name;
	    
		public String getTable_name() {
			return table_name;
		}
		public void setTable_name(String table_name) {
			this.table_name = table_name;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getForm_id() {
			return form_id;
		}
		public void setForm_id(String form_id) {
			this.form_id = form_id;
		}
		public String getMetadata_id() {
			return metadata_id;
		}
		public void setMetadata_id(String metadata_id) {
			this.metadata_id = metadata_id;
		}
		public int getEr_type() {
			return er_type;
		}
		public void setEr_type(int er_type) {
			this.er_type = er_type;
		}
		public int getPage_size() {
			return page_size;
		}
		public void setPage_size(int page_size) {
			this.page_size = page_size;
		}
		public String getGrid_order() {
			return grid_order;
		}
		public void setGrid_order(String grid_order) {
			this.grid_order = grid_order;
		}
		public int getEr_layer() {
			return er_layer;
		}
		public void setEr_layer(int er_layer) {
			this.er_layer = er_layer;
		}
		public String getParent_er_id() {
			return parent_er_id;
		}
		public void setParent_er_id(String parent_er_id) {
			this.parent_er_id = parent_er_id;
		}
		public int getOrder_index() {
			return order_index;
		}
		public void setOrder_index(int order_index) {
			this.order_index = order_index;
		}
		public int getGrid_height() {
			return grid_height;
		}
		public void setGrid_height(int grid_height) {
			this.grid_height = grid_height;
		}
		public String getGrid_title() {
			return grid_title;
		}
		public void setGrid_title(String grid_title) {
			this.grid_title = grid_title;
		}
	
}
