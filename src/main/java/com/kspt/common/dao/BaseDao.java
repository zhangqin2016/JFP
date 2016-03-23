package com.kspt.common.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

public interface BaseDao {
	/**
	 * 按照条件查询多行
	 * @param sqlmapper
	 * @param map
	 * @return
	 */
	public List<?> query(String sqlmapper,Object map);
	/**
	 * 按照条件查询一行数据
	 * @param sqlmapper
	 * @param obj
	 * @return
	 */
	public Object queryOne(String sqlmapper,Object obj);
	/**
	 * 无条件查询
	 * @param sqlmapper
	 * @return
	 */
	public List<?> query(String sqlmapper);
	/**
	 * 无条件查询总数
	 * @param sqlmapper
	 * @return
	 */
	public Integer queryTotal(String sqlmapper);
	/**
	 * 有条件查询总数
	 * @param sqlmapper
	 * @param obj
	 * @return
	 */
	public Integer queryTotal(String sqlmapper,Object obj);
	/**
	 * 添加数据
	 * @param sqlmapper
	 * @param obj
	 * @return
	 */
	public int insert(String sqlmapper,Object obj);
	/**
	 * 更新数据
	 * @param sqlmapper
	 * @param obj
	 * @return
	 */
	public int update(String sqlmapper,Object obj);
	/**
	 * 删除数据
	 * @param sqlmapper
	 * @param obj
	 * @return
	 */
	
	public int delete(String sqlmapper,Object obj);
	public Connection getConnection() throws SQLException;
	
	public String getSupply() throws SQLException;

	public String convertLongDate(String date) throws SQLException;

	public String convertShortDate(String date) throws SQLException;

	public String getDefaultDate() throws SQLException;

	public String convertDateField(String fieldName) throws SQLException ;

	/**
	 * 分页查询语句
	 * 
	 * @param sql
	 *            sql语句
	 * @param start
	 *            开始页
	 * @param lineNumber
	 *            分页数
	 * @return 分页的sql语句
	 * @throws SQLException
	 */
	public String getViewSQL(String sql, int start, int lineNumber) throws SQLException;
}
