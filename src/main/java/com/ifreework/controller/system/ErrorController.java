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





import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ifreework.common.controller.BaseControllerSupport;


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
@RequestMapping({ "/error" })
public class ErrorController extends BaseControllerSupport {

	/**
	 * @Title: error404 @Description: TODO(404错误页面跳转) @param @return @throws
	 */
	@RequestMapping(value = "/404")
	public ModelAndView error404() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("/system/error/404");
		return mv;
	}
	
	/**
	 * @Title: error404 @Description: TODO(404错误页面跳转) @param @return @throws
	 */
	@RequestMapping(value = "/600")
	public ModelAndView error600() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("/system/error/unauthorized");
		return mv;
	}
}
