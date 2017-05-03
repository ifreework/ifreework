package com.ifreework.common.aop;

import org.apache.log4j.Logger;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * 描述：基础cache切面，用于缓存    
 * @param <K>
 * @param <V>
 * @author：wangyh
 * @createDate：2017年4月30日
 * @modify：wangyh    
 * @modifyDate：2017年4月30日 
 * @version 1.0
 */
public class BaseCacheAspect<K, V> implements InitializingBean {

    protected final Logger log = Logger.getLogger(getClass());
    @Autowired
    private CacheManager cacheManager;
    private Cache<K,V> cache;
    protected String cacheName;

    /**
     * 缓存池名称
     *
     * @param cacheName
     */
    public void setCacheName(String cacheName) {

        this.cacheName = cacheName;
    }

    /**
     * 缓存管理器
     *
     * @return
     */
    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }


    public void afterPropertiesSet() throws Exception {
        cache = cacheManager.getCache(cacheName);
    }

    public void clear() {
        log.debug("cacheName:" + cacheName + ", cache clear.");
        this.cache.clear();
    }

    public V remove(K key) {
    	 log.debug("cacheName:" + cacheName + ", remove key:" + key);
        return this.cache.remove(key);
    }

    public V  get(K key) {
        log.debug("cacheName:" + cacheName + ", get key:" + key);
        return cache.get(key);
    }

    public void put(K key, V value) {
        log.debug("cacheName:" + cacheName + ", put key:" + key + ",put value:"+ value);
        this.cache.put(key, value);
    }

}
