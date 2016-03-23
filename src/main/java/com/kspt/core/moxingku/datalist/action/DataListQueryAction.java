package com.kspt.core.moxingku.datalist.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kspt.common.JsonUtil;
import com.kspt.common.ZqConstant;
import com.kspt.common.dao.daoImp.BaseDaoImp;
import com.kspt.core.moxingku.datalist.pojo.DataList;
import com.kspt.core.moxingku.datalist.pojo.DataListQuery;
import com.kspt.core.moxingku.datalist.pojo.DataListQuery;
import com.kspt.core.moxingku.datalist.service.DataListService;
import com.kspt.core.moxingku.datalist.tool.DatalistColumnTool;
import com.kspt.core.moxingku.datalist.tool.DatalistQueryTool;
import com.kspt.core.moxingku.form.pojo.FormEntityRelationshipModel;
import com.kspt.core.moxingku.form.pojo.FormUIModel;
import com.kspt.core.moxingku.form.service.FormUiService;
import com.kspt.core.moxingku.pojo.ModelLibrary;
import com.kspt.core.moxingku.table.pojo.MetadataModel;
import com.kspt.util.DataConnomImpl;


@Controller
@RequestMapping("/zq/dataList/query")
public class DataListQueryAction {
	

	@Resource
	private DatalistQueryTool datalistQueryTool;
	@Resource
	DataListService listService;
	@Resource
	private BaseDaoImp baseDao;
	@Resource
	private DataConnomImpl connomImpl;
	
	@RequestMapping(value = "/listPage", method = { RequestMethod.POST,
			RequestMethod.GET })
	public  String listPage (ModelMap map,String datalistid){
		map.put("datalistid", datalistid);
		return "moxingku/datalist/datalist_querylist";
	}
	
	@RequestMapping(value = "/gridJson", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody
	String getCompanyGridJson(String datalistid) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<DataListQuery> list = listService.get(datalistid)
				.getDataListQuerys();
		map.put("root", list);
		map.put("totalProperty", list == null ? 0 : list.size());
		return JsonUtil.toJSONString(map);
	}
	

	@RequestMapping(value = "/delete", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody
	String delete(String[] dataListQueryIds, String dataListId) {
		DataList dataList = listService.get(dataListId);
		List<DataListQuery> listQuery = dataList.getDataListQuerys();
		for (String cid : dataListQueryIds) {
			for (int i = 0; i < listQuery.size(); i++) {
				if (cid.equals(listQuery.get(i).getId())) {
					listQuery.remove(listQuery.get(i));
				}
			}
		}
		dataList.setDataListQuerys(listQuery);
		listService.update(dataList);
		return ZqConstant.DELETE_SUCCESS;
	}
	@RequestMapping(value = "/save", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody
	String save(String querys,String dataListId) {
		List<DataListQuery> listm=JsonUtil.parseJavaBean(querys, DataListQuery.class);
		DataList dataList=listService.get(dataListId);
		List<DataListQuery> dataListQuerys=dataList.getDataListQuerys();
		for (DataListQuery dataListQuery : listm) {
			datalistQueryTool.updateColumn(dataListQuerys, dataListQuery);
		}
		dataList.setDataListQuerys(dataListQuerys);
		listService.update(dataList);
		return ZqConstant.SAVE_SUCCESS;
	}
	@RequestMapping(value = "/add", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody
	String add(String querys,String dataListId) {
		DataList dataList = listService.get(dataListId);
		List<Map<String, String>>listm=(List<Map<String, String>>) JsonUtil.parseJson(querys);
	List<DataListQuery> datalistQuerys=dataList.getDataListQuerys();
			for (Map<String, String> map : listm) {
				datalistQuerys=(datalistQuerys==null?new ArrayList<DataListQuery>():datalistQuerys);
				if(datalistQueryTool.isExists(dataListId, map.get("ui_name"))){
					continue;
				}
				datalistQuerys.add(new DataListQuery(map.get("ui_name"), map.get("ui_title"), "=","文本",connomImpl.getUUID(),"",connomImpl.getSequence("zq_datalist_query"), true));
			}
		dataList.setDataListQuerys(datalistQuerys);
		listService.update(dataList);
		return ZqConstant.ADD_SUCCESS;
	}
	@RequestMapping(value = "/getAddPage", method = { RequestMethod.POST,
			RequestMethod.GET })
	public String getAddPage(String dataListId,ModelMap map) {
		map.put("dataListId", dataListId);
		return "moxingku/datalist/datalist_addQueryPage";
	}
	
	
	@Resource
	private FormUiService formUiService;
	@RequestMapping(value = "/gridAddJson", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody
	String gridAddJson(String dataListId) {
		DataList dataList = listService.get(dataListId);
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> mapParam = new HashMap<String, Object>();
		mapParam.put("form_id", dataList.getFormId());
		mapParam.put("er_type", 0);
	
		FormEntityRelationshipModel formEntityRelationshipModel = (FormEntityRelationshipModel) baseDao.queryOne(
				"zq_form_er_er.getFormEr", mapParam);
		
			if(formEntityRelationshipModel==null){
			return JsonUtil.toJSONString(map);	
		}
		List<FormUIModel> listModel = (List<FormUIModel>) baseDao.query("zq_form_ui.getFormUIbyFormErId",formEntityRelationshipModel.getId());
		for (FormUIModel formUIModel : listModel) {
			String former_id=formUIModel.getForm_er_id();
			Map<String, Object>mapQuery=new HashMap<String, Object>();
			mapQuery.put("id", former_id);
			FormEntityRelationshipModel entityRelationshipModel=(FormEntityRelationshipModel) baseDao.queryOne("zq_form_er_er.getFormEr", mapQuery);
			if(entityRelationshipModel!=null){
				formUIModel.setUi_name(formUiService.getDatalistAddListName(entityRelationshipModel, formUIModel));
			}
		}
		map.put("root", listModel);
		map.put("totalProperty", listModel==null?0:listModel.size());
		return JsonUtil.toJSONString(map);
	}
}
