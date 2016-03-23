package com.kspt.test;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kspt.common.dao.daoImp.BaseDaoImp;
import com.kspt.util.DataConnomImpl;

@Controller
@RequestMapping("/test2")
public class Test2 {

	@Resource
	private BaseDaoImp baseDao;
	@Resource 
	private DataConnomImpl connomImpl;
	@RequestMapping(value="getPage",method={RequestMethod.POST,RequestMethod.GET})
	private String getPage(ModelMap map,String XM){
		map.put("XM", XM);
		return "/NewFile";
	}
	
	
	@RequestMapping(value="getString",method={RequestMethod.POST,RequestMethod.GET})
	private @ResponseBody String getString(){
		return "11";
	}
	
}
