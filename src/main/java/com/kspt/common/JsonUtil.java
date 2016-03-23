package com.kspt.common;

import java.util.List;

import com.alibaba.fastjson.JSON;

public class JsonUtil {

	public static String toJSONString(Object object){
		return JSON.toJSONString(object);
	}
	
	public static   <T> List<T>  parseJavaBean(String json, Class<T> clazz){
		
		return   JSON.parseArray(json, clazz);
	}
	public static Object  parseJson(String json){
		
		return JSON.parse(json);
	}
}
