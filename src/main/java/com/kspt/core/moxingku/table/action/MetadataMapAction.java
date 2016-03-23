package com.kspt.core.moxingku.table.action;

import java.sql.SQLException;
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

import com.alibaba.fastjson.JSONArray;
import com.kspt.common.JsonUtil;
import com.kspt.common.dao.daoImp.BaseDaoImp;
import com.kspt.core.moxingku.form.pojo.FormEntityRelationshipModel;
import com.kspt.core.moxingku.form.pojo.FormUIModel;
import com.kspt.core.moxingku.table.pojo.MetadataMapModel;
import com.kspt.core.moxingku.table.pojo.MetadataModel;
import com.kspt.util.DataConnomImpl;

@RequestMapping("/moxingku/table/field")

@Controller
public class MetadataMapAction {
	@Resource
	private BaseDaoImp baseDao;
	@Resource 
	private DataConnomImpl connomImpl;
	
	@RequestMapping(method={RequestMethod.POST,RequestMethod.GET})
	public String main(String metadata_id,ModelMap map){
		map.put("metadata_id", metadata_id);
		return "moxingku/bomoxing/table_fieldList";
	}
	@RequestMapping(value="/gridJson",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String getCompanyGridJson(String metadata_id){
		Map<String, Object>map=new HashMap<String, Object>();
		List<MetadataMapModel>lc=(List<MetadataMapModel>) baseDao.query("zq_metadatamap.selectFiledByTableId",metadata_id);
		map.put("root", lc);
		map.put("totalProperty", lc.size());
		return JsonUtil.toJSONString(map);
	}
	
	@Transactional
	@RequestMapping(value="/del",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String del(String datas){
		List<MetadataMapModel> list=(List<MetadataMapModel>) JSONArray.parseArray(datas,MetadataMapModel.class);
		for (MetadataMapModel metadataMapModel : list) {
			try {
				removeBOColumn(metadataMapModel);
			} catch (SQLException e) {
				e.printStackTrace();
				continue;
			} 
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("id",  metadataMapModel.getId());
			baseDao.delete("zq_metadatamap.deleteTableField",map);
			map.clear();
			map.put("metadata_map_id", metadataMapModel.getId());
			baseDao.delete("zq_form_ui.delete",map);
		
		}
		return "删除成功!";
	}
	
	
	@RequestMapping(value="/save",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String save(String datas,String metadata_id){
		List<MetadataMapModel> list=(List<MetadataMapModel>) JSONArray.parseArray(datas,MetadataMapModel.class);
		return getReturnValue(list,metadata_id);
	}
	
	@Transactional
	private String getReturnValue(List<MetadataMapModel> list,String metadata_id){
		StringBuffer sbinsert=new StringBuffer();
		StringBuffer sbreturn=new StringBuffer();
		for (MetadataMapModel metadataMapModel : list) {
			if(metadataMapModel.getId().equals("")){
				metadataMapModel.setId(connomImpl.getUUID());
				metadataMapModel.setMetadata_id(metadata_id);
				metadataMapModel.setOrder_index(connomImpl.getSequence("zq_metadatamapmap"));
				if(baseDao.queryTotal("zq_metadatamap.getmetadataByColumnName",metadataMapModel)==0){
					try {
						addBOColumn(metadataMapModel);
					} catch (SQLException e) {
						e.printStackTrace();
						continue;
					}
					baseDao.insert("zq_metadatamap.insertTableField", metadataMapModel);
					Map<String, Object>mapQuer=new HashMap<String, Object>();
					mapQuer.put("metadata_id", metadata_id);
					List<FormEntityRelationshipModel> listfer= (List<FormEntityRelationshipModel>) baseDao.query("zq_form_er_er.getFormEr", mapQuer);
					for (FormEntityRelationshipModel formEntityRelationshipModel : listfer) {
						FormUIModel formUIModel=new FormUIModel();
						formUIModel.setDefault_value("");
						formUIModel.setForm_er_id(formEntityRelationshipModel.getId());
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
						formUIModel.setUi_type(metadataMapModel.getColumn_type());
						baseDao.insert("zq_form_ui.insertFormUI", formUIModel);
					}
					
				}else{
					sbinsert.append("[").append(metadataMapModel.getColumn_name()).append("]");
				}
			}else{
				try {
					MetadataMapModel metadataMapModel2=(MetadataMapModel) baseDao.queryOne("zq_metadatamap.selectFiledById", metadataMapModel.getId());
					metadataMapModel.setColumn_name(metadataMapModel2.getColumn_name());
					modifyBOColumn(metadataMapModel);
				} catch (SQLException e) {
					e.printStackTrace();
				continue;
				}
				baseDao.update("zq_metadatamap.updateTableField", metadataMapModel);
			}
		}
		sbreturn.append("操作完毕!列名不允许修改");
		if (sbinsert.length()>0) {
			sbreturn.append("<br>"+sbinsert).append("已存在列名,不能重复插入!");
		}
		return sbreturn.toString();
	}
	
	@Transactional
	public void addBOColumn(MetadataMapModel model) throws SQLException {
		// TODO Auto-generated method stub
		String supply = baseDao.getSupply();
		MetadataModel metadataModel = (MetadataModel) baseDao.queryOne("zq_metadata.getmetadataById", model.getMetadata_id());
		StringBuffer sql = new StringBuffer();
		if (supply.equals(BaseDaoImp.SUPPLY_ORACLE)) {
			sql.append("ALTER TABLE ").append(metadataModel.getTable_name()).append(" ADD (").append(model.getColumn_name());
		} else {
			sql.append("ALTER TABLE ").append(metadataModel.getTable_name()).append(" ADD ").append(model.getColumn_name());
		}
		sql.append(getColumnSql(supply, model));
		if (supply.equals(BaseDaoImp.SUPPLY_ORACLE)) {
			sql.append(")");
		}
		baseDao.update("data_base_common.runUpdateSql", sql.toString());
	}

	@Transactional
	public void modifyBOColumn(MetadataMapModel model) throws SQLException {
		// TODO Auto-generated method stub
		String supply = baseDao.getSupply();
		MetadataModel metadataModel = (MetadataModel) baseDao.queryOne("zq_metadata.getmetadataById", model.getMetadata_id());
		StringBuffer sql = new StringBuffer();
		if (supply.equals(BaseDaoImp.SUPPLY_ORACLE)) {
			sql.append("ALTER TABLE ").append(metadataModel.getTable_name()).append(" MODIFY (").append(model.getColumn_name());
		} else if (supply.equals(BaseDaoImp.SUPPLY_SQLSERVER)) {
			sql.append("ALTER TABLE ").append(metadataModel.getTable_name()).append(" ALTER COLUMN ").append(model.getColumn_name());
		} else if (supply.equals(BaseDaoImp.SUPPLY_MYSQL)) {
			sql.append("ALTER TABLE ").append(metadataModel.getTable_name()).append(" MODIFY ").append(model.getColumn_name());
		}
		sql.append(getColumnSql(supply, model));
		if (supply.equals(BaseDaoImp.SUPPLY_ORACLE)) {
			sql.append(")");
		}
		baseDao.update("data_base_common.runUpdateSql", sql.toString());
	}

	public void removeBOColumn(MetadataMapModel model) throws SQLException {
		String supply = baseDao.getSupply();
		MetadataModel metadataModel = (MetadataModel) baseDao.queryOne("zq_metadata.getmetadataById", model.getMetadata_id());
		StringBuffer sql = new StringBuffer();
		if (supply.equals(BaseDaoImp.SUPPLY_ORACLE)) {
			sql.append("ALTER TABLE ").append(metadataModel.getTable_name()).append(" drop column ").append(model.getColumn_name());
		} else if (supply.equals(BaseDaoImp.SUPPLY_SQLSERVER)) {
			sql.append("ALTER TABLE ").append(metadataModel.getTable_name()).append(" drop column ").append(model.getColumn_name());
		} else if (supply.equals(BaseDaoImp.SUPPLY_MYSQL)) {
			sql.append("ALTER TABLE ").append(metadataModel.getTable_name()).append(" DROP ").append(model.getColumn_name()).append("");
		}
		baseDao.update("data_base_common.runUpdateSql", sql.toString());
		
	}

	protected String getColumnSql(String supply, MetadataMapModel model) {
		StringBuffer sql = new StringBuffer();
		if (model.getColumn_type().equals("文本")) {
			if (supply.equals(BaseDaoImp.SUPPLY_ORACLE)) {
				sql.append(" NVARCHAR2(").append(model.getColumn_length()).append(") ");
			} else if (supply.equals(BaseDaoImp.SUPPLY_SQLSERVER)) {
				sql.append(" VARCHAR(").append(model.getColumn_length()).append(") ");
			} else if (supply.equals(BaseDaoImp.SUPPLY_MYSQL)) {
				if (Integer.parseInt(model.getColumn_length()) > 255) {
					sql.append("  text  ");
				} else {
					sql.append(" VARCHAR(").append(model.getColumn_length()).append(") ");
				}
			}
		} else if (model.getColumn_type().equals("数值")) {
			if (supply.equals(BaseDaoImp.SUPPLY_ORACLE)) {
				if (model.getColumn_length().equals("0")) {
					sql.append(" NUMBER(17) ");
				} else if (model.getColumn_type().indexOf(",") != -1) {
					String fLenth = model.getColumn_length().substring(0, model.getColumn_length().indexOf(","));
					String bLenth = model.getColumn_length().substring(model.getColumn_length().indexOf(",") + 1, model.getColumn_length().length());
					sql.append(" NUMBER(").append(fLenth + "," + bLenth).append(") ");
				} else {
					sql.append(" NUMBER(").append(model.getColumn_length()).append(") ");
				}
			} else if (supply.equals(BaseDaoImp.SUPPLY_SQLSERVER)) {
				if (model.getColumn_length().equals("0")) {
					sql.append(" NUMERIC(17) ");
				} else if (model.getColumn_length().indexOf(",") != -1) {
					String fLenth = model.getColumn_length().substring(0, model.getColumn_length().indexOf(","));
					String bLenth = model.getColumn_length().substring(model.getColumn_length().indexOf(",") + 1, model.getColumn_length().length());
					sql.append(" NUMERIC(").append(fLenth + "," + bLenth).append(") ");
				} else {
					sql.append(" NUMERIC(").append(model.getColumn_length()).append(") ");
				}
			} else if (supply.equals(BaseDaoImp.SUPPLY_MYSQL)) {
				if (model.getColumn_length().equals("0")) {
					sql.append(" NUMERIC  ");
				} else if (model.getColumn_length().indexOf(",") != -1) {
					String fLenth = model.getColumn_length().substring(0, model.getColumn_length().indexOf(","));
					String bLenth = model.getColumn_length().substring(model.getColumn_length().indexOf(",") + 1, model.getColumn_length().length());
					sql.append(" NUMERIC(").append(fLenth + "," + bLenth).append(") ");
				} else {
					sql.append(" NUMERIC(").append(model.getColumn_length()).append(") ");
				}
			}
		} else if (model.getColumn_type().equals("日期")) {
			if (supply.equals(BaseDaoImp.SUPPLY_ORACLE)) {
				sql.append(" DATE ");
			} else if (supply.equals(BaseDaoImp.SUPPLY_SQLSERVER)) {
				sql.append(" DATETIME ");
			} else if (supply.equals(BaseDaoImp.SUPPLY_MYSQL)) {
				sql.append(" DATETIME ");
			}
		}
		return sql.toString();
	}

}
