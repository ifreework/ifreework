package com.ifreework.help;

import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.ifreework.entity.system.User;
import com.ifreework.util.Const;


/**
 * 
 * 描述：权限处理    
 * @author：wangyh qq735789026  
 * @创建时间：2016年7月6日 下午4:40:11    
 * @修改人：wangyh    
 * @修改时间：2016年7月6日 下午4:40:11    
 * @version 1.0
 */
public class Jurisdiction {


	/**获取当前登录的用户名
	 * @return
	 */
	public static String getUsername(){
		return getUser().getUsername();
	}
	
	

	/**获取当前登录的用户名
	 * @return
	 */
	public static User getUser(){
		return (User) getSession().getAttribute(Const.SESSION_USER);
	}
	
	/**获取当前按钮权限(增删改查)
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> getHC(){
		return (Map<String, String>)getSession().getAttribute(getUsername() + Const.SESSION_QX);
	}
	
	/**shiro管理的session
	 * @return
	 */
	public static Session getSession(){
		Subject currentUser = SecurityUtils.getSubject();  
		return currentUser.getSession();
	}
}
