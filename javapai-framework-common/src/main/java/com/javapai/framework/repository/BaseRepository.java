package com.javapai.framework.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * BaseRepository定义基本的数据CRUD操作.<br>
 * 注意，此接口只定义基本CURD操作，更复杂查询可继承扩展使用。 <br>
 * 
 * <br>
 * 此接口类似于Spring-data-jpa中的org.springframework.data.repository.Repository<T, Serializable>接口，目的在于此实现没有依赖绑定在Spring-data-jpa。
 * 
 * @author liu.xiang
 *
 * @param <T>
 * @param <PK>
 */
public interface BaseRepository<T, PK extends Serializable> {
	/**
	 * 保存实体对象。<br>
	 * <p>
	 * 
	 * @param entity
	 *            实体对象<br>
	 */
//	public T saveEntity(T entity);
	public PK saveEntiry(T entity);

	/**
	 * 删除指定实体信息
	 * 
	 * @param entity
	 *            实体对象名
	 * @param pk
	 *            实体主键标识
	 * @return void 
	 * 		   true->删除成功<br>
	 *         false->删除失败<br>
	 */
	public boolean deleteEntity(T entity);

	/**
	 * 通过主键标识来删除指定实体信息
	 * 
	 * @param entity
	 *            实体对象名
	 * @param pk
	 *            实体主键标识
	 * @return void
	 */
	public boolean deleteEntity(final PK pk);
	
	/**
	 * 根据实体主键标识删除指定实体信息
	 * 
	 * @param entity
	 *            实体对象名
	 * @param pk
	 *            实体主键标识
	 * @return void
	 */
	public void deleteEntity(T entity, Serializable pk);

	/**
	 * 批量删除一组实体信息
	 * 
	 * @param entity
	 *            实体对象名
	 * @param pk
	 *            实体主键标识数组
	 * @return void
	 */
	public void deleteEntity(T entity, Object[] pk_value);
	
	/**
	 * 更新实体信息。
	 * 
	 * @param entity
	 *            实体对象.<br>
	 * @return true->删除成功<br>
	 *         false->删除失败<br>
	 */
	public void updateEntity(T entity);
	
	//根据实体名，和提供kv对应来更新相应的字段(不可更新pk)
//	public boolean updateEntity(String entityName,Map<String,String entityOrm>);

	/**
	 * 
	 * @param entity
	 */
	public void findEntity(T entity);

	/**
	 * findAll.
	 * 
	 * @param entity
	 */
	public List<T> findEntityList();

	/**
	 * 
	 * @return
	 */
	public List<T> findEntityList(String condition);

	/**
	 * 
	 * @param entity
	 * @param select
	 * @param orderby
	 * @return
	 */
	public List<T> findEntityList(T entity, Set<String> select, List<String> orderby);

	/**
	 * 查找指定实体集。<br>
	 * <p>
	 * 
	 * @param entity
	 *            实体对象，entiry is a Class type
	 * @param pk_value
	 *            entiry对象主键值
	 */
	public List<T> findEntity(Class<T> entity, PK pk_value);

	/**
	 * 
	 * @param entityPK
	 * @return
	 */
	public T findEntityById(final PK entityPK);

	/**
	 * 通过指定主键标识值查找指定实体信息。<br>
	 * <p>
	 * 
	 * @param entityClass
	 *            实体对象名
	 * @param entityPK
	 *            entity对象主键值
	 */
	public T findEntityById(T entity, final PK entityPK);

	
}
