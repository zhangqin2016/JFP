package com.kspt.core.moxingku.datalist.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kspt.common.JsonUtil;
import com.kspt.common.ZqConstant;
import com.kspt.common.dao.daoImp.BaseDaoImp;
import com.kspt.core.moxingku.datalist.conponent.cache.ButtonConponentCache;
import com.kspt.core.moxingku.datalist.conponent.pojo.ButtonComponent;
import com.kspt.core.moxingku.datalist.pojo.DataList;
import com.kspt.core.moxingku.datalist.pojo.DataListButton;
import com.kspt.core.moxingku.datalist.service.DataListService;
import com.kspt.util.DataConnomImpl;

@Controller
@RequestMapping("/zq/dataList/botton")
public class DataListButtonAction {

	@Resource
	private DataListService listService;
	@Resource
	private ButtonConponentCache buttonConponentCache;
	@Resource
	private BaseDaoImp baseDao;
	@Resource
	private DataConnomImpl connomImpl;
	@RequestMapping
	public String listPage(ModelMap map, String datalistid) {
		map.put("datalistid", datalistid);
		return "moxingku/datalist/datalist_buttonlist";
	}

	@RequestMapping(value = "/gridJson", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody
	String gridJson(String datalistid) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<DataListButton> list = listService.get(datalistid)
				.getDataListButtons();
		map.put("root", list);
		map.put("totalProperty", list == null ? 0 : list.size());
		return JsonUtil.toJSONString(map);
	}


	@RequestMapping(value = "/save", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody
	String save(DataListButton dataListButton,String dataListId) {
		if(dataListButton.getId()==null||dataListButton.getId().equals("")){
			dataListButton.setOrderIndex(connomImpl.getSequence("work_list_button"));
			dataListButton.setId(connomImpl.getUUID());
		}
		DataList dataList = listService.get(dataListId);
		if(dataList==null){
			dataList=new DataList();
		}
		List<DataListButton> listButton = dataList.getDataListButtons();
		if(listButton==null){
			listButton=new ArrayList<DataListButton>();
		}
		listButton.add(dataListButton);
		dataList.setDataListButtons(listButton);
		listService.update(dataList);
		return ZqConstant.ADD_SUCCESS;
	}
	
	@RequestMapping(value = "/delete", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody
	String delete(String  ids, String dataListId) {
		String iids[]=ids.split(",");
		DataList dataList = listService.get(dataListId);
		List<DataListButton> listButton = dataList.getDataListButtons();
		for (String cid : iids) {
			for (int i = 0; i < listButton.size(); i++) {
				if (cid.equals(listButton.get(i).getId())) {
					listButton.remove(listButton.get(i));
				}
			}
		}
		dataList.setDataListButtons(listButton);
		listService.update(dataList);
		return ZqConstant.DELETE_SUCCESS;
	}

	@RequestMapping(value = "/getAddPage", method = { RequestMethod.POST,
			RequestMethod.GET })
	public String getAddPage(String id,String buttonId,String dataListId,ModelMap map) {
		if(id==null||id.equals("")){
			
		}else{
			map.put("id", id);
		}
		map.put("dataListId", dataListId);
		ButtonComponent buttonComponent= buttonConponentCache.get(buttonId);
		map.put("buttonName", buttonComponent.getButtonName());
		map.put("buttonId", buttonId);
		return buttonComponent.getButtonPage();
	}
	
	@RequestMapping(value = "/getAddButtonTreeJson", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody
	String getAddButtonTreeJson(String datalistid,String node) {
		Set<String>lb=buttonConponentCache.getAllGroup();
		List<Map<String, Object>> lm=new ArrayList<Map<String,Object>>();
		if(node.equals("root")){
			for (String string : lb) {
				lm.add(getNodeMap(string, string, "", true, false));
			}
		}else {
			List<ButtonComponent> ls=buttonConponentCache.getAll();
			for (ButtonComponent buttonComponent : ls) {
				lm.add(getNodeMap(buttonComponent.getId(), buttonComponent.getButtonName(), "", true, true));
			}
		}
		
		
		return JsonUtil.toJSONString(lm);
	}
	private Map<String, Object> getNodeMap(String id, String title,
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
