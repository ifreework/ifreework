package com.ifreework.common.shiro.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ifreework.common.shiro.realm.ShiroAuthInterface;
import com.ifreework.entity.system.Resource;
import com.ifreework.entity.system.User;
import com.ifreework.util.StringUtil;

public class AuthHandlerInterceptor extends HandlerInterceptorAdapter {
	Logger logger = Logger.getLogger(HandlerInterceptorAdapter.class);

	private String unAuthorizedUrl; // 请求没有权限跳转地址
	private ShiroAuthInterface shiroAuth; // 获取请求所需权限接口

	public ShiroAuthInterface getShiroAuth() {
		return shiroAuth;
	}

	public void setShiroAuth(ShiroAuthInterface shiroAuth) {
		this.shiroAuth = shiroAuth;
	}

	public String getUnAuthorizedUrl() {
		return unAuthorizedUrl;
	}

	public void setUnAuthorizedUrl(String unAuthorizedUrl) {
		this.unAuthorizedUrl = unAuthorizedUrl;
	}

	/**
	 * 
	 * 描述：验证当前请求是否已经登录
	 * @Title: preHandle
	 * @param 
	 * @return   
	 * @throws
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String path = request.getServletPath();

		if (path.equals("/")) {
			return true;
		} else {

			Resource resource = shiroAuth.getResource(path, "M");
			if (resource == null) { //如果查询出所需权限为空，则表明不需要权限
				return true;
			}
			
			List<String> auths = shiroAuth.queryAuthorityByResourceId(resource.getResourceId());

			logger.debug("The request path " + path + " need auth of " + auths);
			
			if (auths == null || auths.isEmpty()) { //如果查询出所需权限为空，则表明不需要权限
				return true;
			}
			
			String auth = auths.get(0);
			
			Subject subject = SecurityUtils.getSubject();
			if (subject.isPermitted(auth)) {
				return true;
			}
			request.getRequestDispatcher(unAuthorizedUrl).forward(request,response);
			return false;
		}
	}
}
