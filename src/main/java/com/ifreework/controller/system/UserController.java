package com.ifreework.controller.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ifreework.common.controller.BaseControllerSupport;
import com.ifreework.common.entity.PageData;
import com.ifreework.entity.system.User;
import com.ifreework.service.system.UserService;
import com.ifreework.util.StringUtil;

/**
 * 
 * 描述：    
 * @author：wangyh qq735789026  
 * @createTime：2017年4月11日 下午4:16:41    
 * @editer：wangyh    
 * @editTime：2017年4月11日 下午4:16:41    
 * @version 1.0
 */
@Controller
@RequestMapping({ "/system/user" })
public class UserController extends BaseControllerSupport {

	@Autowired
	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	
	/**
	 * 
	 * @Title: gotoView
	 * @Description: TODO(根据用户名查询用户信息，并跳转到修改界面)
	 * @param 
	 * @return   
	 * @throws
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView gotoView() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("/system/user/list");
		return mv;
	}
	
	
	@RequestMapping(value = "/query")
	@ResponseBody
	public PageData query(){
		PageData pd = this.getPageData();
		List<User> list = userService.queryUserList(pd);
		pd = new PageData();
		pd.setPagination(list);
		return pd;
	}
	
	
	/**
	 * 描述：根据用户id，重置用户密码
	 * @Title: resetPwd
	 * @param 
	 * @return   
	 * @throws
	 */
	@RequestMapping(value = "/resetPwd")
	@ResponseBody
	public PageData resetPwd(){
		PageData pd = this.getPageData();
		return userService.resetPwd(pd);
	}
	/**
	 * 
	 * @Title: gotoView
	 * @Description: TODO(根据用户名查询用户信息，并跳转到修改界面)
	 * @param 
	 * @return   
	 * @throws
	 */
	@RequestMapping(value = "/edit")
	public ModelAndView edit() {
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		User user = userService.getUserInfoByUserName(pd);
		mv.addObject("user",user);
		mv.setViewName("/system/user/edit");
		return mv;
	}
	
	
	@RequestMapping(value = "/save")
	@ResponseBody
	public PageData save(@ModelAttribute( "user" ) User user){
		PageData pd;
		if(StringUtil.isEmpty(user.getUserId())){
			pd = userService.add(user);
		}else{
			pd = userService.update(user);
		}
		
		return pd;
	}
	
	
}
