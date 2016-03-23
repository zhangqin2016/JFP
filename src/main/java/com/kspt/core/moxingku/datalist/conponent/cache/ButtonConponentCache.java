package com.kspt.core.moxingku.datalist.conponent.cache;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.kspt.common.ZQResourceLoaderPath;
import com.kspt.core.moxingku.datalist.conponent.pojo.ButtonComponent;

@Service
public class ButtonConponentCache implements BaseCache<ButtonComponent> {
	@Resource
	ResourceLoader res;
	@Resource
	ZQResourceLoaderPath zQResourceLoaderPath;
	
	public void put(ButtonComponent model) {
		Cache cache = ZqCache.getCache(ButtonConponentCache.class);
		cache.remove(model.getId());
		Element element = new Element(model.getId(), model);
		cache.put(element);
	}

	
	public List<ButtonComponent> getAll() {
		Cache cache = ZqCache.getCache(ButtonConponentCache.class);
		final List<ButtonComponent> list = new LinkedList<ButtonComponent>();
		Query query = cache.createQuery().includeValues();
		Attribute<Integer> order = cache.getSearchAttribute("id");
		query.addOrderBy(order, Direction.ASCENDING);
		Results rs = query.execute();
		List<Result> all = rs.all();
		for (Result r : all) {
			ButtonComponent model = (ButtonComponent) r.getValue();
			list.add(model);
		}
		query.end();
		return list;
	}

	public Set<String> getAllGroup() {
		Cache cache = ZqCache.getCache(ButtonConponentCache.class);
		 Set<String> list = new HashSet<String>();
		Query query = cache.createQuery().includeValues();
		Attribute<Integer> order = cache.getSearchAttribute("id");
		query.addOrderBy(order, Direction.ASCENDING);
		Results rs = query.execute();
		List<Result> all = rs.all();
		for (Result r : all) {
			ButtonComponent model = (ButtonComponent) r.getValue();
			list.add(model.getButtonGroup());
		}
		query.end();
		return list;
	}
	
	public ButtonComponent get(String id) {
		Cache cache = ZqCache.getCache(ButtonConponentCache.class);
		Element element = cache.get(id);
		if (element != null) {
			return (ButtonComponent) element.getObjectValue();
		}
		return null;
	}

	
	public void remove(String id) {
		Cache cache = ZqCache.getCache(ButtonConponentCache.class);
		cache.remove(id);
	}

	public List<ButtonComponent> query(Map<String, DBCacheModel> keys) {
		Cache cache = ZqCache.getCache(ButtonConponentCache.class);
		final List<ButtonComponent> list = new LinkedList<ButtonComponent>();
		Query query = cache.createQuery().includeValues();
		ZqCache.addSearchAttribute(cache, query, keys);
		Results rs = query.execute();
		List<Result> all = rs.all();
		for (Result r : all) {
			ButtonComponent model = (ButtonComponent) r.getValue();
			list.add(model);
		}
		query.end();
		return list;
	}


	@PostConstruct
	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		String[] searchs = { "buttonGroup", "buttonName","id"};
		ZqCache.registCache(ButtonConponentCache.class, searchs);
	}


	
}
