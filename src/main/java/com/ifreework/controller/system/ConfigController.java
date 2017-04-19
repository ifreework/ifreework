package com.ifreework.controller.system;

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
import com.ifreework.service.system.ConfigService;
import com.ifreework.util.StringUtil;

@Controller
@RequestMapping({ "/system/config" })
public class ConfigController extends BaseControllerSupport {

	@Autowired
	private ConfigService configService;


	/**
	 * 
	 * 描述：跳转到系统设置首页
	 * @Title: gotoView
	 * @param 
	 * @return   
	 * @throws
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView gotoView() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("/system/config/config");
		return mv;
	}
	
	
	@RequestMapping(value = "/save")
	@ResponseBody
	public PageData save(){
		PageData pd = this.getPageData();
		pd = configService.update(pd);
		return pd;
	}
}
