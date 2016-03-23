package com.kspt.core.moxingku.form.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.kspt.common.dao.BaseDao;
import com.kspt.core.moxingku.form.pojo.FormEntityRelationshipModel;
import com.kspt.core.moxingku.form.pojo.FormUIModel;
import com.kspt.core.moxingku.table.pojo.MetadataModel;

@Component
public class FormUiService {

	@Resource
	private BaseDao baseDao;
	public String getDatalistAddListName(FormEntityRelationshipModel entityRelationshipModel,FormUIModel formUIModel){
		if(entityRelationshipModel.getTable_name()==null&&entityRelationshipModel.getMetadata_id()!=null){
			MetadataModel metadataModel= (MetadataModel) baseDao.queryOne("getmetadataById", entityRelationshipModel.getMetadata_id()); 
			return (metadataModel.getTable_name()+"("+formUIModel.getUi_name()+")");
			}else{
			return (entityRelationshipModel.getTable_name()+"("+formUIModel.getUi_name()+")");
			}
	}

}
