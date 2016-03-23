package com.kspt.common;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.core.io.ResourceLoader;

public class ZQResourceLoaderPath {
	private String processPath;
	private String templateSystemPath;
	private String templateCustomPath;
	private String dataListPath;
	private String config;
	private String attachment;
	private String dataGrid;
	
	private String attached;
	

	public String getAttached() {
		return getXmlPath()+attached;
	}
	public void setAttached(String attached) {
		this.attached = attached;
	}
	public static String getXmlPath()  {
	        return System.getProperty("zq.root");
	    } 
	public String getProcessPath() throws IOException {
		
		return getXmlPath()+processPath;
	}

	public void setProcessPath(String processPath) {
		this.processPath = processPath;
	}

	public String getFullTemplateSystemPath() throws IOException {
		return getXmlPath()+templateSystemPath;
	}
	public String getWebTemplateSystemPath() throws IOException {
		return templateSystemPath;
	}

	public void setTemplateSystemPath(String templateSystemPath) {
		this.templateSystemPath = templateSystemPath;
	}

	public String getTemplateCustomPath() throws IOException {
		return getXmlPath()+templateCustomPath;
	}
	
	public String getDataListPath() throws IOException {
		return getXmlPath()+dataListPath;
	}

	public void setDataListPath(String dataListPath) {
		this.dataListPath = dataListPath;
	}

	public String getWebTemplateCustomPath() throws IOException {
		return templateCustomPath;
	}

	public void setTemplateCustomPath(String templateCustomPath) {
		this.templateCustomPath = templateCustomPath;
	}

	public String getConfig() throws IOException {
		return getXmlPath()+config;
	}

	public void setConfig(String config) {
		this.config = config;
	}

	public String getAttachment() throws IOException {
		return getXmlPath()+attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	 public String getDataGrid() {
		  	return getXmlPath()+dataGrid;
	}
	public void setDataGrid(String dataGrid) {
		this.dataGrid = dataGrid;
	}
}
