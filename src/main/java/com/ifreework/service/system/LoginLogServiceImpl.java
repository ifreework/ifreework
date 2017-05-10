/**    
 * 文件名：FileUploadServiceImpl.java    
 *    
 * 版本信息：    
 * 日期：2014-12-16    
 * Copyright  Corporation 2014     
 * 版权所有    
 *    
 */
package com.ifreework.service.system;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifreework.common.entity.PageData;
import com.ifreework.common.manager.ServletRequestManager;
import com.ifreework.common.shiro.listener.LoginLogManager;
import com.ifreework.entity.system.LoginLog;
import com.ifreework.mapper.system.LoginLogMapper;

import cz.mallat.uasparser.OnlineUpdater;
import cz.mallat.uasparser.UASparser;
import cz.mallat.uasparser.UserAgentInfo;


/**
 * 
 * 描述：    附件上传
 * @author：wangyh
 * @createDate：2017年5月8日
 * @modify：wangyh    
 * @modifyDate：2017年5月8日 
 * @version 1.0
 */
@Service("loginLogService")
public class LoginLogServiceImpl implements LoginLogService, LoginLogManager {
	
	@Autowired
	private LoginLogMapper loginLogMapper;

	/**
	 * 
	 * 描述：分页查询
	 * @Title: queryPageList
	 * @param 
	 * @return   
	 * @throws
	 */
	@Override
	public PageData queryPageList(PageData pd) {
		List<LoginLog> list = loginLogMapper.queryPageList(pd);
		pd = new PageData();
		pd.setPagination(list);
		return pd;
	}

	@Override
	public void add(String username) {
		HttpServletRequest request = ServletRequestManager.getHttpServletRequest();
		String ip = request.getRemoteAddr();// 访问者IP
		String agent = request.getHeader("User-Agent");
		UserAgentInfo userAgentInfo = null;
		
		try {
			userAgentInfo = new UASparser(OnlineUpdater.getVendoredInputStream()).parse(agent);
			LoginLog loginLog = new LoginLog();
			loginLog.setUsername(username);
			loginLog.setBrowser(userAgentInfo.getUaFamily());
			loginLog.setBrowserVersion(userAgentInfo.getBrowserVersionInfo());
			loginLog.setIp(ip);
			loginLog.setOs(userAgentInfo.getOsFamily());
			loginLogMapper.add(loginLog);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void logoutLog(String username) {
		loginLogMapper.update(username);
	}

}
