package com.kspt.liao.pojo;

import java.util.Date;

import com.kspt.common.UtilDate;

public class UserMsg {

	private String user_name;
	private String user_account;
	private String user_msg;
	private Date send_date;
	private String uuid;
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_account() {
		return user_account;
	}
	public void setUser_account(String user_account) {
		this.user_account = user_account;
	}
	public String getSend_date() {
		return UtilDate.datetimeFormat(send_date);
	}
	public void setSend_date(Date send_date) {
		
		this.send_date = send_date;
	}
	public String getUser_msg() {
		return user_msg;
	}
	public void setUser_msg(String user_msg) {
		this.user_msg = user_msg;
	}
	public UserMsg(String user_name, String user_account, String user_msg,
			Date send_date, String uuid) {
		super();
		this.user_name = user_name;
		this.user_account = user_account;
		this.user_msg = user_msg;
		this.send_date = send_date;
		this.uuid = uuid;
	}
	public UserMsg() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
