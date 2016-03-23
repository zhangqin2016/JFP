package com.kspt.core.moxingku.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.kspt.common.JsonUtil;
import com.kspt.common.dao.daoImp.BaseDaoImp;
import com.kspt.core.moxingku.datalist.pojo.DataList;
import com.kspt.core.moxingku.datalist.service.DataListService;
import com.kspt.core.moxingku.datalist.util.DataListComparator;
import com.kspt.core.moxingku.form.pojo.FormModel;
import com.kspt.core.moxingku.pojo.ModelLibrary;
import com.kspt.core.moxingku.table.pojo.MetadataModel;
import com.kspt.core.moxingku.util.MoXingKuChangLiang;
import com.kspt.util.DataConnomImpl;

@Controller
@RequestMapping("/moxingku/main")
public class MoxingkuAction {
	@Resource
	private BaseDaoImp baseDao;

	@Resource
	private DataConnomImpl connomImpl;

	@Resource
	private DataListService dataListService;

	@RequestMapping(method = { RequestMethod.POST, RequestMethod.GET })
	public String getMainPage() {
		return "moxingku/moxingku_main";
	}

	@RequestMapping(value = "/listPage", method = { RequestMethod.POST,
			RequestMethod.GET })
	public String getModelListPage() {
		return "moxingku/moxingku_list";
	}

	@RequestMapping(value = "/gridJson", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody
	String getCompanyGridJson() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<ModelLibrary> listModel = (List<ModelLibrary>) baseDao
				.query("zq_model_library.getallmoldelibrary");
		map.put("root", listModel);
		map.put("totalProperty", listModel.size());
		return JsonUtil.toJSONString(map);
	}

