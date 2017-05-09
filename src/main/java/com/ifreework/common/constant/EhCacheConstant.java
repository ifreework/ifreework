package com.ifreework.common.constant;

import net.sf.ehcache.CacheManager;

public enum EhCacheConstant {
	USER_CACHE_NAME("sys-userCache"), //用户信息缓存空间
	USERNAME_KEY_PREFIX("username-"), //用户缓存key前缀
	 
	AUTH_CACHE_NAME("sys-authCache"), //权限缓存空间
	RESOURCE_PERMISSIONS_PREFIX("resource-permissions-"), //资源所需权限key前缀
	USER_PERMISSIONS_PREFIX("user-permissions-"), //用户拥有权限key前缀
	
	
	MENU_CACHE_NAME("sys-menuCache"), //菜单缓存空间
	USER_MENU_PREFIX("user-menu-"),  //用户拥有菜单key前缀
	
	CONFIG_CACHE_NAME("sys-configCache"), //系统配置缓存空间
	CONFIG_CACHE_KEY_NAME("sys-configCache_static_key"); //系统配置key
	
	
	private String value;
	private EhCacheConstant(String value){
		this.value = value;
	}
	
	public String toString(){
		return value;
	}
}