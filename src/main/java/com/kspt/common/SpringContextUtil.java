package com.kspt.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
/**
  * 工具类 - Spring
  */
 @Component
public class SpringContextUtil implements ApplicationContextAware {
  private static ApplicationContext applicationContext;
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
   SpringContextUtil.applicationContext = applicationContext;
  }
  public static ApplicationContext getApplicationContext() {
   return applicationContext;
  }
  /**
   * 根据Bean名称获取实例
  * @param name
   * Bean注册名称
  * @return bean实例
  * @throws BeansException
   */
  public static Object getBean(String name) throws BeansException {
   return applicationContext.getBean(name);
  }
  
  public static Object getBean(Class<?> name) throws BeansException {
	   return applicationContext.getBean(name);
	  }
 }