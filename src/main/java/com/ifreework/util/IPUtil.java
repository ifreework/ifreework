
/**    
 * 文件名：IPUtil.java    
 *    
 * 版本信息：    
 * 日期：2016年7月4日    
 * Copyright  Corporation 2016     
 * 版权所有    
 *    
 */
package com.ifreework.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 描述：
 * 
 * @author：wangyh qq735789026
 * @创建时间：2016年7月4日 下午2:58:11
 * @修改人：wangyh
 * @修改时间：2016年7月4日 下午2:58:11
 * @version 1.0
 */
public class IPUtil {
	/**
	 * 
	 * @Title: getIp
	 * @Description: TODO(获取本机IP地址)
	 * @return String
	 */
	public static String getIp() {
		String ip = "";
		try {
			InetAddress inet = InetAddress.getLocalHost();
			ip = inet.getHostAddress();
			// System.out.println("本机的ip=" + ip);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		return ip;
	}

}
