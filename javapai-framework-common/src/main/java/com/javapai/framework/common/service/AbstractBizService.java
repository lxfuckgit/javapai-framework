package com.javapai.framework.common.service;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.javapai.framework.common.vo.PageResult;
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
	 * 分页查询.<br>
	 * 
	 * @param sql
	 *            SQL语句.<br>
	 * @param sqlArgs
	 *            SQL参数.<br>
	 * @param startIndex
	 *            分页当前页.<br>
	 * @param pageSize
	 *            分页每数据量.<br>
	 * @param mappedClass
	 *            映射对象.<br>
	 * @return
	 */
	public <T> PageResult<T> getPage(String sql, List<Object> sqlArgs, Integer startIndex, Integer pageSize, Class<T> mappedClass) {
		return getPage(sql, startIndex, pageSize, mappedClass, sqlArgs.toArray());
	}
	
	/**
	 * 分页查询.<br>
	 * 
	 * @param sql
	 * @param startIndex
	 * @param pageSize
	 * @param mappedClass
	 * @param sqlArgs
	 * @return
	 */
	public <T> PageResult<T> getPage(String sql, Integer startIndex, Integer pageSize, Class<T> mappedClass, Object... sqlArgs) {
		Integer totalRecord = this.getSqlCount(sql, sqlArgs);
		if (null != startIndex && null != pageSize) {
			sql = this.getPageSQL(sql, startIndex, pageSize);
		}

		Integer pageIndex = (startIndex / pageSize) + 1;
		List<T> list = jdbcTemplate.query(sql, sqlArgs, new BeanPropertyRowMapper<T>(mappedClass));
		return new PageResult<T>(pageIndex, pageSize, list, totalRecord);
	}
	
	/**
	 * 根据传入参数进行分页查询.<br>
	 * 
	 * @param sql
	 *            SQL语句.
	 * @param startIndex
	 *            记录起始行.
	 * @param pageSize
	 *            每页记录数。
	 * @param mappedClass
	 *            结果集映射对象.
	 * @param values
	 * @return
	 */
	public <T> PageResult<T> getPage(String sql, Integer startIndex, Integer pageSize, Class<T> mappedClass) {
		return getPage(sql, startIndex, pageSize, mappedClass, new Object[]{});
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
	 * 构造MySQL数据分页SQL
	 * 
	 * @param sql
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	private String getMySQLPageSQL(String sql, Integer startIndex, Integer pageSize) {
		if (null != startIndex && null != pageSize) {
			return sql + " limit " + startIndex + "," + pageSize;
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
	
	
	

//	public Page<Map<String, Object>> getPage(String sql, Integer startIndex, Integer pageSize, Object... values) {
//		/*方法1（关闭理由：没有必要自定义countsql,有特殊要求时，自己外部实现）*/
////		return this.getPage(sql, null, startIndex, pageSize, values);
//		
//		/*方法2*/
//		Integer totalRecord = this.getSqlCount(sql, values);
//		if (null != startIndex && null != pageSize) {
//			sql = this.getPageSQL(sql, startIndex, pageSize);
//		}
//		
//		Integer pageIndex = (startIndex / pageSize) + 1;
//		List<Map<String, Object>> items = jdbcTemplate.query(sql, values, new ColumnMapRowMapper());
//		return new Page<Map<String, Object>>(pageIndex, pageSize, items, totalRecord);
//	}
	
//	public Page<Map<String, Object>> getPage(String sql, String countSQL, Integer startIndex, Integer pageSize, Object... values) {
////		if (StringUtils.isEmpty(countSQL) && StringUtils.isNotEmpty(SQL)) {
////			countSQL = "select count(*) from (" + SQL + ") xCount";
////		}
//		Integer totalRecord = this.getSqlCount(countSQL, values);
//		
//		String pageQueryString = sql;
//		if (null != startIndex && null != pageSize) {
//			pageQueryString = this.getPageSQL(sql, startIndex, pageSize);
//		}
//		
//		List<Map<String, Object>> items = jdbcTemplate.query(pageQueryString, values,
//				new ColumnMapRowMapper());
////		Page page = new Page(startIndex,pageSize,items);
//		Page<Map<String, Object>> page = new Page<>(startIndex, pageSize, items, totalRecord);
//		return page;
//	}

}
