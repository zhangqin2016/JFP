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
import com.kspt.model.NavThree;
import com.kspt.util.DataConnomImpl;

@RequestMapping("/portal/navication/three")
@Controller
public class NavigationThreeController {
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
	public String getMainPage(String second_id,ModelMap map){
		map.put("second_id", second_id);
		return "daohang/navigation_three";
	}
	
	@RequestMapping(value="/gridJson",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String getgridJson(String second_id){
		NavThree navThree=new NavThree();
		navThree.setSecondId(second_id);
		List<NavThree>ln=navThreeMapper.selectBySelective(navThree);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("root", ln);
		return JsonUtil.toJSONString(map);
	}
	@RequestMapping(value="/del",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String del(String []ids){
		for (int i = 0; i < ids.length; i++) {
			navThreeMapper.deleteByPrimaryKey(ids[i]);
		}
		return "删除成功!";
	}
	@RequestMapping(value="/save",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String save(String datas){
		List<NavThree> list=JSONArray.parseArray(datas,NavThree.class);
		return getReturnValue(list);
	}
	/**
	 * 得到插入更新操作并返回值
	 * @param list
	 * @return 
	 */
	private String getReturnValue(List<NavThree> list){
		for (NavThree navigationThree : list) {
			if(navigationThree.getId().equals("")){
				navigationThree.setId(connomImpl.getUUID());
				navigationThree.setOrderIndex(Long.parseLong(connomImpl.getSequence("zq_nav_three")+""));
				navThreeMapper.insertSelective(navigationThree);
			}else{
				navThreeMapper.updateByPrimaryKeySelective(navigationThree);
			}
		}
		return "操作成功";
	}
	
}
