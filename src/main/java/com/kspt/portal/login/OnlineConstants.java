package com.kspt.portal.login;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;

import com.kspt.model.OrgUser;

public class OnlineConstants { 
	//索引所有的Session 
	public static Map<String, HttpSession> SESSION_MAP =  new HashMap<String, HttpSession>(); 
	public static Map<String,OrgUser> SESSION_USERMODEL =  getUser();
	public static Map<String,OrgUser> getUser(){
		Map<String,OrgUser> map=new HashMap<String, OrgUser>(); 
		for (Entry<String, HttpSession> session : SESSION_MAP.entrySet()) {
			if(session.getValue().getAttribute("user_model")!=null){
				OrgUser u=(OrgUser) session.getValue().getAttribute("user_model");
				map.put(u.getUserAccount(), u);
			}
		}
		return map;
		
	}
} 
