package com.kspt.common;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;

public class ZqContextLis  extends ContextLoaderListener {  
  
    public void contextDestroyed(ServletContextEvent sce) {  
        // TODO Auto-generated method stub  
  
    }  
  
    public void contextInitialized(ServletContextEvent sce) {  
        // TODO Auto-generated method stub  
        String webAppRootKey = sce.getServletContext().getRealPath("/");  
        System.setProperty("zq.root" , webAppRootKey);  
        String path =System.getProperty("zq.root");  
        System.out.println("zq:::"+path);  
    }  
  
}  