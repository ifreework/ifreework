package com.ifreework.service.system;


import com.ifreework.common.entity.PageData;
import com.ifreework.entity.system.LoginLog;

public interface LoginLogService {
	public PageData queryPageList(PageData pd);
	public void add(String username);
}
