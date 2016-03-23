package com.kspt.portal.login;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kspt.common.dao.daoImp.BaseDaoImp;
@Controller
@RequestMapping("/console/logout")
public class LoginOutAction {
	@Resource
	private BaseDaoImp baseDao;
	@RequestMapping(method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String doLogin(HttpServletRequest request) {
		request.getSession().invalidate();
			String user_url =request.getScheme()+"://"+request.getHeader("host")+request.getContextPath()+"/console";
			return user_url;
	}
}
