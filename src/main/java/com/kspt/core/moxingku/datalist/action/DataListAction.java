package com.kspt.core.moxingku.datalist.action;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kspt.common.JsonUtil;
import com.kspt.common.ZQResourceLoaderPath;
import com.kspt.common.dao.daoImp.BaseDaoImp;
import com.kspt.core.moxingku.datalist.pojo.DataList;
import com.kspt.core.moxingku.datalist.service.DataListService;
import com.kspt.core.moxingku.datalist.util.DataListComparator;

@Controller
@RequestMapping("/zq/dataList")
public class DataListAction {
@Resource
private ZQResourceLoaderPath  rs;
@Resource
private BaseDaoImp baseDao;

@Resource
private DataListService dataListService;
@RequestMapping(value="/getCreateDataListPage",method={RequestMethod.POST,RequestMethod.GET})
public String createDataListPage (String moxingkuid,String dataListType,ModelMap map){
	
	map.put("moxingkuid", moxingkuid);
	map.put("dataListType", dataListType);
	return "moxingku/datalist/datalist_create";
}

	@RequestMapping(value="/createDataList",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String createDataList (DataList dataList){
		dataListService.create(dataList);
		return "创建成功!";
	}
	
	@RequestMapping(value="/getDataListJson",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String getDataListJson (String moxingkuid,String dataListType){
	List<DataList> dataLists=dataListService.getDataListByModelLibAndViewType(moxingkuid, dataListType);
	Collections.sort(dataLists, new DataListComparator()); 
		return JsonUtil.toJSONString(dataLists);
	}
	
	@RequestMapping(value="/delDataList",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String delDataList (String[] ids){
		for (String id : ids) {
			dataListService.delete(id);
		}
		return "删除成功!";
	}
	
	@RequestMapping(value="/getDataListPage",method={RequestMethod.POST,RequestMethod.GET})
	public  String getDataListPage (String moxingkuid,ModelMap map,String dataListType){
		map.put("moxingkuid", moxingkuid);
		map.put("dataListType", dataListType);
		return "moxingku/datalist/datalist";
	}
	
	@RequestMapping(value="/getDataListMainPage",method={RequestMethod.POST,RequestMethod.GET})
	public  String getDataMainPage (ModelMap map,String datalistid){
		map.put("datalistid", datalistid);
		return "moxingku/datalist/datalist_main";
	}
	
	@RequestMapping(value="/getDataListInfoPage",method={RequestMethod.POST,RequestMethod.GET})
	public  String getDataListInfoPage (ModelMap map,String datalistid){
		DataList dataList=dataListService.get(datalistid);
		map.put("datalistid", datalistid);
		map.put("name", dataList.getName());
		map.put("modelLibraryId", dataList.getModelLibraryId());
		map.put("displayTitle", dataList.getDisplayTitle());
		map.put("formId", dataList.getFormId());
		map.put("dataListType", dataList.getDataListType());
		map.put("processId", dataList.getProcessId());
		map.put("order_by", dataList.getOrder_by());
		map.put("pageSize", dataList.getPageSize());
		map.put("table_id", dataList.getTable_id());
		map.put("sqlFilter", dataList.getSqlFilter());
		return "moxingku/datalist/datalist_info";
	}
	 
	@RequestMapping(value="/saveDataListInfo",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String saveDataListInfo (String pageSize,String id,String table_id,String sqlFilter,String order_by,String modelLibraryId,String name,String displayTitle,String formId,String dataListType,String processId){ 
		DataList datalist=dataListService.get(id);
		datalist.setDataListType(dataListType);
		datalist.setDisplayTitle(displayTitle);
		datalist.setFormId(formId);
		datalist.setTable_id(table_id);
		datalist.setModelLibraryId(modelLibraryId);
		datalist.setName(name);
		datalist.setProcessId(processId);
		datalist.setPageSize(pageSize);
		datalist.setSqlFilter(sqlFilter);
		datalist.setOrder_by(order_by);
		dataListService.update(datalist);
		
		return "保存成功!";
	}
}
