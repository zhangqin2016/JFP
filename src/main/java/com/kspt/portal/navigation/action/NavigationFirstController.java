package com.kspt.portal.navigation.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.kspt.common.JsonUtil;
import com.kspt.common.dao.daoImp.BaseDaoImp;
import com.kspt.dao.NavFirstMapper;
import com.kspt.dao.NavSecondMapper;
import com.kspt.dao.NavThreeMapper;
import com.kspt.model.NavFirst;
import com.kspt.model.NavSecond;
import com.kspt.util.DataConnomImpl;

@RequestMapping("/portal/navication/first")
@Controller
public class NavigationFirstController {
	@Resource
	private BaseDaoImp baseDao;
	@Resource 
	private DataConnomImpl connomImpl;
	@Resource 
	private NavFirstMapper navFirstMapper;
	@Resource 
	private NavSecondMapper navSecondMapper;
	@Resource 
	private NavThreeMapper navThreeMapper;
	@RequestMapping
	public String getMainPage(){
		
		return "daohang/navigation_first";
	}
	
	@RequestMapping(value="/gridJson",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String getgridJson(){
			List<NavFirst>ln=navFirstMapper.selectBySelective(new NavFirst());
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("root", ln);
		return JsonUtil.toJSONString(map);
	}
	@RequestMapping(value="/del",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String del(String []ids){
		String isNext="";
		for (int i = 0; i < ids.length; i++) {
			//先判断是否有下级菜单
			NavSecond navSecond=new NavSecond();
			navSecond.setFirstId(ids[i]);
			List<NavSecond>ln=navSecondMapper.selectBySelective(navSecond);
			int size=0;
			if(ln==null){
			}else{
				size=ln.size();
			}
			if(size>0){
				isNext="存在下级菜单,请先删除下级菜单!";
				continue;
			}else{
				navFirstMapper.deleteByPrimaryKey(ids[i]);
			}
		}
		return "删除成功!"+isNext;
	}
	@RequestMapping(value="/save",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String save(String datas){
		List<NavFirst> list= JSONArray.parseArray(datas,NavFirst.class);
		return getReturnValue(list);
	}
	/**
	 * 得到插入更新操作并返回值
	 * @param list
	 * @return 
	 */
	private String getReturnValue(List<NavFirst> list){
		for (NavFirst navigationFirst : list) {
			if(navigationFirst.getId().equals("")){
				navigationFirst.setId(connomImpl.getUUID());
				navigationFirst.setOrderIndex(Long.parseLong(connomImpl.getSequence("zq_nav_first")+""));
				navFirstMapper.insertSelective(navigationFirst);
			}else{
				navFirstMapper.updateByPrimaryKeySelective(navigationFirst);
			}
		}
		return "操作成功";
	}
	
}
