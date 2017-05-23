package com.ifreework.service.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Service;

import com.ifreework.common.entity.PageData;
import com.ifreework.common.manager.QuartzManager;
import com.ifreework.common.manager.SpringManager;
import com.ifreework.util.WindowsInfoUtil;

@Service("windowsMemeryService")
public class WindowsMemeryServiceImpl implements WindowsMemeryService, Job {

	private static final String REDIS_MEMERY_SCALE_CACHE = "REDIS_MEMERY_SCALE_CACHE1";
	public WindowsMemeryServiceImpl(){
		QuartzManager.addJob("AddWindowsMemeryJob1", WindowsMemeryServiceImpl.class, "0/20 * * * * ?");
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("=======================================================");
		System.out.println(new Date());
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

	
	@Override
	public PageData getMemeryScale() {
		return null;
	}
	
	private String getKey(String macAddress){
		return "memery-" + macAddress;
	}

}
