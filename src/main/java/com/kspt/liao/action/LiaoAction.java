package com.kspt.liao.action;

import java.net.SocketException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kspt.common.JsonUtil;
import com.kspt.liao.msgData.MesgData;
import com.kspt.liao.pojo.UserMsg;
import com.kspt.model.OrgUser;
import com.kspt.portal.login.OnlineConstants;
import com.kspt.util.DataConnomImpl;

@Controller
@RequestMapping("/zq/liao")
public class LiaoAction {
	@Resource 
	private DataConnomImpl connomImpl;
	@RequestMapping(value="/getUsers",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String getLiaoUser(){
		
		Map<String, OrgUser>user_maps=OnlineConstants.getUser();
		List<Map<String, Object>> liao_user_map=new ArrayList<Map<String,Object>>();
		for (Entry<String, OrgUser> user_map : user_maps.entrySet()) {
			OrgUser u=user_map.getValue();
			Map<String, Object>user=new HashMap<String, Object>();
			user.put("user_account", u.getUserAccount());
			user.put("user_name", u.getUserName());
			user.put("user_head", u.getUserName());
			liao_user_map.add(user);
		}
		return JsonUtil.toJSONString(liao_user_map);
	}
	
	@RequestMapping(value="/saveMsg",method={RequestMethod.POST,RequestMethod.GET})
	public synchronized @ResponseBody String sendMsg(String msg,HttpSession session){
		OrgUser user_model=(OrgUser) session.getAttribute("user_model");
		UserMsg u=new UserMsg(user_model.getUserName(),user_model.getUserAccount(),msg,new Date(),connomImpl.getUUID());
		MesgData.LUM.add(u);
	
		if(MesgData.LUM.size()==100){
			MesgData.LUM.remove(0);
		}
		return "ok";
	
	}
	
	@RequestMapping(value="/getMsg",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String getMsg(int timeout) throws SocketException{
			
		
		long l=timeout*1000;
		long nowtime=new Date().getTime();
		while(true){
			if(new Date().getTime()-nowtime>l){
				return "1";
			}
			LinkedList<UserMsg> lums= MesgData.LUM;
			if(lums.size()==0){
				continue;
			}
			if(!lums.getLast().getUuid().equals(MesgData.LAST_MSG_ID)){
				MesgData.LAST_MSG_ID=lums.getLast().getUuid();
					List<UserMsg> lums2=MesgData.LUM;
					return JsonUtil.toJSONString(lums2);
			}else{
				continue;
			}
		}
		
	
	}
}
