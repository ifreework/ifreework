package com.ifreework.common.shiro.session;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionFactory;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.session.mgt.SimpleSessionFactory;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.session.HttpServletSession;
import org.apache.shiro.web.util.WebUtils;

import com.ifreework.common.constant.Constant;
import com.ifreework.common.shiro.listener.LoginLogManager;
import com.ifreework.util.StringUtil;

/**
 * 将shiro的session托管给ehcache进行缓存
 * 描述：    
 * @author：wangyh
 * @createDate：2017年5月1日
 * @modify：wangyh    
 * @modifyDate：2017年5月1日 
 * @version 1.0
 */
public class EhcacheSessionDAO extends AbstractSessionDAO {
	private Logger logger = Logger.getLogger(getClass());
	private Cache<String, Session> cache;
	private String keyPrefix = "shiro_redis_cache:";
	private LoginLogManager loginLogManager;
	
	
	public LoginLogManager getLoginLogManager() {
		return loginLogManager;
	}

	public void setLoginLogManager(LoginLogManager loginLogManager) {
		this.loginLogManager = loginLogManager;
	}

	public EhcacheSessionDAO(String cacheName, CacheManager cacheManager) {
		cache = cacheManager.getCache(cacheName);
	}

	/**
	 * 
	 * 描述：在缓存中保存session
	 * @Title: saveSession
	 * @param 
	 * @return   
	 * @throws
	 */
	private void saveSession(Session session) throws UnknownSessionException {
		if (session == null || session.getId() == null) {
			logger.error("session or session id is null");
			return;
		}
		
		String id = getKey(session.getId());
		cache.put(id, session);
	}

	/**
	 * 
	 * 描述：在缓存中删除session
	 * @Title: delete
	 * @param 
	 * @return   
	 * @throws
	 */
	@Override
	public void delete(Session session) {
		
		if (session == null || session.getId() == null) {
			logger.error("session or session id is null");
			return;
		}
		String username = (String) session.getAttribute(Constant.CACHE_USER);
		if(!StringUtil.isEmpty(username)){
			loginLogManager.logoutLog(username);
		}
		
		logger.debug("delete session : " + session.getId());
		cache.remove(getKey(session.getId()));
	}

	/**
	 * 
	 * 描述：获取缓存中所有活跃的session
	 * @Title: getActiveSessions
	 * @param 
	 * @return   
	 * @throws
	 */
	@Override
	public Collection<Session> getActiveSessions() {
		List<Session> activies = new ArrayList<Session>();
		for (Session session : cache.values()) {
			if (session != null) {
				activies.add(session);
			}
		}
		logger.debug("Active sessions :" + activies);
		return activies;
	}

	/**
	 * 
	 * 描述：修改缓存中的session信息
	 * @Title: update
	 * @param 
	 * @return   
	 * @throws
	 */
	@Override
	public void update(Session session) throws UnknownSessionException {
		logger.debug("update session : " + session.getId());
		saveSession(session);
	}

	/**
	 * 
	 * 描述：创建session
	 * @Title: doCreate
	 * @param 
	 * @return   
	 * @throws
	 */
	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId = this.generateSessionId(session);
		this.assignSessionId(session, sessionId);
		this.saveSession(session);
		logger.debug("create session : " + sessionId);
		return sessionId;
	}

	/**
	 * 
	 * 描述：根据sessionId，获取session信息
	 * @Title: doReadSession
	 * @param 
	 * @return   
	 * @throws
	 */
	@Override
	protected Session doReadSession(Serializable sessionId) {
		if (sessionId == null) {
			logger.error("session id is null");
			return null;
		}
		Session session = cache.get(getKey(sessionId));
		return session;
	}

	/** 
	 * 获得byte[]型的key 
	 * @param key 
	 * @return 
	 */
	private String getKey(Serializable sessionId) {
		String preKey = this.keyPrefix + sessionId;
		return preKey;
	}
}
