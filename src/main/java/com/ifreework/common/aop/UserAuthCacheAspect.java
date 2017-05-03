/**
 * Copyright (c) 2005-2012 https://github.com/zhangkaitao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.ifreework.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.ifreework.common.constant.UserConstant;

import java.util.Arrays;

/**
 * 用户权限的切面
 * <p/>
 * 1、当调用如下方法时，加缓存
 * {@link com.ifreework.common.shiro.realm.ShiroAuthInterface#queryAuthorityByUserName}
 * {@link com.ifreework.common.shiro.realm.ShiroAuthInterface#getAuthorityByUrl}
 * <p/>
 * 2、授权（Auth）
 * 当增删改授权时，
 * 如果是用户相关的，只删用户的即可，
 * 其他的全部清理
 * <p/>
 * 3。1、资源（Resource）
 * 当修改资源时判断是否发生变化（如resourceIdentity，是否显示），如果变了清缓存
 * 当删除资源时，清缓存
 * 3.2、权限（Permission）
 * 当修改权限时判断是否发生变化（如permission，是否显示），如果变了清缓存
 * 当删除权限时，清缓存
 * <p/>
 * 4、角色（Role）
 * 当删除角色时，请缓存
 * 当修改角色show/role/resourcePermissions关系时，清缓存
 * <p/>
 * 
 * 5、用户
 * 修改时，如果组织机构/工作职务变了，仅需清自己的缓存
 * <p/>
 * 清理自己时也同时清理菜单的缓存
 * <p/>
 * TODO
 * 1、异步失效缓存
 * 2、预填充缓存（即把此刻失效的再通过异步任务查一次） 目前只查前100个吧
 * 3、加二级缓存 提高失效再查的效率
 * <p/>
 * 此方法的一个缺点就是 只要改了一个，所有缓存失效。。。。
 * TODO 思考更好的做法？
 * <p/>
 * 
 * @author：wangyh
 * @createDate：2017年5月2日
 * @modify：wangyh    
 * @modifyDate：2017年5月2日 
 * @version 1.0
 */
@Component
@Aspect
public class UserAuthCacheAspect extends BaseCacheAspect<String, Object> {

	public UserAuthCacheAspect() {
		setCacheName("sys-authCache");
	}

	private String userPermissionsPrefix = UserConstant.USER_PERMISSIONS_PREFIX.toString();
	private String resourcePermissionsPrefix = UserConstant.RESOURCE_PERMISSIONS_PREFIX.toString();

	public void setCacheName(String cacheName) {
		this.cacheName = cacheName;
	}

	public String getUserPermissionsPrefix() {
		return userPermissionsPrefix;
	}

	public void setUserPermissionsPrefix(String userPermissionsPrefix) {
		this.userPermissionsPrefix = userPermissionsPrefix;
	}

	public String getResourcePermissionsPrefix() {
		return resourcePermissionsPrefix;
	}

	public void setResourcePermissionsPrefix(String resourcePermissionsPrefix) {
		this.resourcePermissionsPrefix = resourcePermissionsPrefix;
	}


	@Pointcut(value = "execution(* com.ifreework.service.system.UserServiceImpl.queryAuthorityByResourceId(..))")
	private void authCacheAuthorityByResourcePointcut() {
	}

	@Pointcut(value = "execution(* com.ifreework.service.system.UserServiceImpl.queryAuthorityByUserName(..))")
	private void authCacheAuthorityByUsernamePointcut() {
	}

	@Around(value = "authCacheAuthorityByUsernamePointcut()")
	public Object queryAuthorityByUserName(ProceedingJoinPoint joinPoint) throws Throwable {
		String username = (String) joinPoint.getArgs()[0];
		String key = getUserPermissionsKey(username);
		Object obj = get(key);
		if (obj != null) {
			log.debug("cacheName:" + cacheName + ", method:queryAuthorityByUserName, hit key:" + key + ",value:" + obj);
			return obj;
		}
		obj = joinPoint.proceed();
		put(key, obj);
		return obj;

	}
	
	@Around(value = "authCacheAuthorityByUsernamePointcut()")
	public Object queryAuthorityByResourceId(ProceedingJoinPoint joinPoint) throws Throwable {
		String resourceId = (String) joinPoint.getArgs()[0];
		String key = getResourcePermissionsKey(resourceId);
		Object obj = get(key);
		if (obj != null) {
			log.debug("cacheName:" + cacheName + ", method:queryAuthorityByUserName, hit key:" + key + ",value:" + obj);
			return obj;
		}
		obj = joinPoint.proceed();
		put(key, obj);
		return obj;

	}

	private String getUserPermissionsKey(String username) {
		return userPermissionsPrefix + username;
	}
	
	private String getResourcePermissionsKey(String resourceId) {
		return resourcePermissionsPrefix + resourceId;
	}
}
