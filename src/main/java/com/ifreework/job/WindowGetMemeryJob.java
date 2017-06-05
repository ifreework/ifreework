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

import com.ifreework.common.manager.SpringManager;
import com.ifreework.util.DateUtil;
import com.ifreework.util.WindowsInfoUtil;

public class WindowGetMemeryJob implements Job{
	private static final String REDIS_MEMERY_SCALE_CACHE = "REDIS_MEMERY_SCALE_CACHE1";
	
	/**
	 * 
	 * 描述：定时查询服务器内存
	 * @param arg0
	 * @throws JobExecutionException 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		CacheManager cacheManager = SpringManager.getCacheManager();
		
		String macAddress = WindowsInfoUtil.getMACAddress();
		Double memeryScale = WindowsInfoUtil.getMemeryScale();
		
		
		Cache<Object, Object> cache = cacheManager.getCache(REDIS_MEMERY_SCALE_CACHE);
		Map<String,Object> map =  (Map<String, Object>) cache.get(getKey(macAddress));
		
		
		List<Object> memerys = null;
		List<Object> times = null;
		
		if(map == null){
			map = new HashMap<String,Object>();
			memerys = new ArrayList<Object>();
			times = new ArrayList<Object>();
			map.put("memerys", memerys);
			map.put("times", times);
			map.put("serverName",WindowsInfoUtil.getServerName());
		}else{
			memerys = (List<Object>) map.get("memerys");
			times = (List<Object>) map.get("times");
		}
		
		if( memerys.size() >= 30 ){
			memerys.remove(0);
		}
		memerys.add(memeryScale);
		
		if( times.size() >= 30 ){
			times.remove(0);
		}
		times.add(DateUtil.getDate("HH:mm"));
		cache.put(getKey(macAddress), map);
		
	}
	
	private String getKey(String macAddress){
		return "memery-" + macAddress;
	}
}
