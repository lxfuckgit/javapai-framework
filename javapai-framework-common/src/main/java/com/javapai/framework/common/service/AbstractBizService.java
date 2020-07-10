package com.javapai.framework.common.service;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.javapai.framework.action.PageResult;
import com.javapai.framework.action.RstResultBuilder;
import com.javapai.framework.constant.CL_Database;

/**
 * 基础业务层抽象服务(只适用于JDBC操作).<br>
 * 
 * 
 * 
 * @author liu.xiang
 *
 */
public abstract class AbstractBizService implements TopBaseService {
//	@javax.annotation.Resource
	@org.springframework.beans.factory.annotation.Autowired
	protected org.springframework.jdbc.core.JdbcTemplate jdbcTemplate;
	
	/**
	 * 根据传入参数进行分页查询.<br>
	 * 
	 * @param sql
	 *            SQL语句.
	 * @param pageIndex
	 *            记录起始行.
	 * @param pageSize
	 *            每页记录数。
	 * @param mappedClass
	 *            结果集映射对象.
	 * @param values
	 * @return
	 */
	public <T> PageResult<T> getPage(String sql, Integer pageIndex, Integer pageSize, Class<T> mappedClass) {
		return getPage(sql, pageIndex, pageSize, mappedClass, new Object[]{});
	}
	
	/**
	 * 分页查询.<br>
	 * 
	 * @param sql
	 *            SQL语句.<br>
	 * @param sqlArgs
	 *            SQL参数.<br>
	 * @param pageIndex
	 *            分页当前页.<br>
	 * @param pageSize
	 *            分页每数据量.<br>
	 * @param mappedClass
	 *            映射对象.<br>
	 * @return
	 */
	public <T> PageResult<T> getPage(String sql, List<Object> sqlArgs, Integer pageIndex, Integer pageSize, Class<T> mappedClass) {
		return getPage(sql, pageIndex, pageSize, mappedClass, sqlArgs.toArray());
	}
	
	/**
	 * 分页查询.<br>
	 * 
	 * @param sql
	 * @param pageIndex
	 * @param pageSize
	 * @param mappedClass
	 * @param sqlArgs
	 * @return
	 */
	public <T> PageResult<T> getPage(String sql, Integer pageIndex, Integer pageSize, Class<T> mappedClass, Object... sqlArgs) {
		Integer totalRecord = this.getSqlCount(sql, sqlArgs);
		if (null != pageIndex && null != pageSize) {
			sql = this.getPageSQL(sql, pageIndex - 1, pageSize);
		}

		List<T> list = jdbcTemplate.query(sql, sqlArgs, new BeanPropertyRowMapper<T>(mappedClass));
//		return new RstPageResult<T>((pageIndex / pageSize) + 1, pageSize, list, totalRecord);
		return RstResultBuilder.buildPageResult((pageIndex / pageSize) + 1, pageSize, list, totalRecord);
	}
	
	/**
	 * 取得数据库类型.
	 * 
	 * @return
	 */
	private String getDefaultDBType() {
		// String dbType = getSystemConfig().get("system.db.type");
		// if (StringUtils.isEmpty(dbType))
		// {
		// dbType = "MySQL";
		// }
		// return dbType;
		return CL_Database.DATABASE_TYPE_MYSQL;
	}
	
	/**
	 * 数据分页查询.
	 * 
	 * @param sql
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	private String getPageSQL(String sql, Integer startIndex, Integer pageSize) {
		String dbType = this.getDefaultDBType();
		return this.getPageSQL(sql, dbType, startIndex, pageSize);
	}
	
	/**
	 * 数据分页查询<br>
	 * 
	 * @param sql
	 *            :SQL
	 * @param dbType
	 *            数据库类型<br>
	 * @param startIndex
	 *            起始索引<br>
	 * @param pageSize
	 *            分页大小<br>
	 * @return
	 */
	private String getPageSQL(String sql, String dbType, Integer startIndex, Integer pageSize) {
		if (dbType.equals(CL_Database.DATABASE_TYPE_MYSQL)) {
			return this.getMySQLPageSQL(sql, startIndex, pageSize);
		} else if (dbType.equals(CL_Database.DATABASE_TYPE_ORALCE)) {
			return this.getOraclePageSQL(sql, startIndex, pageSize);
		} else {
			System.out.println(">>>未指定数据库类型!");
			return null;
		}
	}
	
	/**
	 * 构造MySQL数据分页SQL.<br>
	 * 
	 * @param sql
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	private String getMySQLPageSQL(String sql, Integer startIndex, Integer pageSize) {
		if (null != startIndex && null != pageSize) {
			return sql + " limit " + (startIndex) + "," + pageSize;
		} else if (null != startIndex && null == pageSize) {
			return sql + " limit " + startIndex;
		} else {
			return sql;
		}
	}

	/**
	 * 构造 Oracle数据分页SQL
	 * 
	 * @param sql
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	private String getOraclePageSQL(String sql, Integer startIndex, Integer pageSize) {
		 if (org.springframework.util.StringUtils.isEmpty(sql)) {
			 return null;
		 }
		// String itemSource = queryString.toLowerCase();
		int endIndex = startIndex + pageSize;
		String endSql = "select * from (select rOraclePageSQL.*,ROWNUM as currentRow from ("
				+ sql
				+ ") rOraclePageSQL where rownum <"
				+ endIndex
				+ ") where currentRow>" + startIndex;
		return endSql;
	}
	
	/**
	 * 按条件统计sql结果集数量.<br>
	 * 
	 * @param sql
	 *            SQL语句.
	 * @param values
	 *            SQL语句条件.
	 * @return
	 */
	public Integer getSqlCount(String sql, Object... values) {
		if (!org.springframework.util.StringUtils.isEmpty(sql)){
			sql = "select count(*) from (" + sql + ") xCount";
		} else {
			System.out.println(">>>SQL语句未指定!");
		}
		return jdbcTemplate.queryForObject(sql, values, Integer.class);
	}
	
	
}
