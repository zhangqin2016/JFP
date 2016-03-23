package com.kspt.cache.dao.impl;

import java.util.List;
import java.util.Map;

import com.kspt.cache.model.DBCacheModel;


public interface BaseCache<T> {
	public void put(T model);
	public List<T> getAll();
	public T get(String id);
	public void remove(String id);
	public void initialize();
	public List<T> query(Map<String,DBCacheModel> keys);
}
