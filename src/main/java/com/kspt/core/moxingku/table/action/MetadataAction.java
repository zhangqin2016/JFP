package com.kspt.core.moxingku.table.action;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.kspt.common.JsonUtil;
import com.kspt.common.dao.daoImp.BaseDaoImp;
import com.kspt.core.moxingku.table.pojo.MetadataModel;
import com.kspt.util.DataConnomImpl;

@RequestMapping("/moxingku/table")

@Controller
public class MetadataAction {
	@Resource
	private BaseDaoImp baseDao;
	@Resource 
	private DataConnomImpl connomImpl;
	@RequestMapping(method={RequestMethod.POST,RequestMethod.GET})
	public String getrenyuan_companygrid(String model_lib_id,ModelMap map){
		map.put("model_lib_id", model_lib_id);
		return "moxingku/bomoxing/table_list";
	}
	@RequestMapping(value="/gridJson",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String getCompanyGridJson(String model_lib_id){
		Map<String, Object>map=new HashMap<String, Object>();
		List<MetadataModel>listModel=(List<MetadataModel>) baseDao.query("zq_bo_metadata.getmetadataByModellibid",model_lib_id);
		map.put("root", listModel);
		map.put("totalProperty", listModel.size());
		return JsonUtil.toJSONString(map);
	}
	
	@RequestMapping(value="/del",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String del(String []tableNames){
		for (int i = 0; i < tableNames.length; i++) {
			try {
				deleteTable(tableNames[i]);
			} catch (SQLException e) {
				e.printStackTrace();
					return "删除失败!";
			} 
			baseDao.delete("zq_bo_metadata.deleteMetadata", tableNames[i]);
		}
		return "删除成功!";
	}
	@RequestMapping(value="/save",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String save(String datas,String model_lib_id){
		List<MetadataModel> list=(List<MetadataModel>) JSONArray.parseArray(datas,MetadataModel.class);
		return getReturnValue(list,model_lib_id);
	}
	
	/**
	 * 得到插入更新操作并返回值
	 * @param list
	 * @return 
	 */
	private String getReturnValue(List<MetadataModel> list,String model_lib_id){
		StringBuffer sbinsert=new StringBuffer();
		StringBuffer sbreturn=new StringBuffer();
		for (MetadataModel metadataModel : list) {
			if(metadataModel.getId().equals("")){
				metadataModel.setId(connomImpl.getUUID());
				metadataModel.setModel_lib_id(model_lib_id);
				metadataModel.setOrder_index(connomImpl.getSequence("zq_bo_metadata"));
				metadataModel.setTable_name("bo_"+metadataModel.getTable_name());
				if(baseDao.queryTotal("zq_bo_metadata.getmetadataByTableName",metadataModel.getTable_name())==0){
					try {
						updateSql(metadataModel);
					} catch (SQLException e) {
						e.printStackTrace();
						return "创建数据库失败";
					}
					baseDao.insert("zq_bo_metadata.insertMetadata", metadataModel);
				
					
				}else{
					sbinsert.append("[").append(metadataModel.getTable_name()).append("]");
				}
			}else{
				baseDao.update("zq_bo_metadata.updataMetadata", metadataModel);
			}
		}
		sbreturn.append("操作完毕!表名不允许修改");
		if (sbinsert.length()>0) {
			sbreturn.append("<br>"+sbinsert).append("已存在表名,不能重复插入!");
		}
		return sbreturn.toString();
	}
	private void updateSql(MetadataModel metadataModel) throws SQLException{
		String supply = baseDao.getSupply();
		StringBuffer sql = new StringBuffer();
		if (supply.equals(baseDao.SUPPLY_ORACLE)) {
			String sqlseq = "create sequence " + metadataModel.getTable_name() + "_id_seq increment by 1 start with 1";
			baseDao.update("data_base_common.runUpdateSql", sqlseq);
			sql.append("create table ").append(metadataModel.getTable_name()).append("(id number ,").append("uuid nvarchar2(128)  not null,").append("org_id nvarchar2(128) default '' not null,").append("bind_id nvarchar2(128) default '' not null ,").append("uu_id nvarchar2(128) default '' not null ,")
					.append("create_date date default sysdate not null,").append("createuser nvarchar2(30) default '' not null ,").append("update_date date default sysdate not null,").append("update_user nvarchar2(30),").append("primary key(id))");
			baseDao.update("data_base_common.runUpdateSql", sql.toString());
		} else if (supply.equals(baseDao.SUPPLY_SQLSERVER)) {
			sql.append("create table ").append(metadataModel.getTable_name()).append("(id numeric(12)  identity(1,1),").append("uuid varchar(128) default '' not null,").append("org_id varchar(128) default '' not null,").append("bind_id varchar(128)  default '' not null ,")
					.append("create_date datetime default getdate() not null,").append("create_user varchar(30) default '' not null,").append("update_date datetime default getdate() not null,").append("update_user varchar(30)  default '' not null,").append("primary key(id))");
			baseDao.update("data_base_common.runUpdateSql", sql.toString());
		} else if (supply.equals(baseDao.SUPPLY_MYSQL)) {
			Connection conn = null;
			String datetime = " datetime default 'now()' not null";
			try {
				conn = baseDao.getConnection();
				if (conn.getMetaData().getDatabaseMajorVersion() == 5) {
					datetime = " timestamp";
				}
			} catch (Exception e) {
			} finally {
				conn.close();
			}

			sql.append("create table ").append(metadataModel.getTable_name()).append("(id int(12)  auto_increment not null,").append("bind_id varchar(128),")
					.append("create_date " + datetime + ",").append("create_user varchar(30),").append("update_date " + datetime + ",").append("update_user varchar(30),").append("primary key(id))");
			baseDao.update("data_base_common.runUpdateSql", sql.toString());
		} else {
			throw new SQLException("不支持数据库类型");
		}
		
	}
	private void deleteTable(String table_name) throws SQLException{
		/*String selsql="select count(id) as c from "+table_name+"";
		int num=Integer.parseInt(baseDao.query("zq_bo_metadata.runSelectFieldSql", selsql).toString());
		if(num>0){
			
		}*/
	String sql = "drop table " + table_name;
	baseDao.update("data_base_common.runUpdateSql", sql.toString());
	}
}