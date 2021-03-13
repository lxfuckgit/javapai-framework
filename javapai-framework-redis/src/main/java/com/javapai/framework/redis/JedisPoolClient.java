package com.javapai.framework.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 单机版实现类.<br>
 * 
 * <p>
 * Srping如何集成:<br>
 * &lt;bean id="jedisPoolClient"
 * class="com.javapai.framework.redis.JedisPoolClient"&gt; <br>
 * &lt;property name="jedisPool" ref="jedisPool"&gt;&lt;/property><br>
 * &lt;/bean&gt; <br>
 * &lt;bean id="jedisPool" class="redis.clients.jedis.JedisPool"&gt; <br>
 * &lt; constructor-arg name="host" value="192.168.36.30"/&gt; <br>
 * &lt;constructor-arg name="port" value="6379"/&gt; &lt;/bean&gt; <br>
 * 如何使用:<br>
 * 
 * @author 27122
 *
 */
public class JedisPoolClient implements JedisClient {
	private JedisPool jedisPool;

	public JedisPool getJedisPool() {
		return jedisPool;
	}

	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

	@Override
	public String set(String key, String value) {
		Jedis jedis = jedisPool.getResource();
		String result = jedis.set(key, value);
		jedis.close();
		return result;
	}

	@Override
	public String get(String key) {
		Jedis jedis = jedisPool.getResource();
		String result = jedis.get(key);
		jedis.close();
		return result;
	}

	@Override
	public Boolean exists(String key) {
		Jedis jedis = jedisPool.getResource();
		Boolean result = jedis.exists(key);
		jedis.close();
		return result;
	}

	@Override
	public Long expire(String key, int seconds) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.expire(key, seconds);
		jedis.close();
		return result;
	}

	@Override
	public Long ttl(String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.ttl(key);
		jedis.close();
		return result;
	}

	@Override
	public Long incr(String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.incr(key);
		jedis.close();
		return result;
	}

	@Override
	public Long hset(String key, String field, String value) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.hset(key, field, value);
		jedis.close();
		return result;
	}

	@Override
	public String hget(String key, String field) {
		Jedis jedis = jedisPool.getResource();
		String result = jedis.hget(key, field);
		jedis.close();
		return result;
	}

	@Override
	public Long hdel(String key, String... field) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.hdel(key, field);
		jedis.close();
		return result;
	}

	@Override
	public void setZset(String key, String member, long score) {
		// TODO Auto-generated method stub
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.zadd(key, score, member);
		jedis.close();
		// return result;
	}

	@Override
	public double getZsetScore(String key, String member) {
		// TODO Auto-generated method stub
		Jedis jedis = jedisPool.getResource();
		double score = jedis.zscore(key, member);
		jedis.close();
		return score;
	}

	@Override
	public double getZset(String key, String member) {
		// TODO Auto-generated method stub
		Jedis jedis = jedisPool.getResource();
		double score = jedis.zscore(key, member);
		jedis.close();
		return score;
	}

	@Override
	public double getZsetAsc(String key, String member) {
		// TODO Auto-generated method stub
		Jedis jedis = jedisPool.getResource();
		double score = jedis.zscore(key, member);
		jedis.close();
		return score;
	}

	@Override
	public double getZsetDesc(String key, String member) {
		// TODO Auto-generated method stub
		Jedis jedis = jedisPool.getResource();
		double score = jedis.zscore(key, member);
		jedis.close();
		return score;
	}

	@Override
	public void rangeZset(String key, long start, long end) {
		// TODO Auto-generated method stub

	}

	@Override
	public void rangeZsetAsc(String key, long start, long end) {
		// TODO Auto-generated method stub
		Jedis jedis = jedisPool.getResource();
		// jedis.zrevrangeWithScores(key, start, stop);
		// Set<Tuple> zrangeWithScoresBytes(String key, final long start, final
		// long end) （从小到大）
	}

	@Override
	public void rangeZsetDesc(String key, long start, long end) {
		// TODO Auto-generated method stub
		Jedis jedis = jedisPool.getResource();
		// Set<Tuple> zrevrangeWithScoresBytes(String key, final long start,
		// final long end) （从大到小）

	}

}
