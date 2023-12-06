package com.javapai.framework.common.service;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.util.StringUtils;

import com.javapai.framework.action.PageResult;
import com.javapai.framework.action.ResultBuilder;
import com.javapai.framework.constant.CL_Database;

/**
 * 基础业务层抽象服务(只适用于JDBC操作).<br>
 * 
 * 依赖：<br>
 * 1、spring-jdbc.jar<br>
 * 
 * @author liu.xiang
 *
 */
public abstract class AbstractBizService implements TopBaseService {
	@org.springframework.beans.factory.annotation.Autowired
	private org.springframework.core.env.Environment environment;
	
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
			sql = this.getPageSQL(sql, pageIndex, pageSize);
		}

		List<T> list = jdbcTemplate.query(sql, sqlArgs, new BeanPropertyRowMapper<T>(mappedClass));
		return ResultBuilder.buildPageResult(pageIndex, pageSize, list, totalRecord);
	}
	
	/**
	 * 取得数据库类型.
	 * 
	 * @return
	 */
	private String getDefaultDBType() {
		String dbType = environment.getProperty("config.system.dbtype");
		if (StringUtils.isEmpty(dbType)) {
			return CL_Database.DATABASE_TYPE_MYSQL;
		} else {
			return dbType;
		}
	}
	
	/**
	 * 数据分页查询.
	 * 
	 * <strong>重要提示：<strong>
	 * pageIndex和pageSize最小值要求符合{@linkplain com.javapai.framework.common.page.Paginate}接口中定义的规范。
	 * 
	 * @param sql
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	private String getPageSQL(String sql, Integer pageIndex, Integer pageSize) {
		String dbType = this.getDefaultDBType();
		return this.getPageSQL(sql, dbType, pageIndex, pageSize);
	}
	
	/**
	 * 数据分页查询<br>
	 * 
	 * @param sql
	 *            :SQL
	 * @param dbType
	 *            数据库类型<br>
	 * @param pageIndex
	 *            起始索引<br>
	 * @param pageSize
	 *            分页大小<br>
	 * @return
	 */
	private String getPageSQL(String sql, String dbType, Integer pageIndex, Integer pageSize) {
		if (dbType.equals(CL_Database.DATABASE_TYPE_MYSQL)) {
			return this.getMySQLPageSQL(sql, pageIndex, pageSize);
		} else if (dbType.equals(CL_Database.DATABASE_TYPE_SQLITE)) {
			return this.getSQLitePageSQL(sql, pageIndex, pageSize);
		} else if (dbType.equals(CL_Database.DATABASE_TYPE_ORALCE)) {
			return this.getOraclePageSQL(sql, pageIndex, pageSize);
		} else {
			System.out.println(">>>未指定数据库类型!");
			return null;
		}
	}
	
	/**
	 * 构造MySQL数据分页SQL.<br>
	 * 
	 * @param sql
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	private String getMySQLPageSQL(String sql, Integer pageIndex, Integer pageSize) {
		if (null != pageIndex && null != pageSize) {
			return sql + " limit " + (pageIndex) + "," + pageSize;
		} else if (null != pageIndex && null == pageSize) {
			return sql + " limit " + pageIndex;
		} else {
			return sql;
		}
	}
	
	/**
	 * 构造SQLite3数据分页SQL.<br>
	 * 
	 * @param sql
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	private String getSQLitePageSQL(String sql, int pageIndex, int pageSize) {
		// 分页语法1：limit x,y; 解释：从X行开始取到Y行（首行为0行）。
		// 分页语法2：limit x offset y; 解释：从Y行开始取X行（首行为0行）。
		return sql + " limit " + pageSize + " offset " + (pageIndex - 1) * pageSize;
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
