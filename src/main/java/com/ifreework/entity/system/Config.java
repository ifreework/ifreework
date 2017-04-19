package com.ifreework.entity.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.ifreework.help.SpringHelper;
import com.ifreework.mapper.system.ConfigMapper;

public class Config {
	
	public static final String FILE_PATH = "file_path"; //文件保存路径，如果开启ftp服务，则为ftp服务器路径
	public static final String SYSTEM_NAME = "system_name"; //系统名称
	public static final String FTP_ENABLE = "ftp_enable";  // 是否启用ftp服务器
	public static final String BUTTON_AUTH_ENABLE = "button_auth_enable"; //是否启用按钮权限
	public static final String FTP_USERNAME = "ftp_username"; //ftp用户
	public static final String FTP_PASSWORD = "ftp_password"; //ftp密码
	public static final String FTP_PORT = "ftp_port"; //ftp密码
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
		if(config == null){
			ConfigMapper configMapper = (ConfigMapper) SpringHelper.getBean("configMapper");
			List<Map<String,Object>> list = configMapper.queryConfigList();
			Map<String,Object> map = new HashMap<String,Object>();
			for(Map<String,Object> item : list ){
				map.put((String) item.get("CONFIG_KEY"), item.get("CONFIG_VALUE"));
			}
			config = new Config(map);
		}
		return config;
	}
	
	public String get(String key){
		return (String) map.get(key);
	}
}


