package com.kspt.core.moxingku.datalist.cache;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import net.sf.ehcache.search.Attribute;
import net.sf.ehcache.search.Direction;
import net.sf.ehcache.search.Query;
import net.sf.ehcache.search.Result;
import net.sf.ehcache.search.Results;

import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.kspt.cache.dao.ZqCache;
import com.kspt.cache.dao.impl.BaseCache;
import com.kspt.cache.model.DBCacheModel;
import com.kspt.common.UtilFile;
import com.kspt.common.XmlUtil;
import com.kspt.common.ZQResourceLoaderPath;
import com.kspt.core.moxingku.datalist.pojo.DataList;

@Service("DataListCache")
public class DataListCache implements BaseCache<DataList> {
	@Resource
	ResourceLoader res;
	@Resource
	ZQResourceLoaderPath zQResourceLoaderPath;
	
	public void put(DataList model) {
		Cache cache = ZqCache.getCache(DataListCache.class);
		cache.remove(model.getId());
		Element element = new Element(model.getId(), model);
		cache.put(element);
	}

	
	public List<DataList> getAll() {
		Cache cache = ZqCache.getCache(DataListCache.class);
		final List<DataList> list = new LinkedList<DataList>();
		Query query = cache.createQuery().includeValues();
		Attribute<Integer> order = cache.getSearchAttribute("id");
		query.addOrderBy(order, Direction.ASCENDING);
		Results rs = query.execute();
		List<Result> all = rs.all();
		for (Result r : all) {
			DataList model = (DataList) r.getValue();
			list.add(model);
		}
		query.end();
		return list;
	}

	
	public DataList get(String id) {
		Cache cache = ZqCache.getCache(DataListCache.class);
		Element element = cache.get(id);
		if (element != null) {
			return (DataList) element.getObjectValue();
		}
		return null;
	}

	
	public void remove(String id) {
		Cache cache = ZqCache.getCache(DataListCache.class);
		cache.remove(id);
	}

	
	@PostConstruct
	public void initialize() {
		String[] searchs = { "id","dataListType", "modelLibraryId", "formId", "name" };
		Cache cache = ZqCache.registCache(DataListCache.class, searchs);
		List<File> ls = getXmlName();
		if(ls!=null&&ls.size()!=0){
			for (int i = 0; i < ls.size(); i++) {
				DataList model = transformXML2Bean(ls.get(i));
				if (model != null) {
					Element element = new Element(model.getId(), model);
					cache.put(element);
				}
			}
		}
		
	}

	
	public List<DataList> query(Map<String, DBCacheModel> keys) {
		Cache cache = ZqCache.getCache(DataListCache.class);
		final List<DataList> list = new LinkedList<DataList>();
		Query query = cache.createQuery().includeValues();
		ZqCache.addSearchAttribute(cache, query, keys);
		Results rs = query.execute();
		List<Result> all = rs.all();
		for (Result r : all) {
			DataList model = (DataList) r.getValue();
			list.add(model);
		}
		query.end();
		return list;
	}

	private List<File> getXmlName() {
		String s = null;
		try {
			s = getDocCenterDir();
		} catch (IOException e) {
			e.printStackTrace();
		}
		File f = new File(s);
		return UtilFile.getAllFile(f, "xml");
	}

	private String getDocCenterDir() throws IOException {
		return zQResourceLoaderPath.getDataListPath();
	}

	private DataList transformXML2Bean(File file)  {
		DataList datalist = new DataList();
		datalist=(DataList) XmlUtil.readXmlToModel(datalist, file);
		return datalist;

	}

	
}
