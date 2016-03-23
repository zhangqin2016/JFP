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
import com.kspt.core.moxingku.datalist.pojo.DataListColumn;
import com.kspt.core.moxingku.datalist.service.DataListService;
import com.kspt.core.moxingku.datalist.tool.DatalistColumnTool;
import com.kspt.core.moxingku.form.pojo.FormEntityRelationshipModel;
import com.kspt.core.moxingku.form.pojo.FormUIModel;
import com.kspt.core.moxingku.form.service.FormUiService;
import com.kspt.util.DataConnomImpl;

@Controller
@RequestMapping("/zq/dataList/column")
public class DataListColumnAction {

	
	@Resource
	private DatalistColumnTool datalistColumnTool;
	@Resource
	DataListService listService;
	@Resource
	private BaseDaoImp baseDao;
	@Resource
	private DataConnomImpl connomImpl;
	@RequestMapping
	public String listPage(ModelMap map, String datalistid) {
		map.put("datalistid", datalistid);
		return "moxingku/datalist/datalist_columnlist";
	}

	@RequestMapping(value = "/gridJson", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody
	String getCompanyGridJson(String datalistid) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<DataListColumn> list = listService.get(datalistid)
				.getDataListColumns();
		map.put("root", list);
		map.put("totalProperty", list == null ? 0 : list.size());
		return JsonUtil.toJSONString(map);
	}

	@RequestMapping(value = "/delete", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody
	String delete(String[] dataListColumnIds, String dataListId) {
		DataList dataList = listService.get(dataListId);
		List<DataListColumn> listColumn = dataList.getDataListColumns();
		for (String cid : dataListColumnIds) {
			for (int i = 0; i < listColumn.size(); i++) {
				if (cid.equals(listColumn.get(i).getId())) {
					listColumn.remove(listColumn.get(i));
				}
			}
		}
		dataList.setDataListColumns(listColumn);
		listService.update(dataList);
		return ZqConstant.DELETE_SUCCESS;
	}
	@RequestMapping(value = "/save", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody
	String save(String columns,String dataListId) {
		List<DataListColumn> listm=JsonUtil.parseJavaBean(columns, DataListColumn.class);
		DataList dataList=listService.get(dataListId);
		List<DataListColumn> dataListColumns=dataList.getDataListColumns();
		for (DataListColumn dataListColumn : listm) {
			datalistColumnTool.updateColumn(dataListColumns, dataListColumn);
		}
		dataList.setDataListColumns(dataListColumns);
		listService.update(dataList);
		return ZqConstant.SAVE_SUCCESS;
	}
	@RequestMapping(value = "/add", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody
	String add(String columns,String dataListId) {
		DataList dataList = listService.get(dataListId);
		List<Map<String, String>>listm=(List<Map<String, String>>) JsonUtil.parseJson(columns);
	List<DataListColumn> datalistColumns=dataList.getDataListColumns();
			for (Map<String, String> map : listm) {
				datalistColumns=(datalistColumns==null?new ArrayList<DataListColumn>():datalistColumns);
				if(datalistColumnTool.isExists(dataListId, map.get("ui_name"))){
					continue;
				}
				datalistColumns.add(new DataListColumn( connomImpl.getUUID(),map.get("ui_name"), map.get("ui_title"), false, connomImpl.getSequence("zq_datalist_column"), false, null));
			}
		dataList.setDataListColumns(datalistColumns);
		listService.update(dataList);
		return ZqConstant.ADD_SUCCESS;
	}
	@RequestMapping(value = "/getAddPage", method = { RequestMethod.POST,
			RequestMethod.GET })
	public String getAddPage(String dataListId,ModelMap map) {
		map.put("dataListId", dataListId);
		return "moxingku/datalist/datalist_addColunmPage";
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
