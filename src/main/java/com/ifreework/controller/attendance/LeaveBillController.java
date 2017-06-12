package com.ifreework.controller.attendance;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ifreework.common.controller.BaseControllerSupport;
import com.ifreework.common.entity.PageData;
import com.ifreework.entity.attendance.LeaveBill;
import com.ifreework.entity.system.User;
import com.ifreework.service.attendance.LeaveBillService;
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
@RequestMapping({ "/attendance/leaveBill" })
public class LeaveBillController extends BaseControllerSupport {

	@Autowired
	private LeaveBillService leaveBillService;
	
	/**
	 * 
	 * 描述：跳转到请假首页
	 * @return 
	 */
	@RequestMapping()
	public ModelAndView gotoView() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("/attendance/leaveBill/list");
		return mv;
	}
	
	/**
	 * 
	 * 描述：跳转到新增页面
	 * @return 
	 */
	@RequestMapping("/add")
	public ModelAndView add() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("/attendance/leaveBill/edit");
		return mv;
	}
	
	
	/**
	 * 描述：查询我的请假信息
	 * @return 
	 * @return
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public PageData query(){
		PageData pd = this.getPageData();
		pd = leaveBillService.queryPageList(pd);
		return pd;
	}
	
	@RequestMapping(value = "/save")
	@ResponseBody
	public PageData save(@ModelAttribute("leaveBill") LeaveBill leaveBill) {
		PageData pd;
		if (StringUtil.isEmpty(leaveBill.getLeaveBillId())) {
			pd = leaveBillService.add(leaveBill);
		} else {
			pd = leaveBillService.update(leaveBill);
		}

		return pd;
	}
}
