/**    
 * 文件名：LoginController.java    
 *    
 * 版本信息：    
 * 日期：2014-11-19    
 * Copyright  Corporation 2014     
 * 版权所有    
 *    
 */
package com.ifreework.controller.system;



import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ifreework.common.controller.BaseControllerSupport;
import com.ifreework.entity.system.User;
import com.ifreework.help.HttpRequest;
import com.ifreework.help.Jurisdiction;
import com.ifreework.service.system.UserService;
import com.ifreework.util.Const;
import com.ifreework.util.FileUtil;
import com.ifreework.util.PageData;
import com.ifreework.util.PropertiesUtil;
import com.ifreework.util.StringUtil;

/**
 * 
 * 类名称：LoginController 
 * 类描述： 
 * 创建人：王宜华 
 * 创建时间：2014-11-19 下午12:40:35 
 * 修改人：王宜华
 * 修改时间：2014-11-19 下午12:40:35 
 * 修改备注：
 * 
 * @version
 * 
 */
@Controller
@RequestMapping({ "/" })
public class LoginController extends BaseControllerSupport {

	@Autowired
	UserService userService;
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
  
	/**
	 * 
	 * @Title: gotoIndexView 
	 * @Description:
	 * @TODO(用户登录界面跳转，如果已经登录，则跳转到Main页面) 
	 * @param @return @throws
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView gotoIndexView() {
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		String sysName = PropertiesUtil.getProperty(FileUtil.getRootPath() + Const.SYSTEM_CONFIG, "systemName");
		pd.put("SYSNAME", sysName); // 读取系统名称
		mv.addObject("pd", pd);
		User user = Jurisdiction.getUser();
		if (user != null) {
			mv.addObject("user",user);
			mv.setViewName("/system/main/main");
		} else {
			mv.setViewName("/system/index/index");
		}
		return mv;
	}

	/**
	 * 
	 * @Title: login 
	 * @Description: TODO(用户登录验证) 
	 * @param @return @throws
	 */
	@RequestMapping(value = "/login", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object login() throws Exception {
		PageData pd = getPageData();
		return userService.validateUserByNameAndPwd(pd);
	}

	/**
	 * 
	 * @Title: gotoIndexView @Description: TODO(用户首页跳转) @param @return @throws
	 */
	@RequestMapping(value = "/main")
	public ModelAndView gotoMainView() {
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		mv.addObject("pd", pd);
		
		User user = Jurisdiction.getUser();
		mv.addObject("user",user);
		
		String sysName = PropertiesUtil.getProperty(FileUtil.getRootPath() + Const.SYSTEM_CONFIG, "systemName");
		pd.put("SYSNAME", sysName); // 读取系统名称
		mv.setViewName("/system/main/main");
		return mv;
	}

	/**
	 * 
	 * @Title: getWeather @Description: TODO(获取天气预报) @param @return @throws
	 */
	@RequestMapping(value = "/main/getWeather")
	@ResponseBody
	public String getWeather() {
		String weather_server = PropertiesUtil.getProperty(FileUtil.getRootPath() + Const.SYSTEM_CONFIG,
				"weather_server");
		User user = Jurisdiction.getUser();
		String cityCode = user.getCityCode();
		String url = weather_server + cityCode;
		System.out.println(url);
		String html = HttpRequest.sendPost(url, "");
		return getBodyHtml(html);
	}

	private String getBodyHtml(String html) {
		if (!StringUtil.isEmpty(html)) {
			html = html.substring(html.indexOf("<body"));
			html = html.substring(html.indexOf(">") + 1);
			html = html.substring(0, html.indexOf("<script"));
		}
		return html;
	}

	/**
	 * @Title: error404 @Description: TODO(404错误页面跳转) @param @return @throws
	 */
	@RequestMapping(value = "/error/404")
	public ModelAndView error404() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("/system/error/404");
		return mv;
	}

