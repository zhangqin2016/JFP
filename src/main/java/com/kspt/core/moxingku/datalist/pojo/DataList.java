package com.kspt.core.moxingku.datalist.pojo;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;


@XStreamAlias("dataList")
public class DataList {
	private String name;
	private String modelLibraryId;
	private String displayTitle;
	private String formId;
	private String id;
	private String  pageSize;
	private String sqlFilter;
	private String dataListType;
	private String processId;
	private String order_by;
	private String table_id;
	private int order_index;
	@XStreamImplicit(itemFieldName="dataListColumn")
	private List<DataListColumn> dataListColumns;
	@XStreamImplicit(itemFieldName="dataListQuery")
	private List<DataListQuery> dataListQuerys;
	@XStreamImplicit(itemFieldName="dataListButton")
	private List<DataListButton> dataListButtons;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getModelLibraryId() {
		return modelLibraryId;
	}
	public void setModelLibraryId(String modelLibraryId) {
		this.modelLibraryId = modelLibraryId;
	}
	public String getDisplayTitle() {
		return displayTitle;
	}
	public void setDisplayTitle(String displayTitle) {
		this.displayTitle = displayTitle;
	}
	public String getFormId() {
		return formId;
	}
	public void setFormId(String formId) {
		this.formId = formId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	public String getSqlFilter() {
		return sqlFilter;
	}
	public void setSqlFilter(String sqlFilter) {
		this.sqlFilter = sqlFilter;
	}
	public String getOrder_by() {
		return order_by;
	}
	public void setOrder_by(String order_by) {
		this.order_by = order_by;
	}
	public String getDataListType() {
		return dataListType;
	}
	public void setDataListType(String dataListType) {
		this.dataListType = dataListType;
	}
	public String getProcessId() {
		return processId;
	}
	public void setProcessId(String processId) {
		this.processId = processId;
	}
	public List<DataListColumn> getDataListColumns() {
		return dataListColumns;
	}
	public void setDataListColumns(List<DataListColumn> dataListColumns) {
		this.dataListColumns = dataListColumns;
	}
	public List<DataListQuery> getDataListQuerys() {
		return dataListQuerys;
	}
	public void setDataListQuerys(List<DataListQuery> dataListQuerys) {
		this.dataListQuerys = dataListQuerys;
	}
	public List<DataListButton> getDataListButtons() {
		return dataListButtons;
	}
	public void setDataListButtons(List<DataListButton> dataListButtons) {
		this.dataListButtons = dataListButtons;
	}
	public int getOrder_index() {
		return order_index;
	}
	public void setOrder_index(int order_index) {
		this.order_index = order_index;
	}
	public String getTable_id() {
		return table_id;
	}
	public void setTable_id(String table_id) {
		this.table_id = table_id;
	}
	
}
