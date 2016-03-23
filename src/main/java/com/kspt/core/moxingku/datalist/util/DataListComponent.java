package com.kspt.core.moxingku.datalist.util;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.kspt.common.dao.daoImp.BaseDaoImp;
import com.kspt.core.moxingku.datalist.pojo.DataList;
import com.kspt.core.moxingku.datalist.pojo.DataListButton;
import com.kspt.core.moxingku.datalist.pojo.DataListColumn;
import com.kspt.core.moxingku.datalist.pojo.DataListQuery;
import com.kspt.core.moxingku.datalist.service.DataListService;
import com.kspt.core.moxingku.pojo.ModelLibrary;

@Component
public class DataListComponent {

	@Resource
	private  BaseDaoImp baseDao;

	@Resource
	private  DataListService dataListService;
	
	
	public  String getModelName(String datalistId){
		if(datalistId==null){
			return "";
		}
		DataList dataList=dataListService.get(datalistId);
		return getModelName(dataList);
	}
	public  String getModelName(DataList dataList){
		ModelLibrary modelLibrary=	(ModelLibrary) baseDao.queryOne("zq_model_library.getmoldelibrarybyid", dataList.getModelLibraryId());
		return modelLibrary.getModel_name();
	}
	public List<DataListColumn> getDataListColumnsByDataListId(String id){
		DataList dataList=dataListService.get(id);
		return dataList.getDataListColumns();
	}
	
	public List<DataListButton> getDataListButtonsByDataListId(String id){
		DataList dataList=dataListService.get(id);
		return dataList.getDataListButtons();
	}
	
	public List<DataListQuery> getDataListQuerysByDataListId(String id){
		DataList dataList=dataListService.get(id);
		return dataList.getDataListQuerys();
	}
}
