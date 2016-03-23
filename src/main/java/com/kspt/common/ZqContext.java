package com.kspt.common;

import javax.servlet.http.HttpServletRequest;

import com.kspt.model.OrgUser;

public class ZqContext {

	public static OrgUser getUserModel(HttpServletRequest request){
		return (OrgUser) request.getSession().getAttribute("user_model");
	}
}
