package com.kspt.portal.navigation.model;

import java.io.Serializable;

public class NavigationThree implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7873201913601405467L;
	/**
	 * 
	 */
	
	private String id;
	
	private String three_name;
	
	private String second_id;
	
	private String three_url;
	
	private String url_target;
	
	private String icon_url;
	
	private int  order_index;
	
	private String first_id;
	
	private String status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getThree_name() {
		return three_name;
	}

	public void setThree_name(String three_name) {
		this.three_name = three_name;
	}

	public String getSecond_id() {
		return second_id;
	}

	public void setSecond_id(String second_id) {
		this.second_id = second_id;
	}

	public String getThree_url() {
		return three_url;
	}

	public void setThree_url(String three_url) {
		this.three_url = three_url;
	}

	public String getUrl_target() {
		return url_target;
	}

	public void setUrl_target(String url_target) {
		this.url_target = url_target;
	}

	public String getIcon_url() {
		return icon_url;
	}

	public void setIcon_url(String icon_url) {
		this.icon_url = icon_url;
	}

	public int getOrder_index() {
		return order_index;
	}

	public void setOrder_index(int order_index) {
		this.order_index = order_index;
	}

	public String getFirst_id() {
		return first_id;
	}

	public void setFirst_id(String first_id) {
		this.first_id = first_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
