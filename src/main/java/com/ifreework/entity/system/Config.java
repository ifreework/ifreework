package com.ifreework.entity.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.ehcache.EhCacheManager;

import com.ifreework.common.constant.EhCacheConstant;
import com.ifreework.common.manager.SpringManager;
import com.ifreework.mapper.system.ConfigMapper;

public class Config{
	public static final String SYSTEM_NAME = "system_name"; //系统名称
	
	public static final String BUTTON_AUTH_ENABLE = "button_auth_enable"; //是否启用按钮权限
	
	public static final String FILE_PATH = "file_path"; //文件保存路径，如果开启ftp服务，则为ftp服务器路径
	public static final String FTP_ENABLE = "ftp_enable";  // 是否启用ftp服务器
	public static final String FTP_USERNAME = "ftp_username"; //ftp用户
	public static final String FTP_PASSWORD = "ftp_password"; //ftp密码
	public static final String FTP_PORT = "ftp_port"; //ftp密码
	
	public static final String RESET_PWD = "reset_pwd"; //用户初始密码
	
	
	public static final String MAIL_SMTP_HOST = "mail_smtp_host"; //邮箱smtp地址
	public static final String MAIL_SMTP_PORT = "mail_smtp_port"; //邮箱smtp地址端口
	public static final String MAIL_SMTP_AUTH = "mail_smtp_auth"; //
	public static final String MAIL_STORE_PROTOCOL = "mail_store_protocol"; //
	public static final String MAIL_STORE_HOST = "mail_store_host"; //邮箱pop3地址
	public static final String MAIL_STORE_PORT = "mail_store_port"; //邮箱pop3地址端口
	public static final String MAIL_USER = "mail_user"; //邮箱用户名
	public static final String MAIL_PASSWORD = "mail_password"; //邮箱密码
	
	private static Config config;
	private Map<String,Object> map;
	
	private Config(Map<String,Object> map){
		this.map = map;
	}
	
	/**
	 * 
	 * 描述：单例模式下初始化Config
	 * @Title: init
	 * @param 
	 * @return   
	 * @throws
	 */
	public static Config init(){
		EhCacheManager ehCacheManager = SpringManager.getEhCacheManager();
		Cache<String,Config> cache = ehCacheManager.getCache(EhCacheConstant.CONFIG_CACHE_NAME.toString());
		config = cache.get(EhCacheConstant.CONFIG_CACHE_KEY_NAME.toString());
		
		if(config == null){
			ConfigMapper configMapper = (ConfigMapper) SpringManager.getBean("configMapper");
			List<Map<String,Object>> list = configMapper.queryConfigList();
			Map<String,Object> map = new HashMap<String,Object>();
			for(Map<String,Object> item : list ){
				map.put((String) item.get("CONFIG_KEY"), item.get("CONFIG_VALUE"));
			}
			config = new Config(map);
			cache.put(EhCacheConstant.CONFIG_CACHE_KEY_NAME.toString(), config);
		}
		return config;
	}
	
	public String get(String key){
		return (String) map.get(key);
	}
	
	
	/**
	 * 
	 * 描述：当cache被修改后，清空缓存
	 * @Title: reset
	 * @param 
	 * @return   
	 * @throws
	 */
	public static void reset(){
		EhCacheManager ehCacheManager = SpringManager.getEhCacheManager();
		Cache<String,Config> cache = ehCacheManager.getCache(EhCacheConstant.CONFIG_CACHE_NAME.toString());
		cache.clear();
	}
}