	@RequestMapping(value = "/del", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody
	String del(String[] ids) {
		for (int i = 0; i < ids.length; i++) {
			// 先判断模型库下是否存在数据
			if (baseDao.queryTotal("zq_bo_metadata.queryCountByModellibid",
					ids[i]) > 0) {
				return "存在表没有删除，请先删除表。";
			} else {
				baseDao.delete("zq_model_library.deleteModelLibrary", ids[i]);
			}
		}
		return "删除成功!";
	}

	@RequestMapping(value = "/save", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody
	String save(String datas) {
		List<ModelLibrary> list = (List<ModelLibrary>) JSONArray.parseArray(datas, ModelLibrary.class);
		return getReturnValue(list);
	}

	@RequestMapping(value = "/treeJson", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody
	String getTreeJson(String node,String type) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> listm = new ArrayList<Map<String, Object>>();
		if (node.equals("root")) {
			List<ModelLibrary> listModel = (List<ModelLibrary>) baseDao
					.query("zq_model_library.getallmoldelibrary");
			for (ModelLibrary model : listModel) {
				listm.add(getWorklistNodeMap("model_" + model.getId(),
						model.getModel_name(), "treeNodeOrg", false, false));

			}
		} else if (node.startsWith("model_")) {
			String modelId = node.replace("model_", "");
			if(type==null||type.equals(MoXingKuChangLiang.ZI_DING_YI_TREE_TABLE_TYPE))
			listm.add(getWorklistNodeMap("table_" + modelId, "数据建模",
					"treeNodeOrg", false, false));
			if(type==null||type.equals(MoXingKuChangLiang.ZI_DING_YI_TREE_FORM_TYPE))
			listm.add(getWorklistNodeMap("form_" + modelId, "表单建模",
					"treeNodeOrg", false, false));
			if(type==null||type.equals(MoXingKuChangLiang.ZI_DING_YI_TREE_DATA_LIST_TYPE))
			listm.add(getWorklistNodeMap("datalist_" + modelId, "数据列表建模",
					"treeNodeOrg", false, false));
			if(type==null||type.equals(MoXingKuChangLiang.ZI_DING_YI_TREE_PROCESS_LIST_TYPE))
			listm.add(getWorklistNodeMap("processlist_" + modelId, "流程列表建模",
					"treeNodeOrg", false, false));
		} else if (node.startsWith("table_")) {
			if(type==null||type.equals(MoXingKuChangLiang.ZI_DING_YI_TREE_TABLE_TYPE)){
			List<MetadataModel> listModel = (List<MetadataModel>) baseDao
					.query("zq_bo_metadata.getmetadataByModellibid",
							node.replace("table_", ""));
			for (MetadataModel metadataModel : listModel) {
				listm.add(getWorklistNodeMap(
						"metadata_" + metadataModel.getId(),
						metadataModel.getTable_title() + "("
								+ metadataModel.getTable_name() + ")",
						"treeNodeOrg", false, true));
			}
			}
		} else if (node.startsWith("form_")) {
			if(type==null||type.equals(MoXingKuChangLiang.ZI_DING_YI_TREE_FORM_TYPE)){
			List<FormModel> listModel = (List<FormModel>) baseDao.query(
					"zq_form.getFormByModellibid", node.replace("form_", ""));
			for (FormModel formModel : listModel) {
				listm.add(getWorklistNodeMap("formlist_" + formModel.getId(),
						formModel.getName(), "treeNodeOrg", false, true));

			}
			}
		} else if (node.startsWith("processlist_")) {
			if(type==null||type.equals(MoXingKuChangLiang.ZI_DING_YI_TREE_PROCESS_LIST_TYPE)){
			List<DataList> dataLists = dataListService
					.getDataListByModelLibAndViewType(
							node.replace("processlist_", ""), "流程列表");
			Collections.sort(dataLists, new DataListComparator());
			for (DataList dataList : dataLists) {
				listm.add(getWorklistNodeMap("listprocess_" + dataList.getId(),
						dataList.getName(), "treeNodeOrg", false, true));

			}
			}
		} else if (node.startsWith("datalist_")) {
			if(type==null||type.equals(MoXingKuChangLiang.ZI_DING_YI_TREE_DATA_LIST_TYPE)){
			List<DataList> dataLists = dataListService
					.getDataListByModelLibAndViewType(
							node.replace("datalist_", ""), "数据列表");
			Collections.sort(dataLists, new DataListComparator());
			for (DataList dataList : dataLists) {
				listm.add(getWorklistNodeMap("listdata_" + dataList.getId(),
						dataList.getName(), "treeNodeOrg", false, true));
			}
			}
		}

		map.put("root", listm);
		return JsonUtil.toJSONString(map);
	}

	/**
	 * 得到插入更新操作并返回值
	 * 
	 * @param list
	 * @return
	 */
	private String getReturnValue(List<ModelLibrary> list) {
		StringBuffer sbinsert = new StringBuffer();
		StringBuffer sbupdata = new StringBuffer();
		StringBuffer sbreturn = new StringBuffer();
		for (ModelLibrary modelLibrary : list) {
			if (modelLibrary.getId().equals("")) {
				modelLibrary.setId(connomImpl.getUUID());
				modelLibrary.setOrder_index(connomImpl
						.getSequence("zq_model_library"));
				if (baseDao.queryTotal(
						"zq_model_library.getmoldelibrarybyname",
						modelLibrary.getModel_name()) == 0) {
					baseDao.insert("zq_model_library.insertModelLibrary",
							modelLibrary);
				} else {
					sbinsert.append("[").append(modelLibrary.getModel_name())
							.append("]");
				}
			} else {
				if (baseDao.queryTotal(
						"zq_model_library.getmoldelibrarybynameandid",
						modelLibrary) == 0) {
					baseDao.update("zq_model_library.updateModelLibrary",
							modelLibrary);
				} else {
					sbupdata.append("[").append(modelLibrary.getModel_name())
							.append("]");
				}
			}
		}
		sbreturn.append("操作完毕!");
		if (sbinsert.length() > 0) {
			sbreturn.append("<br>" + sbinsert).append("已存在,不能重复插入!");
		}
		if (sbupdata.length() > 0) {
			sbreturn.append("<br>" + sbupdata).append("已存在,不能重复存在!");
		}
		return sbreturn.toString();
	}


	@RequestMapping(value = "/getMoXingKuJson", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody
	String getMoxingKuJson() {
		List<ModelLibrary> listModel = (List<ModelLibrary>) baseDao
				.query("zq_model_library.getallmoldelibrary");
		return JsonUtil.toJSONString(listModel);

	}

	private Map<String, Object> getWorklistNodeMap(String id, String title,
			String cls, boolean expanded, boolean leaf) {
		Map<String, Object> nodeMap = new HashMap<String, Object>();
		nodeMap.put("id", id);
		nodeMap.put("text", title);
		nodeMap.put("cls", cls);
		nodeMap.put("expanded", expanded);
		nodeMap.put("leaf", leaf);
		return nodeMap;
	}
	
}
