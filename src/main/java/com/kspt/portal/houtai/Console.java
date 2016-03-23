package com.kspt.portal.houtai;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kspt.model.OrgUser;

@RequestMapping("/console")
@Controller
public class Console {

	@RequestMapping( method={RequestMethod.POST,RequestMethod.GET})
	public String getConsoePage(ModelMap map,HttpSession session){
		OrgUser usermodel=(OrgUser) session.getAttribute("user_model");
		map.put("user_name", usermodel.getUserName());
		map.put("user_account", usermodel.getUserAccount());
		return "console/index";
	}
}
