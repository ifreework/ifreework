package com.ifreework.common.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;


/**
 * 描述：异常处理工具类
 * 
 * @author：wangyh qq735789026
 * @创建时间：2016年7月5日 上午10:42:42
 * @修改人：wangyh
 * @修改时间：2016年7月5日 上午10:42:42
 * @version 1.0
 */
public class MyExceptionResolver implements HandlerExceptionResolver {

	Logger logger = Logger.getLogger(MyExceptionResolver.class);
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		// TODO Auto-generated method stub
		System.out.println("==============异常开始=============");
		logger.error(ex);
		ex.printStackTrace();
		System.out.println("==============异常结束=============");
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("exception", ex.toString().replaceAll("\n", "<br/>"));
		return mv;
	}

}
