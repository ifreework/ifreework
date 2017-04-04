/**    
 * 文件名：SpringHelper.java    
 *    
 * 版本信息：    
 * 日期：2014-11-18    
 * Copyright  Corporation 2014     
 * 版权所有    
 *    
 */
package com.ifreework.help;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.web.context.ContextLoader;

/**
 * 获取spring中配置的bean 类名称：SpringHelper 类描述： 创建人：王宜华 创建时间：2014-11-18 上午11:27:40
 * 修改人：王宜华 修改时间：2014-11-18 上午11:27:40 修改备注：
 * 
 * @version
 * 
 */
public class SpringHelper {

	/**
	 * 根据beanname获取bean getBean(这里用一句话描述这个方法的作用)
	 * 
	 * @param beanName
	 * @return 参数中文名
	 * @return 列出方法的返回值列表（如果需要返回值的话）
	 */
	public static Object getBean(String beanName) {
		return ContextLoader.getCurrentWebApplicationContext().getBean(beanName);
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(String beanName, Class<?> clazz) {
		T t = null;
		t = (T) ContextLoader.getCurrentWebApplicationContext().getBean(beanName, clazz);
		return t;
	}
}
