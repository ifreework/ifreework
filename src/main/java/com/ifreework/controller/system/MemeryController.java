package com.ifreework.controller.system;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ifreework.common.controller.BaseControllerSupport;



/**    
 *     
 * 类名称：AttachmentController    
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
@RequestMapping("/system/memery")
public class MemeryController extends BaseControllerSupport {

	@RequestMapping()
	public String gotoView(){
		return "/system/server/memery";
	}
	
}
