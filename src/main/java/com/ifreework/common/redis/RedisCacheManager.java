package com.ifreework.common.redis;


import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ifreework.util.SerializeUtils;

public class RedisCacheManager<K, V> implements CacheManager {

	private static final Logger logger = LoggerFactory.getLogger(RedisCacheManager.class);

	private RedisManager redisManager;

	public RedisManager getRedisManager() {
		return redisManager;
	}
	

	public void setRedisManager(RedisManager redisManager) {
		this.redisManager = redisManager;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Cache getCache(String name) throws CacheException {
		RedisCache cache;
		byte[] key = name.getBytes();
		byte[] value = redisManager.get(key);
		if (value == null || value.length == 0) {
			cache = new RedisCache<K, V>(redisManager,name);
			logger.debug("Redis get cache {} is null ", name);
		} else {
			cache = (RedisCache) SerializeUtils.deserialize(value);
			logger.debug("Redis get cache {} : {} ", name, cache);
		}
		return cache;
	}

}
