package com.javapai.framework.redis;

import redis.clients.jedis.JedisCluster;

/**
 * 集群版实现类.<br>
 * 
 * @code <bean id="jedisClusterClient" class=
 *       "com.javapai.framework.redis.JedisClusterClient">
 *       <property name="jedisCluster" ref="jedisCluster"></property>
 * 
 * 
 * 
 * 
 *       </bean>
 *       <bean id="jedisCluster" class="redis.clients.jedis.JedisCluster">
 *       <constructor-arg name="nodes"> <set>
 *       <bean class="redis.clients.jedis.HostAndPort">
 *       <constructor-arg name="host" value="192.168.36.30"/>
 *       <constructor-arg name="port" value="7001"/> </bean>
 *       <bean class="redis.clients.jedis.HostAndPort">
 *       <constructor-arg name="host" value="192.168.36.30"/>
 *       <constructor-arg name="port" value="7002"/> </bean>
 *       <bean class="redis.clients.jedis.HostAndPort">
 *       <constructor-arg name="host" value="192.168.36.30"/>
 *       <constructor-arg name="port" value="7003"/> </bean>
 *       <bean class="redis.clients.jedis.HostAndPort">
 *       <constructor-arg name="host" value="192.168.36.30"/>
 *       <constructor-arg name="port" value="7004"/> </bean>
 *       <bean class="redis.clients.jedis.HostAndPort">
 *       <constructor-arg name="host" value="192.168.36.30"/>
 *       <constructor-arg name="port" value="7005"/> </bean>
 *       <bean class="redis.clients.jedis.HostAndPort">
 *       <constructor-arg name="host" value="192.168.36.30"/>
 *       <constructor-arg name="port" value="7006"/> </bean> </set>
 *       </constructor-arg> </bean>
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * @author 27122
 *
 */
public class JedisClusterClient implements JedisClient {
	private JedisCluster jedisCluster;

	public JedisCluster getJedisCluster() {
		return jedisCluster;
	}

	public void setJedisCluster(JedisCluster jedisCluster) {
		this.jedisCluster = jedisCluster;
	}

	@Override
	public String set(String key, String value) {
		return jedisCluster.set(key, value);
	}

	@Override
	public String get(String key) {
		return jedisCluster.get(key);
	}

	@Override
	public Boolean exists(String key) {
		return jedisCluster.exists(key);
	}

	@Override
	public Long expire(String key, int seconds) {
		return jedisCluster.expire(key, seconds);
	}

	@Override
	public Long ttl(String key) {
		return jedisCluster.ttl(key);
	}

	@Override
	public Long incr(String key) {
		return jedisCluster.incr(key);
	}

	@Override
	public Long hset(String key, String field, String value) {
		return jedisCluster.hset(key, field, value);
	}

	@Override
	public String hget(String key, String field) {
		return jedisCluster.hget(key, field);
	}

	@Override
	public Long hdel(String key, String... field) {
		return jedisCluster.hdel(key, field);
	}

	@Override
	public void setZset(String key, String member, long score) {
		// TODO Auto-generated method stub

	}

	@Override
	public double getZset(String key, String member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getZsetAsc(String key, String member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getZsetDesc(String key, String member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getZsetScore(String key, String member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void rangeZset(String key, long start, long end) {
		// TODO Auto-generated method stub

	}

	@Override
	public void rangeZsetAsc(String key, long start, long end) {
		// TODO Auto-generated method stub

	}

	@Override
	public void rangeZsetDesc(String key, long start, long end) {
		// TODO Auto-generated method stub

	}

}
