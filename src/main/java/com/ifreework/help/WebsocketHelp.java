package com.ifreework.help;

import java.util.List;
import java.util.Set;

import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.ifreework.util.Const;

public class WebsocketHelp {

	/**
	 * @Title: getSimpMessagingTemplate
	 * @Description: TODO(获取spring中默认的brokerMessagingTemplate)
	 * @param 
	 * @return   
	 * @throws
	 */
	public static SimpMessagingTemplate getSimpMessagingTemplate() {
		SimpMessagingTemplate t = SpringHelper.getBean("brokerMessagingTemplate", SimpMessagingTemplate.class);
		return t;
	}

	/**
	 * 
	 * @Title: send
	 * @Description: TODO(推送消息到指定地址给全部用户)
	 * @param url      消息推送地址
	 * @param msg      用户名
	 * @return   
	 * @throws
	 */
	public static void send(String url, Object msg) {
		getSimpMessagingTemplate().convertAndSend(url, msg);
	}

	/**
	 * 
	 * @Title: send
	 * @Description: TODO(根据指定用户名称，推送消息到指定地址)
	 * @param userList 用户名列表集
	 * @param url      消息推送地址
	 * @param msg      用户名
	 * @return   
	 * @throws
	 */
	public static void send(List<String> userList, String url, Object msg) {
		if (userList != null) {
			for (String userName : userList) {
				getSimpMessagingTemplate().convertAndSendToUser(userName, url, msg);
			}
		}
	}

	/**
	 * 
	 * @Title: send
	 * @Description: TODO(推送消息到指定用户)
	 * @param userName 用户名
	 * @param url      消息推送地址
	 * @param msg      用户名
	 * @return   
	 * @throws
	 */
	public static void send(String userName, String url, Object msg) {
		getSimpMessagingTemplate().convertAndSendToUser(userName, url, msg);
	}
}
