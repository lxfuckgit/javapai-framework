package com.javapai.framework.repository;

import java.io.Serializable;

/**
 * 通用Repository接口，具体实现由不同的ORM/JDBC支持实现.<br>
 * 所有GenericRepository的实现类，都共用一个GenericRepository接口，可面向接口编程。 <br>
 * 
 * @author liu.xiang
 *
 * @param <T>
 * @param <PK>
 */
public interface GenericRepository<T, PK extends Serializable> extends BaseRepository<T, PK> {
//	 * 
//	 * @param sql
//	 * @return
//	 */
//	<T> List<T> findBySQL(String sql);	
}