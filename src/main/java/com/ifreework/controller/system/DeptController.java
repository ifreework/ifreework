package com.ifreework.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ifreework.common.controller.BaseControllerSupport;
import com.ifreework.service.system.DeptService;

@Controller
@RequestMapping({ "/system/dept" })
public class DeptController extends BaseControllerSupport {

	@Autowired
	private DeptService deptService;

	public DeptService getDeptService() {
		return deptService;
	}

	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}
	
	/**
	 * 
	 * @Title: gotoIndexView 
	 * @Description:
	 * @TODO(用户登录界面跳转，如果已经登录，则跳转到Main页面) 
	 * @param @return @throws
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView gotoView() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("/system/dept/dept");
		return mv;
	}
}
