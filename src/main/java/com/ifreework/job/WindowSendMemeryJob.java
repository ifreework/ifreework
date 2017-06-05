package com.ifreework.job;

import java.util.ArrayList;
import java.util.Calendar;
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
import com.ifreework.util.DateUtil;

public class WindowSendMemeryJob implements Job{
	private static final String REDIS_MEMERY_SCALE_CACHE = "REDIS_MEMERY_SCALE_CACHE1";
	
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		CacheManager cacheManager = SpringManager.getCacheManager();
		Cache<Object, Object> cache = cacheManager.getCache(REDIS_MEMERY_SCALE_CACHE);
		Map<Object,Object> data = new HashMap<Object,Object>();
		List<Object> dataSets = new ArrayList<Object>();
		
		
		
		List<String> labels = getTimes();
		data.put("labels", labels);
		
		for (Object key : cache.keys()) {
			Map<String,Object> cacheMap = (Map<String, Object>) cache.get(key);
			List<Object> doubleData = new ArrayList<Object>();
			List<Object> memeryList = (List<Object>) cacheMap.get("datas");
			
			Map<Object,Object> param = new HashMap<Object,Object>();
			
			for (Object object : memeryList) {
				param.putAll((Map<Object,Object>) object);
			}
			
			for (String object : labels) {
				Object val = param.get(object);
				if(val == null){
				}
			}
			
		}
		
		WebsocketManager.send("/topic/memeryScale", JSON.toJSONString(data));
	}
	
	
	private List<String> getTimes(){
		List<String> list = new ArrayList<String>();
		Calendar cal = Calendar.getInstance();
		for(int i = 0 ;i < 30 ; i++){
			String time = DateUtil.getDate(cal.getTime(), "HH:mm");
			list.add(0, time);
			cal.add(Calendar.MINUTE, -1);
		}
		return list;
	}
}
