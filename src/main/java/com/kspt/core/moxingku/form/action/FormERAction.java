package com.kspt.core.moxingku.form.action;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kspt.common.JsonUtil;
import com.kspt.common.dao.daoImp.BaseDaoImp;
import com.kspt.core.moxingku.form.pojo.FormEntityRelationshipModel;
import com.kspt.core.moxingku.form.pojo.FormModel;
import com.kspt.core.moxingku.form.pojo.FormUIModel;
import com.kspt.core.moxingku.table.pojo.MetadataMapModel;
import com.kspt.core.moxingku.table.pojo.MetadataModel;
import com.kspt.util.DataConnomImpl;

@RequestMapping("/moxingku/form/linkTable/er")
@Controller
public class FormERAction {
	@Resource
	private BaseDaoImp baseDao;
	@Resource 
	private DataConnomImpl connomImpl;
	@RequestMapping(value="/editTable",method={RequestMethod.GET})
	public String editTable(String form_id,ModelMap map){
		map.put("form_id", form_id);
		FormModel form=	(FormModel) baseDao.queryOne("zq_form.getFormById", form_id);
		map.put("type", form.getType());
		return "moxingku/formmoxing/form_linkTable_editTable";
	}

	@RequestMapping(value="/addTable",method={RequestMethod.GET})
	public String addTable(String form_id,String form_er_id,ModelMap map,String isMain,int type){
		map.put("form_id", form_id);
		map.put("form_er_id", form_er_id);
		map.put("isMain", isMain);
		map.put("type",type);
		if(type==0){
			return "moxingku/formmoxing/form_linkTable_addTable";
		}else{
			return "moxingku/formmoxing/form_linkTable_addSqlTable";
		}
	}
	@RequestMapping(value="/treeJson",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String getTreeJson(String node,String form_id){
		Map<String, Object>map=new HashMap<String, Object>();
		List<Map<String, Object>> listm=new ArrayList<Map<String,Object>>();
		if(node.equals("root")){
			Map<String, Object>canshuMap=new HashMap<String, Object>();
			canshuMap.put("form_id", form_id);
			canshuMap.put("er_type", 0);
			FormEntityRelationshipModel entityRelationshipModel=(FormEntityRelationshipModel) baseDao.queryOne("zq_form_er_er.getFormEr", canshuMap);
			if(entityRelationshipModel!=null){
				Map<String, Object> nodeMap=new HashMap<String, Object>();
				nodeMap.put("id", "FB_"+entityRelationshipModel.getId());
				if(entityRelationshipModel.getTable_name()==null&&entityRelationshipModel.getMetadata_id()!=null){
				MetadataModel metadataModel= (MetadataModel) baseDao.queryOne("getmetadataById", entityRelationshipModel.getMetadata_id()); 
				nodeMap.put("text",metadataModel.getTable_title()+"("+metadataModel.getTable_name()+")");
				}else{
					nodeMap.put("text",entityRelationshipModel.getGrid_title()+"("+entityRelationshipModel.getTable_name()+")");
				}
				nodeMap.put("cls", "treeNodeOrg");
				nodeMap.put("expanded", true);
				nodeMap.put("leaf", false);
				listm.add(nodeMap);
			}
		}else if(node.startsWith("FB_")){
			Map<String, Object>canshuMap=new HashMap<String, Object>();
			canshuMap.put("form_id", form_id);
			canshuMap.put("er_type", 1);
			List<FormEntityRelationshipModel> listEntityRelationshipModel=(List<FormEntityRelationshipModel>) baseDao.query("zq_form_er_er.getFormEr", canshuMap);
			if(listEntityRelationshipModel!=null&&listEntityRelationshipModel.size()>0){
				for (FormEntityRelationshipModel formEntityRelationshipModel : listEntityRelationshipModel) {
					Map<String, Object> nodeMap=new HashMap<String, Object>();
					nodeMap.put("id","SB_"+formEntityRelationshipModel.getId());
					if(formEntityRelationshipModel.getTable_name()==null&&formEntityRelationshipModel.getMetadata_id()!=null){
						MetadataModel metadataModel= (MetadataModel) baseDao.queryOne("getmetadataById", formEntityRelationshipModel.getMetadata_id()); 
						nodeMap.put("text",metadataModel.getTable_title()+"("+metadataModel.getTable_name()+")");
						}else{
							nodeMap.put("text",formEntityRelationshipModel.getGrid_title()+"("+formEntityRelationshipModel.getTable_name()+")");
						}
					nodeMap.put("cls", "treeNodeOrg");
					nodeMap.put("leaf", true);
					listm.add(nodeMap);
				}
			}

		}
		map.put("root", listm);
		return JsonUtil.toJSONString(map);
	}
	@Transactional
	@RequestMapping(value="/insertTable",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String addTable(String table_id,int isMain,String form_id,String form_er_id,String table_name,String table_title,int type){
		Map<String, Object> mapfo=new HashMap<String, Object>();
		mapfo.put("form_id", form_id);
		if(isMain==0){
			mapfo.put("er_type", isMain);
			int countFb=baseDao.queryTotal("zq_form_er_er.queryCount", mapfo);
			if(countFb>0){
				return "只能绑定一张主表!";
			}
		}else{
			int countSb=0;
			int countSb2=0;
			if(table_id!=null){
				mapfo.put("metadata_id", table_id);
				countSb=baseDao.queryTotal("zq_form_er_er.queryCount", mapfo);
			}
			mapfo=new HashMap<String, Object>();
			if(table_name!=null){
				mapfo.put("table_name", table_name);
				countSb2=baseDao.queryTotal("zq_form_er_er.queryCount", mapfo);
			}

			if(countSb>0||countSb2>0){
				return "不能绑定相同的表!";
			}
		}
		FormEntityRelationshipModel former=new FormEntityRelationshipModel();
		String formErId=connomImpl.getUUID();
		former.setId(formErId);
		former.setEr_type(isMain);
		former.setEr_layer(isMain+1);
		former.setForm_id(form_id);
		former.setGrid_height(300);
		if(type==0){
			MetadataModel metadataModel=(MetadataModel) baseDao.queryOne("zq_bo_metadata.getmetadataById", table_id);
			former.setGrid_title(metadataModel.getTable_title());
			former.setMetadata_id(table_id);
		}else{
			former.setTable_name(table_name);
			former.setGrid_title(table_title);
		}
		former.setPage_size(0);
		if(isMain==1){
			former.setParent_er_id(form_er_id);
		}
		former.setOrder_index(connomImpl.getSequence("zq_form_er"));
		try {
			insertFormEr(former, formErId, table_id,type,table_name);
		} catch (Exception e) {
			e.printStackTrace();
			return "绑定失败!";
		}

		return "绑定成功!";
	}

	@Transactional
	private void insertFormEr(FormEntityRelationshipModel formEntityRelationshipModel,String formErId,String table_id,int type,String table_name)throws Exception{
		baseDao.insert("zq_form_er_er.insertFormER", formEntityRelationshipModel);
		if(type==0){
			insertBoFormUi(formErId, table_id);
		}else{
			insertSqlFormUi(table_name,formErId);
		}

	}

	private void insertBoFormUi(String formErId,String table_id)throws Exception{
		List<MetadataMapModel> listMetadataMapModel=(List<MetadataMapModel>) baseDao.query("zq_metadatamap.selectFiledByTableId", table_id);
		for (MetadataMapModel metadataMapModel : listMetadataMapModel) {
			FormUIModel formUIModel=new FormUIModel();
			formUIModel.setDefault_value("");
			formUIModel.setForm_er_id(formErId);
			formUIModel.setId(connomImpl.getUUID());
			formUIModel.setIs_display("显示");
			formUIModel.setIs_edit(true);
			formUIModel.setIs_null(true);
			formUIModel.setMetadata_map_id(metadataMapModel.getId());
			formUIModel.setOrder_index(connomImpl.getSequence("zq_form_ui"));
			formUIModel.setUi_html("");
			formUIModel.setUi_length("100");
			formUIModel.setUi_length_type("px");
			formUIModel.setUi_name(metadataMapModel.getColumn_name());
			formUIModel.setUi_param("");
			formUIModel.setUi_title(metadataMapModel.getColumn_title());
			if(metadataMapModel.getColumn_type().equals("数值")){
				formUIModel.setUi_type("文本");
			}else{
				formUIModel.setUi_type(metadataMapModel.getColumn_type());
			}

			baseDao.insert("zq_form_ui.insertFormUI", formUIModel);
		}
	}

	private void insertSqlFormUi(String table_name,String former_id)throws Exception{
		Connection connection=null;
		connection=baseDao.getConnection();
		/**
		 *  TABLE_CAT String => 表类别（可为 null） 
			TABLE_SCHEM String => 表模式（可为 null） 
			TABLE_NAME String => 表名称 
			COLUMN_NAME String => 列名称 
			DATA_TYPE int => 来自 java.sql.Types 的 SQL 类型 
			TYPE_NAME String => 数据源依赖的类型名称，对于 UDT，该类型名称是完全限定的 
			COLUMN_SIZE int => 列的大小。 
			BUFFER_LENGTH 未被使用。 
			DECIMAL_DIGITS int => 小数部分的位数。对于 DECIMAL_DIGITS 不适用的数据类型，则返回 Null。 
			NUM_PREC_RADIX int => 基数（通常为 10 或 2） 
			NULLABLE int => 是否允许使用 NULL。 
			columnNoNulls - 可能不允许使用 NULL 值 
			columnNullable - 明确允许使用 NULL 值 
			columnNullableUnknown - 不知道是否可使用 null 
			REMARKS String => 描述列的注释（可为 null） 
			COLUMN_DEF String => 该列的默认值，当值在单引号内时应被解释为一个字符串（可为 null） 
			SQL_DATA_TYPE int => 未使用 
			SQL_DATETIME_SUB int => 未使用 
			CHAR_OCTET_LENGTH int => 对于 char 类型，该长度是列中的最大字节数 
			ORDINAL_POSITION int => 表中的列的索引（从 1 开始） 
			IS_NULLABLE String => ISO 规则用于确定列是否包括 null。 
			YES --- 如果参数可以包括 NULL 
			NO --- 如果参数不可以包括 NULL 
			空字符串 --- 如果不知道参数是否可以包括 null 
			SCOPE_CATLOG String => 表的类别，它是引用属性的作用域（如果 DATA_TYPE 不是 REF，则为 null） 
			SCOPE_SCHEMA String => 表的模式，它是引用属性的作用域（如果 DATA_TYPE 不是 REF，则为 null） 
			SCOPE_TABLE String => 表名称，它是引用属性的作用域（如果 DATA_TYPE 不是 REF，则为 null） 
			SOURCE_DATA_TYPE short => 不同类型或用户生成 Ref 类型、来自 java.sql.Types 的 SQL 类型的源类型（如果 DATA_TYPE 不是 DISTINCT 或用户生成的 REF，则为 null） 
			IS_AUTOINCREMENT String => 指示此列是否自动增加 
			YES --- 如果该列自动增加 
			NO --- 如果该列不自动增加 
			空字符串 --- 如果不能确定该列是否是自动增加参数 
		 */
		DatabaseMetaData databaseMetaData =connection.getMetaData();
		ResultSet columnSet = databaseMetaData.getColumns(null, "%",
				table_name, "%");
		while (columnSet.next()){
			System.out.println(columnSet.getString("DATA_TYPE"));
			System.out.println(columnSet.getString("TYPE_NAME"));
			FormUIModel formUIModel=new FormUIModel();
			formUIModel.setForm_er_id(former_id);
			formUIModel.setId(connomImpl.getUUID());
			formUIModel.setIs_display("显示");
			formUIModel.setIs_edit(true);
			formUIModel.setIs_null(true);
			formUIModel.setOrder_index(connomImpl.getSequence("zq_form_ui"));
			formUIModel.setUi_html("");
			formUIModel.setUi_length("100");
			formUIModel.setUi_length_type("px");
			formUIModel.setUi_name(columnSet.getString("COLUMN_NAME"));
			formUIModel.setUi_param("");
			formUIModel.setUi_title(columnSet.getString("REMARKS"));
			if(columnSet.getString("TYPE_NAME").equals("timestamp")){
				formUIModel.setUi_type("日期");
			}else{
				formUIModel.setUi_type("文本");
			}
			
			baseDao.insert("zq_form_ui.insertFormUI", formUIModel);
		}
	}

	@Transactional
	@RequestMapping(value="/deleteFormEr",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String deleteFormEr(String form_er_id){
		if(form_er_id.startsWith("FB_")){
			Map<String, Object> mapfo=new HashMap<String, Object>();
			mapfo.put("parent_er_id", form_er_id.replace("FB_", ""));
			int countFb=baseDao.queryTotal("zq_form_er_er.queryCount", mapfo);
			if(countFb>0){
				return "请先删除子表数据";
			}else{
				baseDao.delete("zq_form_er_er.deleteFormERbyId",  form_er_id.replace("FB_", ""));
				Map<String, Object> mapdeleui=new HashMap<String, Object>();
				mapdeleui.put("form_er_id", form_er_id.replace("FB_", ""));
				baseDao.delete("zq_form_ui.delete",mapdeleui);
			}
		}else if(form_er_id.startsWith("SB_")){
			baseDao.delete("zq_form_er_er.deleteFormERbyId",  form_er_id.replace("SB_", ""));
			Map<String, Object> mapdeleui=new HashMap<String, Object>();
			mapdeleui.put("form_er_id", form_er_id.replace("SB_", ""));
			baseDao.delete("zq_form_ui.delete",mapdeleui);
		}
		return "删除成功!";
	}

}