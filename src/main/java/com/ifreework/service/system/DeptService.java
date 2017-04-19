package com.ifreework.service.system;

import java.util.List;

import com.ifreework.common.entity.PageData;
import com.ifreework.entity.system.Dept;

public interface DeptService {

	/**
	 * 
	 * @Title: queryContacts
	 * @Description: TODO()
	 * @param 
	 * @return   
	 * @throws
	 */
	List<Dept> queryContacts(PageData pd);
	
	/**
	 * 
	 * @Title: queryContacts
	 * @Description: TODO(查询联系人信息)
	 * @param 
	 * @return   
	 * @throws
	 */
	public List<Dept> queryContacts();

}