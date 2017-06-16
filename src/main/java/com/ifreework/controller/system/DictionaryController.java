package com.ifreework.controller.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ifreework.common.controller.BaseControllerSupport;
import com.ifreework.common.entity.PageData;
import com.ifreework.entity.system.Dictionary;
import com.ifreework.service.system.DictionaryService;
import com.ifreework.util.StringUtil;



/**    
 *     
 * 类名称：DictionaryController    
 * 类描述：    
 * 创建人：王宜华    
 * 创建时间：2014-11-26 下午4:46:18    
 * 修改人：王宜华    
 * 修改时间：2014-11-26 下午4:46:18    
 * 修改备注：    
 * @version     
 *     
 */
@Controller
@RequestMapping("/system/dictionary")
public class DictionaryController extends BaseControllerSupport {

	@Autowired
	private DictionaryService dictionaryService ;

	/**
	 * 
	 * 描述：跳转到系统设置首页
	 * @Title: gotoView
	 * @param 
	 * @return   
	 * @throws
	 */
	@RequestMapping()
	public ModelAndView gotoView() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("/system/dictionary/view");
		return mv;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/query")
	@ResponseBody
	public List query() {
		PageData pd = this.getPageData();
		List list = dictionaryService.queryDictionaryList(pd);
		return list;
	}
	
	@RequestMapping("/add")
	public ModelAndView add(){
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		String treeId = pd.getString("treeId");
		Dictionary dictionary = new Dictionary();
		dictionary.setParentId(treeId);
		mv.addObject("dictionary", dictionary);
		mv.setViewName("/system/dictionary/edit");
		return mv;
	}
	
	@RequestMapping("/update")
	public ModelAndView update(){
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		Dictionary dictionary = dictionaryService.getDictionaryById(pd.getString("treeId"));
		mv.addObject("dictionary", dictionary);
		mv.setViewName("/system/dictionary/edit");
		return mv;
	}
	
	@RequestMapping(value = "/save")
	@ResponseBody
	public PageData save(@ModelAttribute( "dictionary" )Dictionary dictionary){
		PageData pd;
		if(StringUtil.isEmpty(dictionary.getDictionaryId())){
			pd = dictionaryService.add(dictionary);
		}else{
			pd = dictionaryService.update(dictionary);
		}
		
		return pd;
	}
}
