package com.kspt.core.moxingku.datalist.tool;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kspt.core.moxingku.datalist.pojo.DataList;
import com.kspt.core.moxingku.datalist.pojo.DataListQuery;
import com.kspt.core.moxingku.datalist.service.DataListService;

@Service
public class DatalistQueryTool {

	
	@Resource
	private DataListService dataListService;
	public  boolean isExists(String datalistid,String fieldName){
		
		DataList dataList=dataListService.get(datalistid);
		List<DataListQuery> dataListQuerys=dataList.getDataListQuerys();
		if (dataListQuerys!=null&& dataListQuerys.size()>0) {
			for (DataListQuery dataListQuery : dataListQuerys) {
				if(fieldName.equals(dataListQuery.getFieldName())){
					return true;
				}
			}
		
		}
		return false;
	}
	
	public DataListQuery get(String datalistid,String id){
		DataList dataList=dataListService.get(datalistid);
		List<DataListQuery> dataListQuerys=dataList.getDataListQuerys();
		if (dataListQuerys!=null&& dataListQuerys.size()>0) {
			for (DataListQuery dataListQuery : dataListQuerys) {
				if(id.equals(dataListQuery.getId())){
					return dataListQuery;
				} 
			}
		
		}
		return null;
	}
	
	public List<DataListQuery> updateColumn( List<DataListQuery> dataListQuerys,DataListQuery dataListQueryUp){
		if (dataListQuerys!=null&& dataListQuerys.size()>0) {
			for (DataListQuery dataListColumn : dataListQuerys) {
				if(dataListColumn.getFieldName().equals(dataListQueryUp.getFieldName())){
				}
			}
		
		}
		return dataListQuerys;
	}
}
