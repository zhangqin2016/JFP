package com.kspt.core.run.action;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kspt.common.JsonUtil;
import com.kspt.common.MathUtil;
import com.kspt.common.UtilDate;
import com.kspt.common.UtilFile;
import com.kspt.common.ZQResourceLoaderPath;
import com.kspt.common.dao.daoImp.BaseDaoImp;
import com.kspt.core.moxingku.datalist.pojo.DataList;
import com.kspt.core.moxingku.datalist.pojo.DataListColumn;
import com.kspt.core.moxingku.datalist.pojo.DataListQuery;
import com.kspt.core.moxingku.datalist.service.DataListService;
import com.kspt.core.moxingku.datalist.tool.DatalistQueryTool;
import com.kspt.core.moxingku.datalist.util.DataListComponent;
import com.kspt.core.moxingku.form.engine.dao.impl.FormEngineDaoImpl;
import com.kspt.core.moxingku.form.engine.dao.impl.FormUiDaoImpl;
import com.kspt.core.moxingku.form.pojo.FormEntityRelationshipModel;
import com.kspt.core.moxingku.form.pojo.FormModel;
import com.kspt.core.moxingku.form.pojo.FormUIModel;
import com.kspt.core.moxingku.pojo.ModelLibrary;
import com.kspt.core.moxingku.table.pojo.MetadataModel;
import com.kspt.model.OrgUser;
import com.kspt.util.DataConnomImpl;

@RequestMapping("/run")
@Controller
public class RunFormAction {
	@Resource
	private BaseDaoImp baseDao;
	@Resource
	private DataConnomImpl connomImpl;
	@Resource
	private FormEngineDaoImpl formEngineDaoImpl;
	@Resource
	private DatalistQueryTool datalistQueryTool;
	@Resource
	private ZQResourceLoaderPath zQResourceLoaderPath;
	@Resource
	private  DataListService dataListService;

