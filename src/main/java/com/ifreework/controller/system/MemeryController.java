package com.ifreework.controller.system;



import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ifreework.common.controller.BaseControllerSupport;
import com.ifreework.common.manager.SpringManager;



/**    
 *     
 * 类名称：AttachmentController    
 * 类描述：    
 * 创建人：王宜华    
 * 创建时间：2014-11-26 下午4:46:18    
 * 修改人：王宜华    
 * 修改时间：2014-11-26 下午4:46:18    
 * 修改备注：    
 * @version     
 *     
 */
@Controller
@RequestMapping("/system/memery")
public class MemeryController extends BaseControllerSupport {
	private static final String REDIS_MEMERY_SCALE_CACHE = "REDIS_MEMERY_SCALE_CACHE1";
	
	@RequestMapping()
	public String gotoView(){
		return "/system/server/memery";
	}
	
	
	@RequestMapping("/load")
	@ResponseBody
	public List<Object> load(){
		CacheManager cacheManager = SpringManager.getCacheManager();
		Cache<Object, Object> cache = cacheManager.getCache(REDIS_MEMERY_SCALE_CACHE);
		List<Object> list = new ArrayList<Object>();
		for (Object key : cache.keys()) {
			list.add(cache.get(key));
		}
		return list;
	}
	
}
