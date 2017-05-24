package com.ifreework.job;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.ifreework.common.manager.SpringManager;
import com.ifreework.util.WindowsInfoUtil;

public class WindowGetMemeryJob implements Job{
	private static final String REDIS_MEMERY_SCALE_CACHE = "REDIS_MEMERY_SCALE_CACHE1";
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		CacheManager cacheManager = SpringManager.getCacheManager();
		
		String macAddress = WindowsInfoUtil.getMACAddress();
		Double memeryScale = WindowsInfoUtil.getMemeryScale();
		Cache<Object, Object> cache = cacheManager.getCache(REDIS_MEMERY_SCALE_CACHE);
		List<Double> list = (List<Double>) cache.get(getKey(macAddress));
		if(list == null || list.isEmpty()){
			list = new ArrayList<Double>();
		}
		
		if( list.size() >= 30 ){
			list.remove(0);
		}
		list.add(memeryScale);
		cache.put(getKey(macAddress), list);
	}
	
	private String getKey(String macAddress){
		return "memery-" + macAddress;
	}
}
