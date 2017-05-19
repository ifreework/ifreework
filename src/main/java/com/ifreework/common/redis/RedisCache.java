package com.ifreework.common.redis;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ifreework.util.SerializeUtils;


class RedisCache<K, V> implements Cache<K, V>, Serializable {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private static final long serialVersionUID = -3239675522496957694L;

	private RedisManager redisManager;
	private String redisKey;
	private Map<K, V> cache;

	
	
	public RedisCache(RedisManager redisManager, String redisKey) {
		super();
		this.redisManager = redisManager;
		this.redisKey = redisKey;
		cache = new HashMap<K, V>();
	}


	public String getRedisKey() {
		return redisKey;
	}

	/**
	 * 描述：通过key获取cache中的对象
	 * @param key 键
	 * @return   value 值，如果key == null ,则返回null
	 */
	@Override
	public V get(K key) {
		if (key == null) {
			logger.debug("{} get value has the key null!", redisKey);
			return null;
		} else {
			V value = cache.get(key);
			logger.debug("{} get key: {} , value : {}!", redisKey, key, value);
			return value;
		}

	}

	/**
	 * 
	 * 描述：通过key:value形式存储值
	 * @param key 键
	 * @param value 值
	 * @return   
	 */
	@Override
	public V put(K key, V value) {
		cache.put(key, value);
		redisManager.set(getByte(redisKey), getByte(this));
		logger.debug("{} put key : {} , value : {}", redisKey, key, value);
		return value;
	}

	/**
	 * 
	 * 描述：通过key删除元素
	 * @param key 键
	 * @return   
	 */
	@Override
	public V remove(K key) throws CacheException {
		V value = cache.remove(key);
		logger.debug("{} remove key : {} , value : {}!", redisKey, key, value);
		redisManager.set(getByte(redisKey), getByte(this));
		return value;
	}

	/**
	 * 描述：清楚缓存中所有元素
	 */
	@Override
	public void clear() throws CacheException {
		cache.clear();
		logger.debug("{} is cleared!", redisKey);
		redisManager.set(getByte(redisKey), getByte(this));
	}

	/**
	 * 
	 * 描述：获取缓存中所有对象的数量
	 * @return  int
	 */
	@Override
	public int size() {
		return cache.size();
	}

	/**
	 * 描述：获取缓存所有的key
	 * @param 
	 * @return
	 */
	@Override
	public Set<K> keys() {
		return cache.keySet();
	}

	/**
	 * 描述：获取缓存所有的value
	 * @param 
	 * @return
	 */
	@Override
	public Collection<V> values() {
		return cache.values();
	}

	public String toString() {
		return cache.toString();
	}

	/**
	 * 获得byte[]型的key
	 * @param key
	 * @return
	 */
	private byte[] getByte(Object obj) {
		if (obj instanceof String) {
			return ((String) obj).getBytes();
		} else {
			return SerializeUtils.serialize(obj);
		}
	}

}
