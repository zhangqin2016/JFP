package com.kspt.common.dao.daoImp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;

import com.kspt.common.dao.BaseDao;
@Service
public class BaseDaoImp extends SqlSessionDaoSupport implements BaseDao {
	@Resource
	DataSource dataSource;
	  @Resource  
	     public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){  
	         super.setSqlSessionFactory(sqlSessionFactory);  
	     } 
	private static String supply = null;
	public static final String SUPPLY_SQLSERVER = "sqlserver";

	public static final String SUPPLY_DB2 = "db2";

	public static final String SUPPLY_MYSQL = "mysql";

	public static final String SUPPLY_ORACLE = "oracle";

	public int delete(String sqlmapper, Object obj) {
		return this.getSqlSession().delete(sqlmapper, obj);
	}

	public int insert(String sqlmapper, Object obj) {
		return this.getSqlSession().insert(sqlmapper, obj);
	}

	public List<?> query(String sqlmapper, Object obj) {
		return this.getSqlSession().selectList(sqlmapper, obj);
	}

	public int update(String sqlmapper, Object obj) {
		return this.getSqlSession().update(sqlmapper, obj);
	}

	public Object queryOne(String sqlmapper, Object obj) {
		return this.getSqlSession().selectOne(sqlmapper,obj);
	}

	public List<?> query(String sqlmapper) {		
		return this.getSqlSession().selectList(sqlmapper);
	}

	public Integer queryTotal(String sqlmapper, Object obj) {		
		
		return (Integer)this.getSqlSession().selectOne(sqlmapper,obj);
	}
	public Integer queryTotal(String sqlmapper) {		
		return (Integer)this.getSqlSession().selectOne(sqlmapper);
	}

	public String getSupply() throws SQLException  {
		if (supply == null) {
			Connection conn = null;
			try {
				conn = getConnection();
				supply = conn.getMetaData().getDatabaseProductName().toLowerCase();
				if ("microsoft sql server".equals(supply)) {
					supply = SUPPLY_SQLSERVER;
				} else if (supply.indexOf(SUPPLY_DB2) != -1) {
					supply = SUPPLY_DB2;
				} else if (supply.indexOf(SUPPLY_ORACLE) != -1) {
					supply = SUPPLY_ORACLE;
				} else if (supply.indexOf(SUPPLY_MYSQL) != -1) {
					supply = SUPPLY_MYSQL;
				}
			} finally {
					conn.close();
			}
		}
		return supply;
	}

	public String convertLongDate(String date) throws SQLException   {
		getSupply();
		if (SUPPLY_SQLSERVER.equals(supply)) {
			return "getdate()";
		} else if (SUPPLY_ORACLE.equals(supply)) {
			return "sysdate";
		} else if (SUPPLY_MYSQL.equals(supply)) {
			return "now()";
		}
		throw new SQLException("不支持的数据库类型");
	}

	public String convertShortDate(String date) throws SQLException   {
		if (date == null || date.trim().length() == 0)
			return null;
		if (SUPPLY_ORACLE.equals(getSupply())) {
			return "to_date('" + date + "','YYYY-mm-dd')";
		} else if (SUPPLY_MYSQL.equals(getSupply())) {
			if (date.indexOf(" ") > 0)
				date = date.substring(0, date.indexOf(" "));
			return "'" + date + "'";
		} else if (SUPPLY_DB2.equals(getSupply())) {
			if (date.indexOf(" ") > 0)
				date = date.substring(0, date.indexOf(" "));
			return "'" + date + "'";
		} else {
			return "'" + date + "'";
		}
	}

	public String getDefaultDate() throws SQLException   {
		getSupply();
		if (SUPPLY_SQLSERVER.equals(supply)) {
			return "getdate()";
		} else if (SUPPLY_ORACLE.equals(supply)) {
			return "sysdate";
		} else if (SUPPLY_MYSQL.equals(supply)) {
			return "now()";
		}
		throw new SQLException("不支持的数据库类型");
	}

	public String convertDateField(String fieldName)  throws SQLException  {
		String dateField = fieldName;
		String dbSupply = null;
		try {
			dbSupply = getSupply();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (dbSupply.toLowerCase().equals(SUPPLY_ORACLE)) {
			dateField = "trunc(" + fieldName + ")";
		} else if (dbSupply.toLowerCase().equals(SUPPLY_SQLSERVER)) {
			dateField = "CONVERT(CHAR(10), " + fieldName + ", 120)";
		} else if (dbSupply.toLowerCase().equals(SUPPLY_MYSQL)) {
			dateField = "DATE(" + fieldName + ")";
		}
		return dateField;
	}

	public String getViewSQL(String sql, int start, int lineNumber) throws SQLException 
			 {
		Connection conn = this.getConnection();
		try {
			return getViewSQL(conn, getSupply(), sql, start, lineNumber);
		} finally {
			conn.close();
		}
	}

	public Connection getConnection() throws SQLException  {
		Connection conn = null;
		conn = DataSourceUtils.getConnection(dataSource);
		return conn;
	}

	private String getViewSQL(Connection conn, String dbSupply, String statment, int start, int lineNumber) {
		/**
		 * oracle数据库分页SQL语句
		 */
		// 解决 分页时候 起始页 0，1 的兼容性问题 ，潜在问题，每页只显示1条记录时 存在缺陷
		start = start == 0 ? 1 : start;
		if (dbSupply.toLowerCase().equals("oracle")) {
			// oracle需要判断首页的特殊情况，修正最后一页少一条记录的问题
			return "select * from (select rownum r,t1.* from (" + statment + ") t1 where rownum<=" + (start - 1 + lineNumber) + ") t2 where t2.r>=" + start;
		} else if (dbSupply.toLowerCase().equals("sqlserver")) {
			/**
			 * sqlserver数据库分页SQL语句
			 */
			String statmentTmp = statment;
			String orderby = "";
			if (statment.toUpperCase().indexOf("ORDER BY") > 0) {
				statmentTmp = statment.substring(0, statment.toUpperCase().indexOf("ORDER BY")).trim();
				orderby = statment.substring(statment.toUpperCase().indexOf("ORDER BY"));
			}
			if (orderby.equals("")) {
				orderby = getSqlserverOrderBy(conn, statment);
			}

			return "select * from ( select ROW_NUMBER() OVER(" + orderby + ") as rownum ," + statmentTmp.substring(statmentTmp.toLowerCase().indexOf("select") + 6) + ") as t where rownum between " + (start>1?start+1:start) + " and " + (start==1 ? lineNumber : start + lineNumber);
		} else if (dbSupply.toLowerCase().equals("mysql")) {
			/**
			 * mysql数据库分页SQL语句
			 */
			return "select * from (" + statment + ") tmpTab limit " + (start > 0 ? start - 1 : start) + "," + lineNumber;
		}
		return statment;
	}

	private String getSqlserverOrderBy(Connection conn, String statment) {
		String column = "";
		Statement st = null;
		ResultSet rs = null;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(statment);
			ResultSetMetaData rsmd = rs.getMetaData();
			column = rsmd.getColumnName(1);
		} catch (SQLException e) {
			e.printStackTrace(System.err);
		} finally {
			try {
				st.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		String newStatment = statment.toUpperCase();
		String tableName = "";
		if ((newStatment.indexOf("FROM") > 0) && (newStatment.indexOf("WHERE") > 0)) {
			String tableList = newStatment.substring(newStatment.indexOf("FROM") + 4, newStatment.indexOf("WHERE")).trim();
			if (tableList.indexOf(",") > -1) {
				tableName = tableList.trim().split(",")[0];
				if (tableName.split("\\s+").length > 1) {
					tableName = tableName.split("\\s+")[0];
				}
			}
		}

		return "ORDER BY " + (tableName.equals("") ? column : (tableName + "." + column));
	}
}