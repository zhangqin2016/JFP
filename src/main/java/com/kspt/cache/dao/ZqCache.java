package com.kspt.cache.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.kspt.cache.model.DBCacheModel;
import com.kspt.common.SpringContextUtil;
import com.kspt.common.ZQResourceLoaderPath;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.SearchAttribute;
import net.sf.ehcache.config.Searchable;
import net.sf.ehcache.search.Attribute;
import net.sf.ehcache.search.Query;

public class ZqCache {

	static {
		ZQResourceLoaderPath path = (ZQResourceLoaderPath) SpringContextUtil.getBean("ZQResourceLoaderPath");
		FileInputStream in = null;
		try {
			in = new FileInputStream(new File(path.getConfig() + File.separator + "ehcache.xml"));
			CacheManager.create(in);
		} catch (CacheException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		System.out.println("缓存系统正在启动...");
	}

	public static Cache registCache(@SuppressWarnings("rawtypes") Class className, String[] searchAttributes) {
		CacheManager cacheManager = CacheManager.getInstance();
		cacheManager.removeCache(className.getName());
		CacheConfiguration cacheConfig = new CacheConfiguration(className.getName(), 99999).eternal(true);
		if (searchAttributes != null && searchAttributes.length > 0) {
			Searchable searchable = new Searchable();
			for (int i = 0; i < searchAttributes.length; i++) {
				if (searchAttributes[i] != null && searchAttributes[i].length() > 0) {
					searchable.addSearchAttribute(new SearchAttribute().name(searchAttributes[i]));
				}
			}
			cacheConfig.addSearchable(searchable);
		}

		Cache cache = new Cache(cacheConfig);
		cacheManager.addCache(cache);
		return cache;
	}

	@SuppressWarnings("rawtypes")
	public static Cache getCache(Class className) {
		String name = className.getName();
		return CacheManager.getInstance().getCache(name);
	}

	public static void addSearchAttribute(Cache cache, Query query, Map<String, DBCacheModel> keys) {
		if (keys != null) {
			Set<Map.Entry<String, DBCacheModel>> set = keys.entrySet();
			for (Iterator<Map.Entry<String, DBCacheModel>> it = set.iterator(); it.hasNext();) {
				Map.Entry<String, DBCacheModel> entry = (Map.Entry<String, DBCacheModel>) it.next();
				Attribute<Object> key = cache.getSearchAttribute(entry.getKey());
				if (key != null && entry.getValue() != null) {
					if (entry.getValue().getSymbol() == null || entry.getValue().getSymbol().equals("")) {
						query.addCriteria(key.eq(entry.getValue().getValue()));
					} else if (entry.getValue().getSymbol().equals("like")) {
						query.addCriteria(key.ilike(entry.getValue().getValue().toString()));
					} else {
						query.addCriteria(key.eq(entry.getValue().getValue()));
					}
				}
			}
		}
	}
}
