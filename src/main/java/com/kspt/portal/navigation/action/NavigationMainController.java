package com.kspt.portal.navigation.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kspt.common.JsonUtil;
import com.kspt.common.dao.daoImp.BaseDaoImp;
import com.kspt.dao.NavFirstMapper;
import com.kspt.dao.NavSecondMapper;
import com.kspt.dao.NavThreeMapper;
import com.kspt.model.NavFirst;
import com.kspt.model.NavSecond;
import com.kspt.model.NavThree;

@RequestMapping("/portal/navication")
@Controller
public class NavigationMainController {
	@Resource
	private BaseDaoImp baseDao;
	@Resource 
	private NavFirstMapper navFirstMapper;
	@Resource 
	private NavSecondMapper navSecondMapper;
	@Resource 
	private NavThreeMapper navThreeMapper;
	
	@RequestMapping
	public String getMainPage(){
		
		return "daohang/navigation_main";
	}
	
	@RequestMapping(value="/treeJson",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String getMainTree(String node){
		List<Map<String, Object>> json=new ArrayList<Map<String,Object>>();
		if("root".equals(node)){
			List<NavFirst>ln=navFirstMapper.selectBySelective(null);
			for (NavFirst navigationFirst : ln) {
				Map<String, Object> nodeMap=new HashMap<String, Object>();
				nodeMap.put("id", "first_"+navigationFirst.getId());
				nodeMap.put("text", navigationFirst.getFirstName());
				nodeMap.put("cls", "treeNodeFirstNav");
				nodeMap.put("expanded", false);
				nodeMap.put("leaf", false);
				json.add(nodeMap);
			}
		}else if(node.startsWith("first_")){
			NavSecond navSecond=new NavSecond();
			navSecond.setFirstId(node.replace("first_",""));
			List<NavSecond>ln=navSecondMapper.selectBySelective(navSecond);
			for (NavSecond navigationSecond : ln) {
				Map<String, Object> nodeMap=new HashMap<String, Object>();
				nodeMap.put("id", "second_"+navigationSecond.getId());
				nodeMap.put("text",navigationSecond.getSecondName());
				nodeMap.put("cls", "treeNodeSecondNav");
				nodeMap.put("expanded", false);
				nodeMap.put("leaf", false);
				json.add(nodeMap);
			}
		}else if(node.startsWith("second_")){
			NavThree navThree=new NavThree();
			navThree.setSecondId(node.replace("second_",""));
			List<NavThree>ln=navThreeMapper.selectBySelective(navThree);
			for (NavThree navigationThree : ln) {
				Map<String, Object> nodeMap=new HashMap<String, Object>();
				nodeMap.put("id", "three_"+navigationThree.getId());
				nodeMap.put("text",navigationThree.getThreeName());
				nodeMap.put("cls", "treeNodeThreeNav");
				nodeMap.put("expanded", false);
				nodeMap.put("leaf", true);
				json.add(nodeMap);
			}
		}
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("root", json);
		return JsonUtil.toJSONString(map);
	}
	@Transactional
	@RequestMapping(value="/dropTree",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody  String dropTree(String dropPosition,String yuanshi,String wancheng){
		if(yuanshi.startsWith("first_")){
			NavFirst firstyuanshi=navFirstMapper.selectByPrimaryKey(yuanshi.replace("first_", ""));
			NavFirst firstwancheng=navFirstMapper.selectByPrimaryKey(wancheng.replace("first_", ""));
			if(firstyuanshi.getOrderIndex()-firstwancheng.getOrderIndex()>0){
				dropPosition="before";
			}else{
				dropPosition="after";
			}
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("start", firstyuanshi.getOrderIndex());
			map.put("finish", firstwancheng.getOrderIndex());
			map.put("id", yuanshi.replace("first_", ""));
			firstyuanshi.setOrderIndex(firstwancheng.getOrderIndex());
			navFirstMapper.updateByPrimaryKeySelective(firstyuanshi);
			if(dropPosition.equals("before")){
				navFirstMapper.upOrderIndex(map);
			}else if(dropPosition.equals("after")){
				navFirstMapper.downOrderIndex(map);
			}	
		}else if(yuanshi.startsWith("second_")){
			if(!dropPosition.equals("append")){
				
			NavSecond secondyuanshi=navSecondMapper.selectByPrimaryKey(yuanshi.replace("second_", ""));
			NavSecond secondwancheng=navSecondMapper.selectByPrimaryKey(wancheng.replace("second_", ""));
			if(secondyuanshi.getOrderIndex()-secondwancheng.getOrderIndex()>0){
				dropPosition="before";
			}else{
				dropPosition="after";
			}
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("start", secondyuanshi.getOrderIndex());
			map.put("finish", secondwancheng.getOrderIndex());
			map.put("id", yuanshi.replace("second_", ""));
			secondyuanshi.setOrderIndex(secondwancheng.getOrderIndex());
			navSecondMapper.updateByPrimaryKeySelective(secondyuanshi);
			if(dropPosition.equals("before")){
				navSecondMapper.upOrderIndex(map);
			}else if(dropPosition.equals("after")){
				navSecondMapper.downOrderIndex(map);
			}	
			}
		}else if(yuanshi.startsWith("three_")){
			if(!dropPosition.equals("append")){
				NavThree threeyuanshi=navThreeMapper.selectByPrimaryKey(yuanshi.replace("three_", ""));
				NavThree threewancheng=navThreeMapper.selectByPrimaryKey( wancheng.replace("three_", ""));
				if(threeyuanshi.getOrderIndex()-threewancheng.getOrderIndex()>0){
					dropPosition="before";
				}else{
					dropPosition="after";
				}
				Map<String, Object> map=new HashMap<String, Object>();
			map.put("start", threeyuanshi.getOrderIndex());
			map.put("finish", threewancheng.getOrderIndex());
			map.put("id", yuanshi.replace("three_", ""));
			threeyuanshi.setOrderIndex(threewancheng.getOrderIndex());
			navThreeMapper.updateByPrimaryKeySelective(threeyuanshi);
			if(dropPosition.equals("before")){
				navThreeMapper.upOrderIndex(map);
			}else if(dropPosition.equals("after")){
				navThreeMapper.downOrderIndex(map);
			}
			}
		}
		return dropPosition;
		
	}
}
