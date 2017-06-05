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
		
		if(map == null){
			map = new HashMap<String,Object>();
			map.put("serverName",WindowsInfoUtil.getServerName());
		}
		
		List<Object> datas = (List<Object>) map.get("datas");
		
		if(datas == null){
			datas = new ArrayList<Object>();
			map.put("datas",datas);
		}
		
		if( datas.size() >= 30 ){
			datas.remove(0);
		}
		
		Map<String,Object> memeryMap = new HashMap<String,Object>();
		memeryMap.put(DateUtil.getDate("HH:mm"), memeryScale);
		
		datas.add(memeryMap);
		
		cache.put(getKey(macAddress), map);
		
	}
	
	private String getKey(String macAddress){
		return "memery-" + macAddress;
	}
}
