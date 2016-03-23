package com.kspt.portal.login;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MySessionListener implements HttpSessionListener,
		HttpSessionAttributeListener {

	public void sessionCreated(HttpSessionEvent event) {
		// 创建Session 时被调用
		HttpSession session = event.getSession(); // 新创建的Session
		OnlineConstants.SESSION_MAP.put(session.getId(), session);
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		// 销毁Session前被调用
		HttpSession session = event.getSession(); // 即将被销毁的session
		OnlineConstants.SESSION_MAP.remove(session.getId());// 移除session记录
	}

	public void attributeAdded(HttpSessionBindingEvent event) {
	
	}

	public void attributeRemoved(HttpSessionBindingEvent event) {
	}

	public void attributeReplaced(HttpSessionBindingEvent event) {
	}

}