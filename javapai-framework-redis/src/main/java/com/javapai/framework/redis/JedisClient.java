package com.javapai.framework.redis;

/**
 * Jedis客户端.<br>
 * <p>
 * 依赖jedis.3.0.01.jar
 * </p>
 * 
 * @author 27122
 *
 */
public interface JedisClient extends RedisClient {
	String set(String key, String value);

	String get(String key);

	Boolean exists(String key);

	Long expire(String key, int seconds);

	Long ttl(String key);

	Long incr(String key);

	Long hset(String key, String field, String value);

	String hget(String key, String field);

	Long hdel(String key, String... field);

	/**
	 * 插入或更新数据.<br>
	 * 
	 * @param key
	 * @param member
	 * @param score
	 */
	void setZset(final String key, final String member, long score);

	/**
	 * 读取指定key下member排名（排序号）.<br>
	 * 
	 * @param key
	 * @param member
	 * @deprecated 参见:<br>
	 *             {@link JedisClient#getZsetAsc(String, String) }<br>
	 *             {@link JedisClient#getZsetDesc(String, String)}<br>
	 */
	double getZset(final String key, final String member);

	/**
	 * @see JedisClient#getZset(String, String)
	 * @param key
	 * @param member
	 * @return
	 */
	// asc 是ascend 升序的意思。
	double getZsetAsc(final String key, final String member);

	/**
	 * @see JedisClient#getZset(String, String)
	 * @param key
	 * @param member
	 * @return
	 */
	// DESC 是descend 降序意思 。
	double getZsetDesc(final String key, final String member);

	/**
	 * 读取指定key下member评分.<br>
	 * 
	 * @param key
	 * @param member
	 * @return
	 */
	double getZsetScore(String key, final String member);

	void rangeZset(String key, final long start, final long end);

	/**
	 * 
	 * @param key
	 * @param start
	 *            开始排名
	 * @param end
	 *            结束排名
	 */
	void rangeZsetAsc(String key, final long start, final long end);

	/**
	 * 
	 * @param key
	 * @param start
	 *            开始排名
	 * @param end
	 *            结束排名
	 */
	void rangeZsetDesc(String key, final long start, final long end);
}
