package com.ifreework.job;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.alibaba.fastjson.JSON;
import com.ifreework.common.manager.SpringManager;
import com.ifreework.common.manager.WebsocketManager;

public class WindowSendMemeryJob implements Job{
	private static final String REDIS_MEMERY_SCALE_CACHE = "REDIS_MEMERY_SCALE_CACHE1";
	
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		CacheManager cacheManager = SpringManager.getCacheManager();
		Cache<Object, Object> cache = cacheManager.getCache(REDIS_MEMERY_SCALE_CACHE);
		List<Object> list = new ArrayList<Object>();
		for (Object key : cache.keys()) {
			list.add(cache.get(key));
		}
		
		WebsocketManager.send("/topic/memeryScale", JSON.toJSONString(list));
	}
}
