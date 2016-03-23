package com.kspt.portal.navigation.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.kspt.common.JsonUtil;
import com.kspt.common.dao.daoImp.BaseDaoImp;
import com.kspt.dao.NavFirstMapper;
import com.kspt.dao.NavSecondMapper;
import com.kspt.dao.NavThreeMapper;
import com.kspt.model.NavSecond;
import com.kspt.model.NavThree;
import com.kspt.util.DataConnomImpl;

@RequestMapping("/portal/navication/second")
@Controller
public class NavigationSecondController {
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
	public String getMainPage(String first_id,ModelMap map){
		map.put("first_id", first_id);
		return "daohang/navigation_second";
	}
	
	@RequestMapping(value="/gridJson",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String getgridJson(String first_id){
			NavSecond navSecond=new NavSecond();
			navSecond.setFirstId(first_id);
			List<NavSecond>ln=navSecondMapper.selectBySelective(navSecond);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("root", ln);
		return JsonUtil.toJSONString(map);
	}
	@RequestMapping(value="/del",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String del(String []ids){
		String isNext="";
		for (int i = 0; i < ids.length; i++) {
			//先判断是否有下级菜单
			NavThree navThree=new NavThree();
			navThree.setSecondId(ids[i]);
			List<NavThree>ln=navThreeMapper.selectBySelective(navThree);
			if(ln!=null&&ln.size()>0){
				isNext="存在下级菜单没有删除,请先删除下级菜单!";
				continue;
			}else{
				navSecondMapper.deleteByPrimaryKey(ids[i]);
			}
		}
		return "删除成功!"+isNext;
	}
	@RequestMapping(value="/save",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String save(String datas){
		List<NavSecond> list=JSONArray.parseArray(datas,NavSecond.class);
		return getReturnValue(list);
	}
	/**
	 * 得到插入更新操作并返回值
	 * @param list
	 * @return 
	 */
	private String getReturnValue(List<NavSecond> list){
		for (NavSecond navigationSecond : list) {
			if(navigationSecond.getId().equals("")){
				navigationSecond.setId(connomImpl.getUUID());
				navigationSecond.setOrderIndex(Long.parseLong(connomImpl.getSequence("zq_nav_second")+""));
				navSecondMapper.insertSelective(navigationSecond);
			}else{
				navSecondMapper.updateByPrimaryKeySelective(navigationSecond);
			}
		}
		return "操作成功";
	}
	
}
