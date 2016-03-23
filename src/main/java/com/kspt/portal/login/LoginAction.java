package com.kspt.portal.login;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kspt.common.dao.daoImp.BaseDaoImp;
import com.kspt.dao.OrgUserMapper;
import com.kspt.model.OrgUser;
import com.kspt.util.MD5Lock;
@Controller
@RequestMapping("/console/login")
public class LoginAction {
	@Resource
	private BaseDaoImp baseDao;
	@Resource
	private OrgUserMapper orgUserMapper;
	@RequestMapping(method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String doLogin(String user_account,String user_password,HttpServletRequest request) {
		OrgUser userModel=new OrgUser();
     	userModel.setUserAccount(user_account);
		userModel.setUserPassword(MD5Lock.MD5(user_password));
		List<OrgUser>lu=orgUserMapper.selectBySelective(userModel);
		if(lu==null||lu.size()==0){
			return "-1";
		}else{
			request.getSession().setAttribute("user_model",lu.get(0));
			String user_url =request.getScheme()+"://"+request.getHeader("host")+request.getContextPath()+"/console.zq";
		/*	if(request.getSession().getAttribute("user_url")==null){
				user_url=request.getScheme()+"://"+request.getHeader("host")+request.getContextPath()+"/console.zq";
			}else{
				  user_url=request.getSession().getAttribute("user_url").toString();
				  request.getSession().removeAttribute("user_url");
			}*/
	
			return user_url;
		}
	}
}
