package com.kspt.core.moxingku.datalist.tool;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kspt.core.moxingku.datalist.pojo.DataList;
import com.kspt.core.moxingku.datalist.pojo.DataListColumn;
import com.kspt.core.moxingku.datalist.service.DataListService;

@Service
public class DatalistColumnTool {

	
	@Resource
	private DataListService dataListService;
	
	public  boolean isExists(String datalistid,String fieldName){
		
		DataList dataList=dataListService.get(datalistid);
		List<DataListColumn> dataListColumns=dataList.getDataListColumns();
		if (dataListColumns!=null&& dataListColumns.size()>0) {
			for (DataListColumn dataListColumn : dataListColumns) {
				if(fieldName.equals(dataListColumn.getField())){
					return true;
				}
			}
		
		}
		return false;
	}
	
	public List<DataListColumn> updateColumn( List<DataListColumn> dataListColumns,DataListColumn dataListColumnUp){
		if (dataListColumns!=null&& dataListColumns.size()>0) {
			for (DataListColumn dataListColumn : dataListColumns) {
				if(dataListColumn.getField().equals(dataListColumnUp.getField())){
					dataListColumn.setIs_hidden(dataListColumnUp.isIs_hidden());
					dataListColumn.setTitle(dataListColumnUp.getTitle());
					dataListColumn.setIs_fuzzySearch(dataListColumnUp.isIs_fuzzySearch());
				}
			}
		
		}
		return dataListColumns;
	}
}
