package com.kspt.core.moxingku.datalist.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.kspt.cache.model.DBCacheModel;
import com.kspt.common.SpringContextUtil;
import com.kspt.common.UtilFile;
import com.kspt.common.XmlUtil;
import com.kspt.common.ZQResourceLoaderPath;
import com.kspt.common.dao.daoImp.BaseDaoImp;
import com.kspt.core.moxingku.datalist.cache.DataListCache;
import com.kspt.core.moxingku.datalist.conponent.ButtonAddInterface;
import com.kspt.core.moxingku.datalist.conponent.cache.ButtonConponentCache;
import com.kspt.core.moxingku.datalist.conponent.pojo.ButtonComponent;
import com.kspt.core.moxingku.datalist.pojo.DataList;
import com.kspt.core.moxingku.datalist.util.DataListComponent;
import com.kspt.util.DataConnomImpl;

@Service("DataListService")
public class DataListService {
	@Resource
	private BaseDaoImp baseDao;
	@Resource
	private DataListCache cache;
	@Resource
	private	ResourceLoader res;
	@Resource
	private ZQResourceLoaderPath resourceLoaderPath;
	@Resource 
	private DataConnomImpl connomImpl;
	@Resource 
	private DataListComponent dataListComponent;

	public String create(DataList dataList){
		String modelName=dataListComponent.getModelName(dataList);
		String id=connomImpl.getUUID();
		if(dataList.getId()!=null&&!dataList.getId().equals("")){
			id=dataList.getId();
			dataList=get(id);
		}else{
			dataList.setId(id);
			dataList.setOrder_index(connomImpl.getSequence("zq_datalist"));
		}
	
		try {
			File f=new File(resourceLoaderPath.getDataListPath()+File.separator+modelName+File.separator+id+".xml");
			if (!f.getParentFile().exists()){
				f.getParentFile().mkdirs();
			}
			f.createNewFile();
			XmlUtil.writeToXml(dataList, resourceLoaderPath.getDataListPath()+File.separator+modelName+File.separator+id+".xml");
			cache.put(dataList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		return id;
	}

	
	public List<DataList> getAll() {
		// TODO Auto-generated method stub
		return cache.getAll();
	}
	public void update(DataList dataList) {
		create(dataList);
	}
	
	public DataList get(String id) {
		// TODO Auto-generated method stub
		return cache.get(id);
	}

	
	public void delete(String id) {
		DataList dataList=get(id);
		String modelName=dataListComponent.getModelName(dataList);
		try {
				//得到列表配置文件
				File f=new File(resourceLoaderPath.getDataListPath()+File.separator+modelName+File.separator+id+".xml");
				//得到列表表格文件
				File f2=new File(resourceLoaderPath.getDataGrid()+File.separator+modelName+File.separator+id+".html");
				if(f.exists()){
					f.delete();
				}
				if(f2.exists()){
					f2.delete();
				}
				cache.remove(id);
				//得到列表配置目录
				File fileMulu=new File(resourceLoaderPath.getDataListPath()+File.separator+modelName+File.separator);
				List<File>ls=UtilFile.getAllFile(fileMulu, "xml");
				if(ls.size()==0){
					UtilFile.deleteFile(fileMulu);
					fileMulu.delete();
				}
				//得到列表表格目录
				File fileMulu2=new File(resourceLoaderPath.getDataGrid()+File.separator+modelName+File.separator);
				List<File>ls2=UtilFile.getAllFile(fileMulu2, "html");
				if(ls2.size()==0){
					UtilFile.deleteFile(fileMulu2);
					fileMulu2.delete();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	
	public List<DataList> getDataListByModelLib(String modelLibraryId) {
		Map<String, DBCacheModel> keys = new HashMap<String, DBCacheModel>();
		DBCacheModel db = new DBCacheModel();
		db.setValue(modelLibraryId);
		keys.put("modelLibraryId", db);
		return cache.query(keys);
	}
	
	public List<DataList> getDataListByModelLibAndViewType(String modelLibraryId,String dataListType) {
		Map<String, DBCacheModel> keys = new HashMap<String, DBCacheModel>();
		DBCacheModel db = new DBCacheModel();
		db.setValue(modelLibraryId);
		DBCacheModel db2 = new DBCacheModel();
		db2.setValue(dataListType);
		keys.put("modelLibraryId", db);
		keys.put("dataListType", db2);
		return cache.query(keys);
	}

	
	public List<DataList> getDataListByModelLibAndName(String modelLibraryId, String Name) {
		Map<String, DBCacheModel> keys = new HashMap<String, DBCacheModel>();
		DBCacheModel db = new DBCacheModel();
		db.setValue(modelLibraryId);
		keys.put("modelLibraryId", db);
		List<DataList> list=cache.query(keys);
		List<DataList> list2=new ArrayList<DataList>();
		for (DataList workListViewModel : list) {
			if(workListViewModel.getName().indexOf(Name)!=-1){
				list2.add(workListViewModel);
			}
		}
		return list2;
	}


	
	public List<DataList> getByListType(boolean isProcess) {
			Map<String, DBCacheModel> keys = new HashMap<String, DBCacheModel>();
			DBCacheModel db = new DBCacheModel();
			db.setValue(isProcess);
			keys.put("isProcess", db);
			return cache.query(keys);
		}
	@Resource
	private ButtonConponentCache buttonCache;
	public ButtonAddInterface  getButtonComponent(String id) {
		ButtonComponent model = buttonCache.get(id);
		if (model != null) {
			// TODO Auto-generated method stub
			Class<?> el = null;
			try {
				el = Class.forName(model.getImpClass());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (el != null) {
				Object obj =  SpringContextUtil.getBean(el);
				if (obj != null) {
					return (ButtonAddInterface) obj;
				}
			}
		}
		return null;
	}
}
