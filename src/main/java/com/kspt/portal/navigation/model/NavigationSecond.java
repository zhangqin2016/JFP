package com.kspt.portal.navigation.model;

import java.io.Serializable;

public class NavigationSecond implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7907794343208411057L;

	/**
	 * 
	 */
	private String id;
	
	private String second_name;
	
	private String second_url;
	
	private String url_target;
	
	private String icon_url;
	
	private int order_index;
	
	private String first_id;
	
    private String status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSecond_name() {
		return second_name;
	}

	public void setSecond_name(String second_name) {
		this.second_name = second_name;
	}

	public String getSecond_url() {
		return second_url;
	}

	public void setSecond_url(String second_url) {
		this.second_url = second_url;
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