	@Resource
	private DataListComponent dataListComponent;
	@RequestMapping(value = "/form/page", method = { RequestMethod.POST,
			RequestMethod.GET })
	public String page(String datalistId, ModelMap putValue,HttpServletRequest request,String id) {
		/*GroupTemplate groupTemplate = beetlGroupUtilConfiguration
				.getGroupTemplate();
		Template t;*/
		DataList dataList=dataListService.get(datalistId);
		FormModel formModel=	(FormModel) baseDao.queryOne("zq_form.getFormById", dataList.getFormId());
		ModelLibrary modelLibrary=	(ModelLibrary) baseDao.queryOne("zq_model_library.getmoldelibrarybyid", formModel.getModel_lib_id());
		String formId=dataList.getFormId();
		String modelName=modelLibrary.getModel_name();
		String	url="custom/"+modelName+"/"+formId;
		
		Map<String, Object> record = new HashMap<String, Object>();
		record.put("er_type", 0);
		record.put("form_id", formModel.getId());
		FormEntityRelationshipModel entityRelationshipModel = (FormEntityRelationshipModel) baseDao
				.queryOne("zq_form_er_er.getFormEr", record);
		List<?> formUIModels = baseDao.query(
				"zq_form_ui.getFormUIbyFormErId",
				entityRelationshipModel.getId());
		for (Object obj : formUIModels) {
			FormUIModel formUIModel=(FormUIModel) obj;
		 		FormUiDaoImpl formUiDaoImpl = formEngineDaoImpl
			.getFormUIComponent("UI_"+formUIModel.getUi_type());
	String defaultValue="";
	defaultValue=formUIModel.getDefault_value();
	formUiDaoImpl.setValue(defaultValue==null?"":defaultValue);
	formUiDaoImpl.setFormUIModel(formUIModel);
	formUiDaoImpl.setRequest(request);
	if(formUIModel.isIs_edit()){
		putValue.put(formUIModel.getUi_name(),formUiDaoImpl.getEditableUi());
	}else{
		putValue.put(formUIModel.getUi_name(),formUiDaoImpl.getReadOnlyUi());
	} 
		}
		/*	try {
			t = groupTemplate.getTemplate(zQResourceLoaderPath
					.getWebTemplateSystemPath() + formId+".html");
			Map<String, Object> map = new HashMap<String, Object>();
			t.binding(map);
			t.render();
		} catch (IOException e) {
			e.printStackTrace();
		}*/

		//request.setAttribute("user", name);
		return url;
	}
	@RequestMapping(value = "/form/bootgrid/deleted_data", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody String deleted_data(String ids,String datalist_id){
		DataList dataList=dataListService.get(datalist_id);
		String id=dataList.getTable_id();
		dataList.getFormId();
		Map<String, Object> mapParam = new HashMap<String, Object>();
		mapParam.put("form_id", dataList.getFormId());
		mapParam.put("er_type", 0);
		FormEntityRelationshipModel formEntityRelationshipModel = (FormEntityRelationshipModel) baseDao.queryOne(
				"zq_form_er_er.getFormEr", mapParam);
		String table_name=formEntityRelationshipModel.getTable_name();
		if(table_name==null){
			MetadataModel metadataModel = (MetadataModel) baseDao.queryOne("zq_metadata.getmetadataById", formEntityRelationshipModel.getMetadata_id());
			table_name=metadataModel.getTable_name();
		}
		String[]idsa=ids.split(",");
		for (String string : idsa) {
			String sql="delete from "+table_name+" where "+id+"='"+string+"'";
			baseDao.delete("run_form.delete", sql);
		}
		return "删除成功！";
	}

	@RequestMapping(value = "/form/bootgrid/json", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody String bootgridjson(String querys,HttpServletRequest request,int current,int rowCount,String searchPhrase,String datalist_id){
		Map<String, Object> query= JSON.parseObject(querys);
		String sort="sort[id]";
		String sortShunxu="asc";
		Enumeration<?> pNames=request.getParameterNames();
		while(pNames.hasMoreElements()){
			String name=(String)pNames.nextElement();
			if(name.startsWith("sort[")){
				sort=name;
				sortShunxu=request.getParameter(name);
				break;
			}
		}
		String sql="";
		String countsql="";
		JSONObject querySql=null;
		if(searchPhrase==null||"".equals(searchPhrase.trim())){
			querySql=getSql(datalist_id,current,rowCount,sort,sortShunxu,searchPhrase,query,false);
		}else{
			querySql=getSql(datalist_id,current,rowCount,sort,sortShunxu,searchPhrase,query,true);
		}
		sql=querySql.getString("query");
		countsql=querySql.getString("queryCount");
		List<Map<String, Object>>list=  (List<Map<String, Object>>) baseDao.query("run_form.select",sql);
		DataList dataList=dataListService.get(datalist_id);
		Map<String, Object> mapParam = new HashMap<String, Object>();
		mapParam.put("form_id", dataList.getFormId());
		mapParam.put("er_type", 0);
		FormEntityRelationshipModel formEntityRelationshipModel = (FormEntityRelationshipModel) baseDao.queryOne(
				"zq_form_er_er.getFormEr", mapParam);
		for (Map<String, Object> map : list) {
			for (String key : map.keySet()) {
				Map<String, String> param=new HashMap<String, String>();
				param.put("ui_name", key);
				param.put("form_er_id", formEntityRelationshipModel.getId());
				FormUIModel formUIModel=(FormUIModel) baseDao.queryOne("zq_form_ui.getFormUIbyFormUiNameAndErId",param);
				if(formUIModel.getUi_type().equals("日期")){
					try{
						String riqi="yyyy-MM-dd HH:mm:ss";
						String uiparam=formUIModel.getUi_param();
						if(uiparam!=null&&uiparam.trim().length()!=0){
						  riqi=JSONObject.parseObject(formUIModel.getUi_param()).getString("riqi");
						riqi=riqi==null?"yyyy-MM-dd HH:mm:ss":riqi;
						}
					map.put(key,UtilDate.dateFormat(UtilDate.dateFormat(map.get(key).toString()),riqi));
				
					}catch(Exception e){
						e.printStackTrace();
						continue;
					}
				}
			}
			
		}
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("current", current);
		map.put("rowCount", rowCount);
		map.put("rows", list);
		map.put("total",(Long)baseDao.queryOne("data_base_common.runSelectFieldSql",countsql));

		return JsonUtil.toJSONString(map);
	}

	@RequestMapping(value = "/form/save", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody String save(String formObjectJson,String form_id){

		Map<String, Object> record = new HashMap<String, Object>();
		record.put("er_type", 0);
		record.put("form_id", form_id);
		FormEntityRelationshipModel entityRelationshipModel = (FormEntityRelationshipModel) baseDao
				.queryOne("zq_form_er_er.getFormEr", record);
	
		JSONObject data= JSON.parseObject(formObjectJson);
		data.put("bind_id",connomImpl.getUUID());
		//data.put("uuid", connomImpl.getUUID());
		Map<String, Object> map=new HashMap<String, Object>();
	if(entityRelationshipModel.getTable_name()==null&&entityRelationshipModel.getMetadata_id()!=null){
		MetadataModel metadataModel= (MetadataModel) baseDao.queryOne("zq_bo_metadata.getmetadataById", entityRelationshipModel.getMetadata_id());
		map.put("zq_form_table_name", metadataModel.getTable_name());
	}else{
		map.put("zq_form_table_name", entityRelationshipModel.getTable_name());
	}
		map.put("data", data);
		try{
		baseDao.insert("run_form.insert", map);
		}catch(Exception e){
			e.printStackTrace();
			return "操作失败";
		}
		return "操作成功";
	}

	@RequestMapping(value = "/datalist/page", method = { RequestMethod.POST,
			RequestMethod.GET })
	public String getBuildPage(String id) {
		try{
			return "datagrid/"+dataListComponent.getModelName(id)+"/"+id;
		}catch(Exception e){
			return "/404.html";
		}
		
	}

	private JSONObject getSql(String datalistId,int pagenum,int pagesize,String sort,String sortShunxu,String searchPhrase, Map<String, Object> query,boolean isFuzzySearch){

		List<DataListColumn> columns=dataListComponent.getDataListColumnsByDataListId(datalistId);
		StringBuffer queryData=new StringBuffer();
		StringBuffer queryCount=new StringBuffer();
		StringBuffer fuzzySearch=new StringBuffer();
		queryData.append("select ");
		for (int i = 0; i < columns.size(); i++) {
			DataListColumn dataListColumn=columns.get(i);
			queryData.append(dataListColumn.getTableField());
			if(dataListColumn.isIs_fuzzySearch()){
				fuzzySearch.append(" or ");
				fuzzySearch.append(dataListColumn.getTableField()).append(" ").append("like");
				fuzzySearch.append(" ").append("'%"+searchPhrase+"%' ");
			}
			if(i!=columns.size()-1){
				queryData.append(",");
			}else{
				queryData.append(" ");
			}
		}
		String tablename=getTableName(datalistId);
		queryCount.append("select count(ID) c from "+tablename+" where 1=1 ");
		queryData.append(" from ").append(" ");
		queryData.append(tablename).append(" where 1=1");
		//添加模糊搜索列
		if(isFuzzySearch){
			if(fuzzySearch.length()>0){
				queryData.append(" and (1=0 ");
				queryData.append(fuzzySearch);
				queryData.append("  )");
				queryCount.append(" and ( 1=0 " + fuzzySearch+" ) ");
			}
		}else{
			if(query!=null&&query.size()>0){
				queryData.append(" and ( 1=1 ");
				queryCount.append(" and ( 1=1 ");
				for (Entry<String, Object> querySql : query.entrySet()) {
					String key=querySql.getKey();
					String value=querySql.getValue().toString();
					if(value.trim().length()==0){
						continue;
					}
					DataListQuery dataListQuery=datalistQueryTool.get(datalistId, key.replace("query_", ""));
					String where =dataListQuery.getWhere();
					queryData.append(" and ");
					queryData.append(dataListQuery.getQueryField());
					queryData.append(" ").append(where);
					queryCount.append(" and ");
					queryCount.append(dataListQuery.getQueryField());    
					queryCount.append(" ").append(where); 
					if("like".equals(where)){
						queryData.append(" '%"+value+"%' ");
						queryCount.append(" '%"+value+"%' ");
					}else{
						queryCount.append(" '"+value+"' ");
						queryData.append(" '"+value+"' ");
					}

				}
				queryData.append("  ) ");
				queryCount.append("  ) ");
			}
		}
		queryData.append(" order by "+sort.substring(sort.indexOf("[")+1, sort.indexOf("]"))+" "+sortShunxu);
		if(pagesize!=-1){
			queryData.append("  limit "+((pagenum-1)*pagesize)+","+pagesize);
		}
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("query", queryData.toString());
		jsonObject.put("queryCount", queryCount.toString());
		return jsonObject;

	}

	private String getTableName(String id){
		DataList dataList=dataListService.get(id);
		String form_id=dataList.getFormId();
		Map<String, Object> record = new HashMap<String, Object>();
		record.put("er_type", 0);
		record.put("form_id", form_id);
		FormEntityRelationshipModel entityRelationshipModel = (FormEntityRelationshipModel) baseDao
				.queryOne("zq_form_er_er.getFormEr", record);
		if(entityRelationshipModel.getTable_name()==null&&entityRelationshipModel.getMetadata_id()!=null){
			MetadataModel metadataModel= (MetadataModel) baseDao.queryOne("zq_bo_metadata.getmetadataById", entityRelationshipModel.getMetadata_id()); 
			return metadataModel.getTable_name();
			}else{
			return entityRelationshipModel.getTable_name();
			}

	}

	@Resource
	ZQResourceLoaderPath loaderPath;

	@RequestMapping(value = "/form/upload", method = { RequestMethod.POST})
	public @ResponseBody String upload(HttpServletRequest request){
		MultipartHttpServletRequest httpServletRequest=(MultipartHttpServletRequest) request;
		Map<String, MultipartFile> map=httpServletRequest.getFileMap();
		MultipartFile multipartFile=map.get("file");
		/**获取文件的后缀**/    
		String name = multipartFile.getOriginalFilename();
		DateFormat dateFormat=new SimpleDateFormat("yyyyMMdd");
		String saveFile= loaderPath.getAttached()+((OrgUser)request.getSession().getAttribute("user_model")).getUserAccount()+"/"+dateFormat.format(new Date())+"/";
		File saveDirFile=new File(saveFile);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		File f=	new File(saveFile+name);
		if(f.exists()){
			saveFile=createDir(f, saveFile,name);
		}
		String saveFileUrl=saveFile.replace(loaderPath.getAttached(), "")+name;
		boolean issuccess=false;
		try {
			issuccess=	UtilFile.write(saveFile+name, multipartFile.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		StringBuffer html=new StringBuffer();
		if(issuccess){
			html.append(" <div   class=\"uploadify-queue\"> \r\n");	
			html.append("<div   class=\"uploadify-queue-item\">     \r\n");	    
			html.append("<div class=\"cancel\"> \r\n");	    
			html.append("<a href=\"#\" >X</a> \r\n");	    
			html.append("</div>\r\n");	    
			html.append("<span class=\"fileName\"><a href='"+request.getScheme()+"://"+request.getHeader("host")+request.getContextPath()+"/attached/"+saveFile.replace(loaderPath.getAttached(), "")+name+"'>"+name+"</a>("+MathUtil.convertFileSize(multipartFile.getSize())+")</span> \r\n");	    
			html.append("<span class=\"data\"></span>\r\n");	    
			html.append("</div>    \r\n");	    
			html.append("</div>    \r\n");	 
		}
		Map<String, Object> fileMap=new HashMap<String, Object>();
		fileMap.put("html", html);
		fileMap.put("value", saveFileUrl);
		return JsonUtil.toJSONString(fileMap);
	}


	private String createDir(File f,String saveFile,String name){
		String fs=saveFile+ new Random().nextInt(10)+"_"+new Random().nextInt(10)+"_"+new Random().nextInt(10)+"_"+new Random().nextInt(10)+"/";
		if(new File(fs+name).exists()){
			return createDir(f,saveFile,name);
		}else{
			new File(fs).mkdirs();
			return fs;
		}
	}

	
}