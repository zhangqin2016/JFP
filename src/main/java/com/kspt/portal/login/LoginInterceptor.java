package com.kspt.portal.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String url = request.getRequestURL().toString();
		if(request.getQueryString()!=null){
			url+="?"+request.getQueryString();
		}
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("user_url", url);
		if(request.getRequestURI().startsWith(request.getContextPath()+"/web/")){
				if (request.getRequestURI().startsWith(request.getContextPath()+"/web/exe")) {
					if (httpSession.getAttribute("yhm") == null) {
						response.sendRedirect(request.getContextPath()+"/index.jsp");
						return false;
						}
					return true;
				}else{
					return true;
				}
		}else{
			if (request.getRequestURI().equals(request.getContextPath()+"/console/login.zq")) {
			return true;
			}
			if (httpSession.getAttribute("user_model") == null) {
				response.sendRedirect(request.getContextPath()+"/console/index.jsp");
				return false;
			} else {
				return true;
			}
		}
	}

}
