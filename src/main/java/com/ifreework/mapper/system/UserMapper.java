package com.ifreework.mapper.system;

import java.util.List;

import com.ifreework.entity.system.User;
import com.ifreework.util.PageData;

public interface UserMapper {
	/**
	 * 
	 * @Title: getUserInfo
	 * @Description: TODO(通过用户名密码或者UserId获取用户信息，如果该用户信息不存在，则返回null)
	 * @param 
	 * @return   
	 * @throws
	 */
	public User getUserInfo(PageData pd );
	
	public User getUserInfoByUserName(PageData pd);
	
	
	public List<User> queryUserList(PageData pd );
	
	public void update(User user);
}
