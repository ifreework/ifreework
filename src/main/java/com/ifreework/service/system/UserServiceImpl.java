/**    
 * 文件名：UserServiceImpl.java    
 *    
 * 版本信息：    
 * 日期：2016年7月6日    
 * Copyright  Corporation 2016     
 * 版权所有    
 *    
 */
package com.ifreework.service.system;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ifreework.common.entity.PageData;
import com.ifreework.entity.system.Config;
import com.ifreework.entity.system.User;
import com.ifreework.help.Jurisdiction;
import com.ifreework.mapper.system.UserMapper;
import com.ifreework.util.Const;
import com.ifreework.util.FileUtil;
import com.ifreework.util.ImageUtil;
import com.ifreework.util.PropertiesUtil;
import com.ifreework.util.SecurityUtil;
import com.ifreework.util.StringUtil;

/**
 * 描述：
 * 
 * @author：wangyh qq735789026
 * @创建时间：2016年7月6日 下午4:30:28
 * @修改人：wangyh
 * @修改时间：2016年7月6日 下午4:30:28
 * @version 1.0
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;

	public UserMapper getUserMapper() {
		return userMapper;
	}

	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	/**
	 * 
	 * @Title: getUserInfoByUserName @Description:
	 *         TODO(通过用户名获取用户信息) @param @return @throws
	 */
	public User getUserInfoByUserName(PageData pd) {
		return userMapper.getUserInfo(pd);
	}

	/**
	 * 
	 * @Title: validateUserByNameAndPwd @Description:
	 *         TODO(登录用户名密码验证) @param @return @throws
	 */
	public PageData validateUserByNameAndPwd(PageData pd) {
		// TODO Auto-generated method stub
		String username = pd.getString("username");
		String pwd = pd.getString("password");
		Session session = Jurisdiction.getSession();

		if (!StringUtil.isEmpty(username) && !StringUtil.isEmpty(pwd)) {
			User user = userMapper.getUserInfo(pd);
			if (user != null) {
				pwd = SecurityUtil.encrypt(pwd);
				if (pwd.equals(user.getPassword())) {
					String status = user.getStatus();
					if ("1".equals(status)) {
						session.setAttribute(Const.SESSION_USER, user); // 把用户信息放session中
						// shiro加入身份验证
						Subject subject = SecurityUtils.getSubject();
						UsernamePasswordToken token = new UsernamePasswordToken(username, pwd);
						try {
							subject.login(token);
							pd.setResult(Const.SUCCESS);
						} catch (AuthenticationException e) {
							pd.setResult(Const.FAILED);
							pd.setMsg("登录认证失败。");
						}
						return pd;
					}
					pd.setResult(Const.FAILED);
					pd.setMsg("该用户暂未启用，请与管理员联系。");
					return pd;
				}
			}
		}
		pd.setResult(Const.FAILED);
		pd.setMsg("用户名或者密码错误，请重新输入。");
		return pd;
	}

	/**
	 * 文件上传后图片保存位置
	 * 
	 * @param file
	 * @return
	 */
	public PageData userImgUpload(MultipartFile file, double width, double height, double sw, double sh, double sx,
			double sy) {
		PageData pageData = new PageData();
		String rootPath = "";
		String imgPath = ""; 
		String localPath = "";
		try {
			Map<String, Integer> map = ImageUtil.getImgXY(file.getInputStream());
			int oW = map.get("width"), oH = map.get("height");
			sw = sw * oW / width;
			sh = sh * oH / height;
			sx = sx * oW / width;
			sy = sy * oH / height;

			User user = Jurisdiction.getUser();
			imgPath += "/" + user.getUsername();
			rootPath = FileUtil.getRootPath() + "temp";
			
			localPath = ImageUtil.cutImage(file.getInputStream(), rootPath + imgPath, file.getOriginalFilename(), (int) sx, (int) sy,
					(int) sw, (int) sh);
			ImageUtil.changeImge(localPath, localPath + ".offLine");
			FileUtil.fileUpload(localPath + ".offLine", imgPath);
			imgPath = FileUtil.fileUpload(localPath, imgPath);
			user.setImgPath(imgPath);
			update(user);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		pageData.put("fileName", imgPath);
		return pageData;
	}

	/**
	 * 修改用户信息
	 * 
	 * @param user
	 * @return
	 */
	public PageData update(User user) {
		PageData pd = new PageData();
		userMapper.update(user);
		pd.setResult(Const.SUCCESS);
		return pd;
	}

	/**
	 * 新增用户信息
	 */
	public PageData add(User user) {
		PageData pd = new PageData();
		userMapper.add(user);
		pd.setResult(Const.SUCCESS);
		return pd;
	}

	/**
	 * 
	 * @Title: queryContacts @Description:
	 *         TODO(获取所用联系人和在线联系人信息) @param @return @throws
	 */
	public Map<String, Object> queryContacts(PageData pd) {
		Map<String, Object> map = new HashMap<String, Object>();
		pd.put("username", Jurisdiction.getUser().getUsername());
		List<User> list = queryUserList(pd);
		int onLineNum = 0;
		for (User user : list) {
			if (Const.WEBSOCKET_USER_MAP.containsKey(user.getUsername())) {
				user.setIsOnline("1");
				onLineNum++;
			}
		}
		Collections.sort(list, new Comparator<User>() {
			/*
			 * int compare(Student o1, Student o2) 返回一个基本类型的整型， 返回负数表示：o1 小于o2，
			 * 返回0 表示：o1和o2相等， 返回正数表示：o1大于o2。
			 */
			public int compare(User o1, User o2) {
				int t = o2.getIsOnline().compareTo(o1.getIsOnline());
				if (t == 0) {
					return o2.getPersonName().compareTo(o1.getPersonName());
				}
				return t;
			}
		});
		map.put("allContacts", list);// 所有联系人信息
		map.put("onLineNum", onLineNum);// 在线联系人数量
		map.put("allNum", list.size());// 全部联系人数量
		return map;

	}

	@Override
	public List<User> queryUserList(PageData pd) {
		// TODO Auto-generated method stub
		return userMapper.queryUserList(pd);
	}

	public static void main(String[] args) {
		System.out.println("accccyy".compareTo("x"));
	}
}
