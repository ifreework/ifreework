package com.ifreework.service.system;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifreework.common.entity.PageData;
import com.ifreework.entity.system.Dept;
import com.ifreework.entity.system.User;
import com.ifreework.help.Jurisdiction;
import com.ifreework.mapper.system.DeptMapper;
import com.ifreework.util.Const;

/**
 * 
 * 描述：    
 * @author：wangyh qq735789026  
 * @创建时间：2016年9月2日 下午2:52:42    
 * @修改人：wangyh    
 * @修改时间：2016年9月2日 下午2:52:42    
 * @version 1.0
 */
@Service("deptService")
public class DeptServiceImpl implements DeptService {

	@Autowired
	DeptMapper deptMapper;

	/**
	 * 
	 * @Title: queryContacts
	 * @Description: TODO()
	 * @param 
	 * @return   
	 * @throws
	 */
	@Override
	public List<Dept> queryContacts(PageData pd) {
		return deptMapper.queryContacts(pd);
	}

	public List<Dept> queryContacts() {
		PageData pd = new PageData();
		pd.put("username", Jurisdiction.getUsername());
		List<Dept> depts = queryContacts(pd);
		sortDeptByName(depts);
		
		for(Dept dept : depts){
			List<User> users =  dept.getChildren();
			onOrOffLine(dept, users);
			sortUserByOnLineOrName(users);
		}
		return depts;
	}

	/**
	 * 
	 * @Title: onOrOffLine
	 * @Description: TODO(判断用户是否在线，并将在线用户数量放入dept中)
	 * @param 
	 * @return   
	 * @throws
	 */
	private void onOrOffLine(Dept dept, List<User> users) {
		int onLineNum = 0;
		for(User user : users){
			if (Const.WEBSOCKET_USER_MAP.containsKey(user.getUsername())) {
				user.setIsOnline("1");
				onLineNum++;
			}
		}
		dept.setOnLineNumber(onLineNum);
	}

	/**
	 * 
	 * @Title: sortUserByOnLineOrName
	 * @Description: TODO(根据是否在线和用户名对联系人进行排序)
	 * @param 
	 * @return   
	 * @throws
	 */
	private void sortUserByOnLineOrName(List<User> users) {
		Collections.sort(users, new Comparator<User>() {
			/*
			 * int compare(Student o1, Student o2) 返回一个基本类型的整型， 返回负数表示：o1 小于o2，
			 * 返回0 表示：o1和o2相等， 返回正数表示：o1大于o2。
			 */
			public int compare(User o1, User o2) {
				int t = o2.getIsOnline().compareTo(o1.getIsOnline());
				if(t == 0) {
					return o2.getPersonName().compareTo(o1.getPersonName());
				} 
				return t;
			}
		});
	}

	/**
	 * 
	 * @Title: sortDeptByName
	 * @Description: TODO(对部门根据名称进行排序)
	 * @param 
	 * @return   
	 * @throws
	 */
	private void sortDeptByName(List<Dept> depts) {
		Collections.sort(depts, new Comparator<Dept>() {
			/*
			 * int compare(Student o1, Student o2) 返回一个基本类型的整型， 返回负数表示：o1 小于o2，
			 * 返回0 表示：o1和o2相等， 返回正数表示：o1大于o2。
			 */
			public int compare(Dept o1, Dept o2) {
				return o2.getName().compareTo(o1.getName());
			}
		});
	}

}