	@RequestMapping(value = "/main/userChangeImg")
	public ModelAndView userChangeImg() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("/system/main/user/img");
		return mv;
	}
	
	@RequestMapping(value = "/main/userImgUpload")
	@ResponseBody
	public PageData userImgUpload(@RequestParam(value="file",required=false) MultipartFile file,
			double width,double height,double sw,double sh,double sx,double sy){
		PageData pd = new PageData();
		pd.put("width", width);
		pd.put("height", height);
		pd.put("sw", sw);
		pd.put("sh", sh);
		pd.put("sx", sx);
		pd.put("sy", sy);
		pd = userService.userImgUpload(file, width, height, sw, sh, sx, sy);
		return pd;
	}
	
	@RequestMapping(value = "/main/userImgDownload")
	@ResponseBody
	public String userImgDownload() throws Exception{
		PageData pd = this.getPageData();
		User user;
		if(StringUtil.isEmpty(pd.getString("username"))){
			user = Jurisdiction.getUser();
		}else{
			user = userService.getUserInfoByUserName(pd);
		}
		
		HttpServletResponse res = this.getHttpServletResponse();
		String imgPath = user.getImgPath();
		if(StringUtil.isEmpty(imgPath)){
			imgPath = FileUtil.getRootPath() + "resources/img/main/defeat.jpg";
		}
		FileUtil.fileDownload(res, imgPath);
		return null;
	}

	
	@RequestMapping(value = "/main/contractImgDownload")
	@ResponseBody
	public String contractImgDownload() throws Exception{
		PageData pd = this.getPageData();
		HttpServletResponse res = this.getHttpServletResponse();
		String file = pd.getString("imgPath");
		String isOnLine  = pd.getString("isOnline");
		if(StringUtil.isEmpty(file)){
			file = FileUtil.getRootPath() + "resources/img/main/defeat.jpg";
		}
		if(StringUtil.isEmpty(isOnLine) || "0".equals(isOnLine)){
			file += ".offLine";
		}
		FileUtil.fileDownload(res, file);
		return null;
	}

	
	
	@RequestMapping(value = "/main/skinChoose")
	@ResponseBody
	public PageData skinChoose(){
		PageData pd = this.getPageData();
		User user = Jurisdiction.getUser();
		String skin = pd.getString("skin");
		user.setSkin(skin);
		pd = userService.updateUser(user);
		return pd;
	}
	
	@RequestMapping(value = "/main/message")
	public ModelAndView message() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("/system/main/message/message");
		return mv;
	}
	
	/**
	 * 
	 * @Title: queryContacts
	 * @Description: TODO(获取联系人信息)
	 * @param 
	 * @return   
	 * @throws
	 */
	@RequestMapping(value = "/main/queryContacts")
	@ResponseBody
	public Map<String, Object> queryContacts( ){
		PageData pd = this.getPageData();
		return userService.queryContacts(pd);
	}
	
	/**
	 * 用户注销
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/logout")
	public ModelAndView logout(){
		if(Jurisdiction.getUser() == null ){
			return gotoIndexView();                                                                             
		}
		String USERNAME = Jurisdiction.getUsername();	//当前登录的用户名
		logger.debug( USERNAME+"退出系统");
		Session session = Jurisdiction.getSession();	//以下清除session缓存
		session.removeAttribute(Const.SESSION_USER);
		session.removeAttribute(USERNAME + Const.SESSION_ROLE_RIGHTS);
		session.removeAttribute(USERNAME + Const.SESSION_allmenuList);
		session.removeAttribute(USERNAME + Const.SESSION_menuList);
		session.removeAttribute(USERNAME + Const.SESSION_QX);
		session.removeAttribute(Const.SESSION_userpds);
		session.removeAttribute(Const.SESSION_USERNAME);
		session.removeAttribute(Const.SESSION_USERROL);
		//shiro销毁登录
		Subject subject = SecurityUtils.getSubject(); 
		subject.logout();
		return gotoIndexView();
	}
}
