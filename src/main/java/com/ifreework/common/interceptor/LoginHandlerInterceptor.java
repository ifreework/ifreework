package com.ifreework.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ifreework.entity.system.User;
import com.ifreework.help.Jurisdiction;
import com.ifreework.util.Const;

/**
 * 
 * 描述：过滤请求地址，进行权限验证    
 * @author：wangyh qq735789026  
 * @创建时间：2016年7月7日 上午10:56:48    
 * @修改人：wangyh    
 * @修改时间：2016年7月7日 上午10:56:48    
 * @version 1.0
 */
public class LoginHandlerInterceptor extends HandlerInterceptorAdapter{
	Logger logger = Logger.getLogger(LoginHandlerInterceptor.class);

	/**
	 * @Title: preHandle 
	 * @Description: TODO(验证用户是否已经登录，如果尚未登录，跳转到登录界面) 
	 * @param 
	 * @return 
	 * @throws
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		request.setAttribute("response", response);
		String path = request.getServletPath();
//		logger.debug(path);
		if(path.equals("/") || path.matches(Const.NO_INTERCEPTOR_PATH)){
			return true;
		}else{
			User user = (User)Jurisdiction.getSession().getAttribute(Const.SESSION_USER);
			if(user!=null){
//				path = path.substring(1, path.length());
//				boolean b = Jurisdiction.hasJurisdiction(path); //访问权限校验
//				if(!b){
//					response.sendRedirect(request.getContextPath() + Const.LOGIN);
//				}
//				return b;
				return true;
			}else{
				//登陆过滤
				response.sendRedirect(request.getContextPath() + "?errorType=userIsNull");
				return false;		
			}
		}
	}
	
}
